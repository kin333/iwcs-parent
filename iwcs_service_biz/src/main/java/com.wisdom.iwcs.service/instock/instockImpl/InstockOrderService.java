package com.wisdom.iwcs.service.instock.instockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.instock.InstockOrder;
import com.wisdom.iwcs.domain.instock.InstockOrderDetail;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderConditionDto;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDTO;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailDTO;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.DeleteInstockOrderDTO;
import com.wisdom.iwcs.mapper.instock.InstockOrderDetailMapper;
import com.wisdom.iwcs.mapper.instock.InstockOrderMapper;
import com.wisdom.iwcs.mapstruct.instock.InstockOrderMapStruct;
import com.wisdom.iwcs.service.instock.IInstockOrderService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

import static com.wisdom.iwcs.common.utils.InStockConstants.instockSrc.UPSYNCHRONOUS;

@Service
@Transactional(rollbackFor = Exception.class)
public class InstockOrderService implements IInstockOrderService {
    private final Logger logger = LoggerFactory.getLogger(InstockOrderService.class);

    private final InstockOrderMapper instockOrderMapper;

    private final InstockOrderMapStruct instockOrderMapStruct;

    @Autowired
    private InstockOrderDetailService instockOrderDetailService;
    @Resource
    private InstockOrderDetailMapper instockOrderDetailMapper;

    @Autowired
    public InstockOrderService(InstockOrderMapStruct instockOrderMapStruct, InstockOrderMapper instockOrderMapper) {
        this.instockOrderMapStruct = instockOrderMapStruct;
        this.instockOrderMapper = instockOrderMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InstockOrderDTO }
     * @return int
     */
    public int insert(InstockOrderDTO record) {
        InstockOrder instockOrder = instockOrderMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockOrder.setCreatedTime(new Date());
        instockOrder.setCreatedBy(userId);
        instockOrder.setLastModifiedBy(userId);
        instockOrder.setLastModifiedTime(new Date());

        int num = instockOrderMapper.insert(instockOrder);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<InstockOrderDTO> }
     * @return int
     */
    public int insertBatch(List<InstockOrderDTO> records) {
        List<InstockOrder> recordList = instockOrderMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = instockOrderMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link InstockOrderDTO }
     */
    public InstockOrderDTO selectByPrimaryKey(Integer id) {

        InstockOrder instockOrder = instockOrderMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(instockOrder, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return instockOrderMapStruct.toDto(instockOrder);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link InstockOrderDTO }
     * @return {@link List<InstockOrderDTO> }
     */
    public List<InstockOrderDTO> selectSelective(InstockOrderDTO record) {
        InstockOrder instockOrder = instockOrderMapStruct.toEntity(record);

        List<InstockOrder> instockOrderList = instockOrderMapper.select(instockOrder);
        return instockOrderMapStruct.toDto(instockOrderList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link InstockOrderDTO }
     * @return int
     */
    public int updateByPrimaryKey(InstockOrderDTO record) {
        InstockOrder instockOrder = instockOrderMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockOrder.setLastModifiedBy(userId);
        instockOrder.setLastModifiedTime(new Date());

        int num = instockOrderMapper.updateByPrimaryKey(instockOrder);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link InstockOrderDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(InstockOrderDTO record) {
        InstockOrder instockOrder = instockOrderMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockOrder.setLastModifiedBy(userId);
        instockOrder.setLastModifiedTime(new Date());

        int num = instockOrderMapper.updateByPrimaryKeySelective(instockOrder);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = instockOrderMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return instockOrderMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InstockOrderDTO> }
     */
    public GridReturnData<InstockOrderDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InstockOrderDTO> mGridReturnData = new GridReturnData<>();
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

        List<InstockOrder> list = instockOrderMapper.selectPage(map);

        PageInfo<InstockOrder> pageInfo = new PageInfo<>(list);
        PageInfo<InstockOrderDTO> pageInfoFinal = new PageInfo<>(instockOrderMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 生成入库计划单
     *
     * @param
     * @return
     */
    @Override
    public Result saveInStockData(InstockOrderDTO instockOrderDTO) {
        if (StringUtils.isBlank(instockOrderDTO.getOrderNo())) {
            return new Result(99, "参数缺失");
        }
        List<InstockOrderDetailDTO> instockOrderMaterialDtoList = instockOrderDTO.getInstockOrderDetailDTOList();
        //检查是否存在该订单
        InstockOrderDTO checkInstockOrder = instockOrderMapper.checkInstockOrder(instockOrderDTO.getOrderNo());
        if (checkInstockOrder == null) {
//            String tmpOrderNo = UUID.randomUUID().toString().replaceAll("-", "");
            //来源 上游，默认
            instockOrderDTO.setSrc(UPSYNCHRONOUS);
            //入库单insert
            this.insert(instockOrderDTO);

            //入库明细表insert
            if (instockOrderMaterialDtoList.size() > 0) {
                instockOrderDetailService.insertBatch(instockOrderMaterialDtoList);
            }
        } else {
            //已存在订单 更新
            this.updateByPrimaryKeySelective(instockOrderDTO);

            //入库明细表
            List<InstockOrderDetailDTO> insertInstockOrderMaterialList = new ArrayList<>();
            List<InstockOrderDetailDTO> updateInstockOrderMaterialList = new ArrayList<>();
            for (InstockOrderDetailDTO instockOrderMaterialDto : instockOrderMaterialDtoList) {
                InstockOrderDetail instockOrderDetail = instockOrderDetailMapper.selectInstockOrderDetail(instockOrderDTO.getOrderNo(), instockOrderMaterialDto.getSubOrderNo());
                if (instockOrderDetail.getId() == null || instockOrderDetail.getId() <= 0) {
                    insertInstockOrderMaterialList.add(instockOrderMaterialDto);
                } else {
                    if (instockOrderDetail.getInStorageNum().compareTo(new BigDecimal(0)) > 0) {
                        return new Result(99, "已经开始入库不可以更改入库数量");
                    }
                    updateInstockOrderMaterialList.add(instockOrderMaterialDto);
                }
            }
            //入库明细insert
            if (insertInstockOrderMaterialList.size() > 0) {
                instockOrderDetailService.insertBatch(insertInstockOrderMaterialList);
            }
            //入库明细update
            if (updateInstockOrderMaterialList.size() > 0) {
                for (InstockOrderDetailDTO instockOrderDetailDto : updateInstockOrderMaterialList) {
                    instockOrderDetailService.updateByPrimaryKeySelective(instockOrderDetailDto);
                }
            }
        }
        return new Result();
    }

    //入库单删除
    @Override
    public Result deleteInstockOrder(DeleteInstockOrderDTO deleteInstockOrderDTO) {
        //检查是否存在
        if (StringUtils.isBlank(deleteInstockOrderDTO.getOrderNo())) {
            return new Result(99, "参数缺失");
        }
        //循环检查明细存在已入库数量 不可删除
        List<String> subOrderNoList = deleteInstockOrderDTO.getSubOrderNo();
        for (String subOrderNo : subOrderNoList) {
            InstockOrderDetail instockOrderDetail = instockOrderDetailMapper.selectInstockOrderDetail(deleteInstockOrderDTO.getOrderNo(), subOrderNo);
            if (instockOrderDetail.getInStorageNum().compareTo(new BigDecimal(0)) > 0) {
                return new Result(99, "已经开始入库不可以删除");
            }
        }
        instockOrderDetailMapper.deleteInstockOrder(deleteInstockOrderDTO.getOrderNo(), subOrderNoList);
        return new Result();
    }

    /**
     * 获取入库单
     *
     * @param instockOrderConditionDto
     * @return
     */
    @Override
    public List<InstockOrderDTO> selectInstockOrder(InstockOrderConditionDto instockOrderConditionDto) {

        return instockOrderMapStruct.toDto(instockOrderMapper.selectInstockOrder(instockOrderConditionDto));
    }
}
