package com.wisdom.service.outstock.outstockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.outstock.MatConfigRelationMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.outstock.MatConfigRelation;
import com.wisdom.iwcs.domain.outstock.dto.MatConfigRelationDTO;
import com.wisdom.iwcs.mapper.outstock.MatConfigRelationMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.outstock.IMatConfigRelationService;
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
public class MatConfigRelationService implements IMatConfigRelationService {
    private final Logger logger = LoggerFactory.getLogger(MatConfigRelationService.class);

    private final MatConfigRelationMapper matConfigRelationMapper;

    private final MatConfigRelationMapStruct matConfigRelationMapStruct;

    @Autowired
    public MatConfigRelationService(MatConfigRelationMapStruct matConfigRelationMapStruct, MatConfigRelationMapper matConfigRelationMapper) {
        this.matConfigRelationMapStruct = matConfigRelationMapStruct;
        this.matConfigRelationMapper = matConfigRelationMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link MatConfigRelationDTO }
     * @return int
     */
    @Override
    public int insert(MatConfigRelationDTO record) {
        MatConfigRelation matConfigRelation = matConfigRelationMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        matConfigRelation.setCreatedTime(new Date());

        int num = matConfigRelationMapper.insert(matConfigRelation);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<MatConfigRelationDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<MatConfigRelationDTO> records) {
        List<MatConfigRelation> recordList = matConfigRelationMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = matConfigRelationMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link MatConfigRelationDTO }
     */
    @Override
    public MatConfigRelationDTO selectByPrimaryKey(Integer id) {

        MatConfigRelation matConfigRelation = matConfigRelationMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(matConfigRelation, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return matConfigRelationMapStruct.toDto(matConfigRelation);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link MatConfigRelationDTO }
     * @return {@link List<MatConfigRelationDTO> }
     */
    @Override
    public List<MatConfigRelationDTO> selectSelective(MatConfigRelationDTO record) {
        MatConfigRelation matConfigRelation = matConfigRelationMapStruct.toEntity(record);

        List<MatConfigRelation> matConfigRelationList = matConfigRelationMapper.select(matConfigRelation);
        return matConfigRelationMapStruct.toDto(matConfigRelationList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link MatConfigRelationDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(MatConfigRelationDTO record) {
        MatConfigRelation matConfigRelation = matConfigRelationMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        int num = matConfigRelationMapper.updateByPrimaryKey(matConfigRelation);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link MatConfigRelationDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(MatConfigRelationDTO record) {
        MatConfigRelation matConfigRelation = matConfigRelationMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = matConfigRelationMapper.updateByPrimaryKeySelective(matConfigRelation);
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
        int num = matConfigRelationMapper.deleteByPrimaryKey(id);
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
        return matConfigRelationMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<MatConfigRelationDTO> }
     */
    @Override
    public GridReturnData<MatConfigRelationDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<MatConfigRelationDTO> mGridReturnData = new GridReturnData<>();
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

        List<MatConfigRelation> list = matConfigRelationMapper.selectPage(map);

        PageInfo<MatConfigRelation> pageInfo = new PageInfo<>(list);
        PageInfo<MatConfigRelationDTO> pageInfoFinal = new PageInfo<>(matConfigRelationMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
