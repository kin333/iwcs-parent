package com.wisdom.iwcs.service.instock.instockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.instock.InstockOrderDetail;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailConditionDTO;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDetailDTO;
import com.wisdom.iwcs.mapper.instock.InstockOrderDetailMapper;
import com.wisdom.iwcs.mapstruct.instock.InstockOrderDetailMapStruct;
import com.wisdom.iwcs.service.instock.IInstockOrderDetailService;
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
public class InstockOrderDetailService implements IInstockOrderDetailService {
    private final Logger logger = LoggerFactory.getLogger(InstockOrderDetailService.class);

    private final InstockOrderDetailMapper instockOrderDetailMapper;

    private final InstockOrderDetailMapStruct instockOrderDetailMapStruct;

    @Autowired
    public InstockOrderDetailService(InstockOrderDetailMapStruct instockOrderDetailMapStruct, InstockOrderDetailMapper instockOrderDetailMapper) {
        this.instockOrderDetailMapStruct = instockOrderDetailMapStruct;
        this.instockOrderDetailMapper = instockOrderDetailMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InstockOrderDetailDTO }
     * @return int
     */
    @Override
    public int insert(InstockOrderDetailDTO record) {
        InstockOrderDetail instockOrderDetail = instockOrderDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockOrderDetail.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        instockOrderDetail.setCreatedTime(new Date());
        instockOrderDetail.setCreatedBy(userId);
        instockOrderDetail.setLastModifiedBy(userId);
        instockOrderDetail.setLastModifiedTime(new Date());

        int num = instockOrderDetailMapper.insert(instockOrderDetail);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<InstockOrderDetailDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<InstockOrderDetailDTO> records) {
        List<InstockOrderDetail> recordList = instockOrderDetailMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = instockOrderDetailMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link InstockOrderDetailDTO }
     */
    @Override
    public InstockOrderDetailDTO selectByPrimaryKey(Integer id) {

        InstockOrderDetail instockOrderDetail = instockOrderDetailMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(instockOrderDetail, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return instockOrderDetailMapStruct.toDto(instockOrderDetail);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link InstockOrderDetailDTO }
     * @return {@link List<InstockOrderDetailDTO> }
     */
    @Override
    public List<InstockOrderDetailDTO> selectSelective(InstockOrderDetailDTO record) {
        InstockOrderDetail instockOrderDetail = instockOrderDetailMapStruct.toEntity(record);

        List<InstockOrderDetail> instockOrderDetailList = instockOrderDetailMapper.select(instockOrderDetail);
        return instockOrderDetailMapStruct.toDto(instockOrderDetailList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link InstockOrderDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(InstockOrderDetailDTO record) {
        InstockOrderDetail instockOrderDetail = instockOrderDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockOrderDetail.setLastModifiedBy(userId);
        instockOrderDetail.setLastModifiedTime(new Date());

        int num = instockOrderDetailMapper.updateByPrimaryKey(instockOrderDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link InstockOrderDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(InstockOrderDetailDTO record) {
        InstockOrderDetail instockOrderDetail = instockOrderDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        instockOrderDetail.setLastModifiedBy(userId);
        instockOrderDetail.setLastModifiedTime(new Date());

        int num = instockOrderDetailMapper.updateByPrimaryKeySelective(instockOrderDetail);
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
        int num = instockOrderDetailMapper.deleteByPrimaryKey(id);
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
        return instockOrderDetailMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return instockOrderDetailMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return instockOrderDetailMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InstockOrderDetailDTO> }
     */
    @Override
    public GridReturnData<InstockOrderDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InstockOrderDetailDTO> mGridReturnData = new GridReturnData<>();
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

        List<InstockOrderDetail> list = instockOrderDetailMapper.selectPage(map);

        PageInfo<InstockOrderDetail> pageInfo = new PageInfo<>(list);
        PageInfo<InstockOrderDetailDTO> pageInfoFinal = new PageInfo<>(instockOrderDetailMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 根据库主单单号获取入库详情
     *
     * @param orderNo
     * @return
     */
    @Override
    public List<InstockOrderDetailDTO> selectInstockOrderDetail(String orderNo) {
        if (orderNo == null || orderNo.isEmpty()) {
            throw new BusinessException("缺少入库单单号");
        }

        return instockOrderDetailMapStruct.toDto(instockOrderDetailMapper.selectInstockDetail(orderNo));
    }

    /**
     * 查询所有订单的入库单详情
     *
     * @param instockOrderDetailConditionDTO
     * @return
     */
    @Override
    public List<InstockOrderDetailDTO> selectInstockOrderAllDetail(InstockOrderDetailConditionDTO instockOrderDetailConditionDTO) {

        List<InstockOrderDetail> instockOrderDetailDTOList = instockOrderDetailMapper.selectInstockOrderAllDetail(instockOrderDetailConditionDTO);

        return instockOrderDetailMapStruct.toDto(instockOrderDetailDTOList);
    }
}
