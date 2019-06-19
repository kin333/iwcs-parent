package com.wisdom.iwcs.service.instock.instockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.instock.InstockRecordDetail;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordDetailDTO;
import com.wisdom.iwcs.mapper.instock.InstockRecordDetailMapper;
import com.wisdom.iwcs.mapstruct.instock.InstockRecordDetailMapStruct;
import com.wisdom.iwcs.service.instock.IInstockRecordDetailService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class InstockRecordDetailService implements IInstockRecordDetailService {
    private final Logger logger = LoggerFactory.getLogger(InstockRecordDetailService.class);

    private final InstockRecordDetailMapper instockRecordDetailMapper;

    private final InstockRecordDetailMapStruct instockRecordDetailMapStruct;

    @Autowired
    public InstockRecordDetailService(InstockRecordDetailMapStruct instockRecordDetailMapStruct, InstockRecordDetailMapper instockRecordDetailMapper) {
        this.instockRecordDetailMapStruct = instockRecordDetailMapStruct;
        this.instockRecordDetailMapper = instockRecordDetailMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InstockRecordDetailDTO }
     * @return int
     */
    @Override
    public int insert(InstockRecordDetailDTO record) {
        InstockRecordDetail instockRecordDetail = instockRecordDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = instockRecordDetailMapper.insert(instockRecordDetail);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<InstockRecordDetailDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<InstockRecordDetailDTO> records) {
        List<InstockRecordDetail> recordList = instockRecordDetailMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();


        int num = instockRecordDetailMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link InstockRecordDetailDTO }
     */
    @Override
    public InstockRecordDetailDTO selectByPrimaryKey(Integer id) {

        InstockRecordDetail instockRecordDetail = instockRecordDetailMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(instockRecordDetail, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return instockRecordDetailMapStruct.toDto(instockRecordDetail);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link InstockRecordDetailDTO }
     * @return {@link List<InstockRecordDetailDTO> }
     */
    @Override
    public List<InstockRecordDetailDTO> selectSelective(InstockRecordDetailDTO record) {
        InstockRecordDetail instockRecordDetail = instockRecordDetailMapStruct.toEntity(record);

        List<InstockRecordDetail> instockRecordDetailList = instockRecordDetailMapper.select(instockRecordDetail);
        return instockRecordDetailMapStruct.toDto(instockRecordDetailList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link InstockRecordDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(InstockRecordDetailDTO record) {
        InstockRecordDetail instockRecordDetail = instockRecordDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = instockRecordDetailMapper.updateByPrimaryKey(instockRecordDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link InstockRecordDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(InstockRecordDetailDTO record) {
        InstockRecordDetail instockRecordDetail = instockRecordDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = instockRecordDetailMapper.updateByPrimaryKeySelective(instockRecordDetail);
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
        int num = instockRecordDetailMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return instockRecordDetailMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InstockRecordDetailDTO> }
     */
    @Override
    public GridReturnData<InstockRecordDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InstockRecordDetailDTO> mGridReturnData = new GridReturnData<>();
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

        List<InstockRecordDetail> list = instockRecordDetailMapper.selectPage(map);

        PageInfo<InstockRecordDetail> pageInfo = new PageInfo<>(list);
        PageInfo<InstockRecordDetailDTO> pageInfoFinal = new PageInfo<>(instockRecordDetailMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 根据入库记录id，获取入库记录详情
     *
     * @param instockRecordId
     * @return
     */
    @Override
    public List<InstockRecordDetailDTO> selectRecordDetailByRecordId(Integer instockRecordId) {
        if (instockRecordId == null || instockRecordId <= 0) {
            throw new BusinessException("缺少入库记录ID");
        }
        List<InstockRecordDetail> instockRecordDetailDTOList = instockRecordDetailMapper.selectRecordDetail(instockRecordId);
        return instockRecordDetailMapStruct.toDto(instockRecordDetailDTOList);
    }
}
