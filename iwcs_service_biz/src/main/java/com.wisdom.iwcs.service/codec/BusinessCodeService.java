package com.wisdom.iwcs.service.codec;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.codec.BusinessCode;
import com.wisdom.iwcs.domain.codec.dto.BusinessCodeDto;
import com.wisdom.iwcs.domain.codec.dto.BusinessCodePageRequest;
import com.wisdom.iwcs.domain.operation.dto.BusinessTypeDto;
import com.wisdom.iwcs.domain.system.BusinessCodeSearchDTO;
import com.wisdom.iwcs.mapper.codec.BusinessCodeMapper;
import com.wisdom.iwcs.mapstruct.codec.BusinessCodeMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class BusinessCodeService {
    private final Logger logger = LoggerFactory.getLogger(BusinessCodeService.class);

    @Autowired
    BusinessCodeMapper businessCodeMapper;

    @Autowired
    BusinessCodeMapStruct businessCodeMapStruct;

    /**
     * 写入记录
     *
     * @param BusinessCodeDto record
     * @return int
     */
    public Result insert(BusinessCodeDto record) {
        BusinessCode businessCode = businessCodeMapStruct.toEntity(record);
        if (businessCode.getCodeType().equals("CHINESE_NAME_COMMODITY") && StringUtils.isEmpty(businessCode.getCode())) { // 如果是新增中文品名
            try {
                String Code = PinyinHelper.getShortPinyin(businessCode.getChineseName());
                businessCode.setCode(Code);
            } catch (PinyinException e) {
                businessCode.setCode("");
                e.printStackTrace();
            }
        }

        BusinessCode checkBusinessCode = businessCodeMapper.getByCodeAndType(businessCode.getCode(), businessCode.getCodeType());
        if (checkBusinessCode != null) {
            return new Result(400, "重复数据");
        }
        Integer userId = SecurityUtils.getCurrentUserId();
        // TODO 这里delete_flag 为1时为正常
        businessCode.setDeleteFlag(1);

        businessCode.setCreatedTime(new Date());
        businessCode.setCreatedBy(userId);
        businessCode.setLastModifiedBy(userId);
        businessCode.setLastModifiedTime(new Date());

        int num = businessCodeMapper.insertSelective(businessCode);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return new Result();
    }

    /**
     * 批量写入记录
     *
     * @param List<BusinessCodeDto> records
     * @return int
     */
    public int insertBatch(List<BusinessCodeDto> records) {
        List<BusinessCode> recordList = businessCodeMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = businessCodeMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param Integer id
     * @return BusinessCodeDto
     */
    public BusinessCodeDto selectByPrimaryKey(Integer id) {

        BusinessCode businessCode = businessCodeMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(businessCode, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return businessCodeMapStruct.toDto(businessCode);
    }

    /**
     * 根据字段选择性查询
     *
     * @param BusinessCodeDto record
     * @return List<BusinessCodeDto>
     */
    public List<BusinessCodeDto> selectSelective(BusinessCodeDto record) {
        BusinessCode businessCode = businessCodeMapStruct.toEntity(record);

        List<BusinessCode> businessCodeList = businessCodeMapper.select(businessCode);
        return businessCodeMapStruct.toDto(businessCodeList);
    }

    /**
     * 根据主键更新
     *
     * @param BusinessCodeDto record
     * @return int
     */
    public int updateByPrimaryKey(BusinessCodeDto record) {
        BusinessCode businessCode = businessCodeMapStruct.toEntity(record);

        BusinessCode oldDate = businessCodeMapper.selectByPrimaryKey(record.getId());
        if (oldDate == null) {
            throw new BusinessException("代码不存在");
        }
        Integer userId = SecurityUtils.getCurrentUserId();
        if (!userId.equals(oldDate.getCreatedBy())) {
            throw new BusinessException("只能修改自己创建的代码");
        }

        businessCode.setLastModifiedBy(userId);
        businessCode.setLastModifiedTime(new Date());

        int num = businessCodeMapper.updateByPrimaryKey(businessCode);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param BusinessCodeDto record
     * @return int
     */
    public int updateByPrimaryKeySelective(BusinessCodeDto record) {
        BusinessCode businessCode = businessCodeMapStruct.toEntity(record);
        BusinessCode oldDate = businessCodeMapper.selectByPrimaryKey(record.getId());
        if (oldDate == null) {
            throw new BusinessException("代码不存在");
        }
        Integer userId = SecurityUtils.getCurrentUserId();
        if (!userId.equals(oldDate.getCreatedBy())) {
            throw new BusinessException("只能修改自己创建的代码");
        }


        businessCode.setLastModifiedBy(userId);
        businessCode.setLastModifiedTime(new Date());

        int num = businessCodeMapper.updateByPrimaryKeySelective(businessCode);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param Integer id
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = businessCodeMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param Integer id
     * @return int
     */
    public int deleteLogicByPrimaryKey(Integer id) {
        return businessCodeMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return businessCodeMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param List<String> ids
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return businessCodeMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param GridPageRequest 　gridPageRequest
     * @return　GridReturnData<BusinessCodeDto>
     */
    public GridReturnData<BusinessCodeDto> selectPage(BusinessCodePageRequest gridPageRequest) {
        Integer user_id = SecurityUtils.getCurrentUserId();

        GridReturnData<BusinessCodeDto> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map map = new HashMap();
        filterList.stream().forEach(gridFilterInfo -> {//封装筛选条件
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        if (gridPageRequest.getBusinessCodeId() != null) {
            map.put("businessCodeId", gridPageRequest.getBusinessCodeId());
        }
        if (gridPageRequest.getCode() != null && gridPageRequest.getCode() != "") {
            map.put("code", gridPageRequest.getCode());
        }
        if (gridPageRequest.getAirPortFlag() != null && gridPageRequest.getAirPortFlag() != "") {
            map.put("airPortFlag", gridPageRequest.getAirPortFlag());
        }
        map.put("searchKey", gridPageRequest.getSearchKey());
        //对map中的参数的合法性进行校验
        map.put("codeType", gridPageRequest.getCodeType());
        if ("1".equals(gridPageRequest.getSearchFromBegin())) {
            map.put("searchFromBegin", gridPageRequest.getSearchFromBegin());
        }

        //忽略冻结
        map.put("ignoreFreeze", gridPageRequest.getIgnoreFreeze());

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<BusinessCodeDto> list = businessCodeMapper.selectDTOByMap(map);
        PageInfo<BusinessCodeDto> pageInfo = new PageInfo<>(list);
        mGridReturnData.setPageInfo(pageInfo);
        return mGridReturnData;
    }


    public Result getAllCodeByType(String type) {
        List<BusinessCode> list = businessCodeMapper.getAllCodeByType(type);
        return new Result(list);
    }


    public BusinessTypeDto getXHZCodeByTyp() {
        //包装等级
        List<BusinessCode> hazardImdgList = businessCodeMapper.getAllCodeByType("PACKING_GROUP");
        //中文品名
        List<BusinessCode> chineseNameList = businessCodeMapper.getAllCodeByType("CHINESE_NAME_COMMODITY");
        //货物种类
        List<BusinessCode> goodsTypeList = businessCodeMapper.getAllCodeByType("GOODS_TYPE");
        //签单方式
        List<BusinessCode> billTypeList = businessCodeMapper.getAllCodeByType("SIGNING_WAY");
        //付费方式
        List<BusinessCode> payTypeList = businessCodeMapper.getAllCodeByType("PAY_TYPE");
        //贸易方式
        List<BusinessCode> fobCifList = businessCodeMapper.getAllCodeByType("TRADE_MODE");
        //揽货类型
        List<BusinessCode> freightTypeList = businessCodeMapper.getAllCodeByType("FREIGHT_TYPE");
        //运输条款
        List<BusinessCode> transitClause = businessCodeMapper.getAllCodeByType("TRANSIT_CLAUSE");
        //等级
        List<BusinessCode> levelIntList = businessCodeMapper.getAllCodeByType("LEVEL_INT");
        //服务项目
        List<BusinessCode> serviceItemList = businessCodeMapper.getAllCodeByType("SERVICE_PROJECT");
        //服务项目
        List<BusinessCode> importServiceItemList = businessCodeMapper.getAllCodeByType("IMPORT_SERVICE_PROJECT");
        //服务项目
        List<BusinessCode> airExportServiceItemList = businessCodeMapper.getAllCodeByType("AIR_TRANSPORTATION_SERVICE_PROJECT");

        //正本提单
        List<BusinessCode> orblSumList = businessCodeMapper.getAllCodeByType("BILL_OF_LADING");
        //副本提单
        List<BusinessCode> secOrblSumList = businessCodeMapper.getAllCodeByType("NUMBER_OF_COPIES");
        //运价等级
        List<BusinessCode> rateGradeList = businessCodeMapper.getAllCodeByType("FREIGHT_RATE_GRADE");
        //币种
        List<BusinessCode> currencyList = businessCodeMapper.getAllCodeByType("CURRENCY");


        BusinessTypeDto businessTypeDto = new BusinessTypeDto();
        businessTypeDto.setHazardImdgList(hazardImdgList);
        businessTypeDto.setChineseNameList(chineseNameList);
        businessTypeDto.setGoodsTypeList(goodsTypeList);
        businessTypeDto.setBillTypeList(billTypeList);
        businessTypeDto.setPayTypeList(payTypeList);
        businessTypeDto.setFobCifList(fobCifList);
        businessTypeDto.setFreightTypeList(freightTypeList);
        businessTypeDto.setTransitClause(transitClause);
        businessTypeDto.setLevelIntList(levelIntList);
        businessTypeDto.setServiceItemList(serviceItemList);
        businessTypeDto.setImportServiceItemList(importServiceItemList);
        businessTypeDto.setOrblSumList(orblSumList);
        businessTypeDto.setSecOrblSumList(secOrblSumList);
        businessTypeDto.setAirExportServiceItemList(airExportServiceItemList);
        businessTypeDto.setRateGradeList(rateGradeList);
        businessTypeDto.setCurrencyList(currencyList);
        return businessTypeDto;
    }

    public Result searchAllBusinessCode(BusinessCodeSearchDTO businessCodeSearchDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("codeType", businessCodeSearchDTO.getCodeType());
        map.put("searchKey", businessCodeSearchDTO.getSearchKey());
        map.put("limit", 30);
        List<BusinessCode> list = businessCodeMapper.searchFromAll(map);
        return new Result(list);
    }

    public Result searchAllBusinessCodeMore(BusinessCodeSearchDTO businessCodeSearchDTO) {
        Map<String, Object> map = new HashMap<>();
        map.put("codeType", businessCodeSearchDTO.getCodeType());
        map.put("searchKey", businessCodeSearchDTO.getSearchKey());
        List<BusinessCode> list = businessCodeMapper.searchFromAll(map);
        return new Result(list);
    }

    public Result selectAllBusinessCode(BusinessCode businessCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("codeType", businessCode.getCodeType());
        List<BusinessCode> list = businessCodeMapper.searchFromAll(map);
        return new Result(list);
    }


    public Result getAllUseBusinessCode(BusinessCodePageRequest businessCodePageRequest) {
        Map map = new HashMap();
        map.put("code", businessCodePageRequest.getCode());
        map.put("codeType", businessCodePageRequest.getCodeType());
        map.put("freezeFlag", businessCodePageRequest.getFreezeFlag());
        map.put("deleteFlag", businessCodePageRequest.getDeleteFlag());
        List<BusinessCode> list = businessCodeMapper.getAllUseBusinessCode(map);
        return new Result(list);
    }
}
