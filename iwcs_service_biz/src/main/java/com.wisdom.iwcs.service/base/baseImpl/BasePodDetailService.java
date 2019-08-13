package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.podUtils.PodTaskLockEnum;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapstruct.base.BasePodDetailMapStruct;
import com.wisdom.iwcs.service.base.IBasePodDetailService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class BasePodDetailService implements IBasePodDetailService {
    private final Logger logger = LoggerFactory.getLogger(BasePodDetailService.class);

    private final BasePodDetailMapper basePodDetailMapper;

    private final BasePodDetailMapStruct basePodDetailMapStruct;

    @Autowired
    public BasePodDetailService(BasePodDetailMapStruct basePodDetailMapStruct, BasePodDetailMapper basePodDetailMapper) {
        this.basePodDetailMapStruct = basePodDetailMapStruct;
        this.basePodDetailMapper = basePodDetailMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BasePodDetailDTO }
     * @return int
     */
    @Override
    public int insert(BasePodDetailDTO record) {
        BasePodDetail basePodDetail = basePodDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodDetail.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        basePodDetail.setCreatedTime(new Date());
        basePodDetail.setCreatedBy(userId);
        basePodDetail.setLastModifiedBy(userId);
        basePodDetail.setLastModifiedTime(new Date());

        int num = basePodDetailMapper.insert(basePodDetail);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BasePodDetailDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BasePodDetailDTO> records) {
        List<BasePodDetail> recordList = basePodDetailMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = basePodDetailMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BasePodDetailDTO }
     */
    @Override
    public BasePodDetailDTO selectByPrimaryKey(Integer id) {

        BasePodDetail basePodDetail = basePodDetailMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(basePodDetail, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return basePodDetailMapStruct.toDto(basePodDetail);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BasePodDetailDTO }
     * @return {@link List<BasePodDetailDTO> }
     */
    @Override
    public List<BasePodDetailDTO> selectSelective(BasePodDetailDTO record) {
        BasePodDetail basePodDetail = basePodDetailMapStruct.toEntity(record);

        List<BasePodDetail> basePodDetailList = basePodDetailMapper.select(basePodDetail);
        return basePodDetailMapStruct.toDto(basePodDetailList);
    }

    /**
     * 判断当前bincode 是否已有任务
     */
    @Override
    public List<BasePodDetailDTO> judgeBincodeIfTask(String bincode) {
        BasePodDetailDTO basePodDetailDTO = new BasePodDetailDTO();
        basePodDetailDTO.setPodCode(bincode.substring(0, 6));
        basePodDetailDTO.setLockStat(0);
        return selectSelective(basePodDetailDTO);
    }

    @Override
    public List<BasePodDetailDTO> selectByInLock() {
        List<BasePodDetailDTO> basePodDetails = basePodDetailMapper.selectByInLock();
        return basePodDetails;
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BasePodDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BasePodDetailDTO record) {
        BasePodDetail basePodDetail = basePodDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodDetail.setLastModifiedBy(userId);
        basePodDetail.setLastModifiedTime(new Date());

        int num = basePodDetailMapper.updateByPrimaryKey(basePodDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BasePodDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BasePodDetailDTO record) {
        BasePodDetail basePodDetail = basePodDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodDetail.setLastModifiedBy(userId);
        basePodDetail.setLastModifiedTime(new Date());

        int num = basePodDetailMapper.updateByPrimaryKeySelective(basePodDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = basePodDetailMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteLogicByPrimaryKey(Integer id) {
        return basePodDetailMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return basePodDetailMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return basePodDetailMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BasePodDetailDTO> }
     */
    @Override
    public GridReturnData<BasePodDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BasePodDetailDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<BasePodDetailDTO> list = basePodDetailMapper.selectPage(map);

        PageInfo<BasePodDetailDTO> pageInfo = new PageInfo<>(list);
        mGridReturnData.setPageInfo(pageInfo);

        return mGridReturnData;
    }

    /**
     * 根据task
     *
     * @param taskLockValue
     * @return
     */
    @Override
    public String returnPodLockNameByResolveLockValue(Integer taskLockValue) {
        if (taskLockValue == 0) {
            PodTaskLockEnum podTaskLockEnum = PodTaskLockEnum.returnEnumByTaskValue(taskLockValue);
            return podTaskLockEnum.getName();
        }
        String podTaskLockName = "";
        String binaryValue = Integer.toBinaryString(taskLockValue);
        Integer binaryValueSize = binaryValue.length();
        Integer powStartNum = binaryValueSize - 1;
        for (int i = 0; i < binaryValueSize; i++) {
            Integer binaryIndexValue = Integer.parseInt(String.valueOf(binaryValue.charAt(i)));
            if (binaryIndexValue == 1) {
                int value = (int) Math.pow(2, powStartNum);
                PodTaskLockEnum podTaskLockEnum = PodTaskLockEnum.returnEnumByTaskValue(value);
                podTaskLockName += podTaskLockEnum.getName();
            }
            powStartNum--;
        }

        return podTaskLockName;
    }

    /**
     * 更改货架空满
     * @param
     * @return
     */
    @Override
    public Result savePodInStock(BasePodDetailDTO record){
        Preconditions.checkBusinessError(Strings.isNullOrEmpty(record.getPodCode()),"缺少货架号");
        Preconditions.checkBusinessError(record.getInStock() == null || record.getInStock() <0 || record.getInStock() > 1,"空满状态值不正确，1-有货，0-空");
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(record.getPodCode());
        Preconditions.checkBusinessError(basePodDetail == null,"货架："+record.getPodCode()+"不存在");
        basePodDetailMapper.updateInStock(record.getPodCode(),record.getInStock());
        return new Result();
    }
}
