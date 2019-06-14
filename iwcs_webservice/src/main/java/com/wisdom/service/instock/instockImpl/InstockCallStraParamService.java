package com.wisdom.service.instock.instockImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.instock.InstockCallStraParamMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.instock.InstockCallStraParam;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraParamDTO;
import com.wisdom.iwcs.mapper.instock.InstockCallStraParamMapper;
import com.wisdom.security.SecurityUtils;
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
public class InstockCallStraParamService {
    private final Logger logger = LoggerFactory.getLogger(InstockCallStraParamService.class);

    private final InstockCallStraParamMapper instockCallStraParamMapper;

    private final InstockCallStraParamMapStruct instockCallStraParamMapStruct;

    @Autowired
    public InstockCallStraParamService(InstockCallStraParamMapStruct instockCallStraParamMapStruct, InstockCallStraParamMapper instockCallStraParamMapper) {
        this.instockCallStraParamMapStruct = instockCallStraParamMapStruct;
        this.instockCallStraParamMapper = instockCallStraParamMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InstockCallStraParamDTO }
     * @return int
     */
    public int insert(InstockCallStraParamDTO record) {
        InstockCallStraParam instockCallStraParam = instockCallStraParamMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = instockCallStraParamMapper.insert(instockCallStraParam);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<InstockCallStraParamDTO> }
     * @return int
     */
    public int insertBatch(List<InstockCallStraParamDTO> records) {
        List<InstockCallStraParam> recordList = instockCallStraParamMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = instockCallStraParamMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link InstockCallStraParamDTO }
     */
    public InstockCallStraParamDTO selectByPrimaryKey(Integer id) {

        InstockCallStraParam instockCallStraParam = instockCallStraParamMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(instockCallStraParam, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return instockCallStraParamMapStruct.toDto(instockCallStraParam);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link InstockCallStraParamDTO }
     * @return {@link List<InstockCallStraParamDTO> }
     */
    public List<InstockCallStraParamDTO> selectSelective(InstockCallStraParamDTO record) {
        InstockCallStraParam instockCallStraParam = instockCallStraParamMapStruct.toEntity(record);

        List<InstockCallStraParam> instockCallStraParamList = instockCallStraParamMapper.select(instockCallStraParam);
        return instockCallStraParamMapStruct.toDto(instockCallStraParamList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link InstockCallStraParamDTO }
     * @return int
     */
    public int updateByPrimaryKey(InstockCallStraParamDTO record) {
        InstockCallStraParam instockCallStraParam = instockCallStraParamMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = instockCallStraParamMapper.updateByPrimaryKey(instockCallStraParam);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link InstockCallStraParamDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(InstockCallStraParamDTO record) {
        InstockCallStraParam instockCallStraParam = instockCallStraParamMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = instockCallStraParamMapper.updateByPrimaryKeySelective(instockCallStraParam);
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
        int num = instockCallStraParamMapper.deleteByPrimaryKey(id);
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
        return instockCallStraParamMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InstockCallStraParamDTO> }
     */
    public GridReturnData<InstockCallStraParamDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InstockCallStraParamDTO> mGridReturnData = new GridReturnData<>();
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

        List<InstockCallStraParam> list = instockCallStraParamMapper.selectPage(map);

        PageInfo<InstockCallStraParam> pageInfo = new PageInfo<>(list);
        PageInfo<InstockCallStraParamDTO> pageInfoFinal = new PageInfo<>(instockCallStraParamMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
