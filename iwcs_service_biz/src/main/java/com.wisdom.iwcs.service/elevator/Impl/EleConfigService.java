package com.wisdom.iwcs.service.elevator.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.elevator.EleConfig;
import com.wisdom.iwcs.domain.elevator.dto.EleConfigDTO;
import com.wisdom.iwcs.mapper.elevator.EleConfigMapper;
import com.wisdom.iwcs.mapstruct.elevator.EleConfigMapStruct;
import com.wisdom.iwcs.service.elevator.IEleConfigService;
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
public class EleConfigService implements IEleConfigService {

    private final Logger logger = LoggerFactory.getLogger(EleConfigService.class);

    private final EleConfigMapper eleConfigMapper;

    private final EleConfigMapStruct eleConfigMapStruct;

    @Autowired
    public EleConfigService(EleConfigMapStruct eleConfigMapStruct, EleConfigMapper eleConfigMapper) {
        this.eleConfigMapStruct = eleConfigMapStruct;
        this.eleConfigMapper = eleConfigMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link EleConfigDTO }
     *
     * @return int
     */
    public int insert(EleConfigDTO record) {
        EleConfig eleConfigDTO = eleConfigMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        eleConfigDTO.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//        eleConfigDTO.setCreatedTime(new Date());
//        eleConfigDTO.setCreatedBy(userId);
//        eleConfigDTO.setLastModifiedBy(userId);
//        eleConfigDTO.setLastModifiedTime(new Date());

        int num = eleConfigMapper.insert(eleConfigDTO);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<EleConfigDTO> }
     *
     * @return int
     */
    public int insertBatch(List<EleConfigDTO> records) {
        List<EleConfig> recordList = eleConfigMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
//        recordList.forEach(record -> {
//            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//            record.setCreatedTime(new Date());
//            record.setCreatedBy(userId);
//            record.setLastModifiedBy(userId);
//            record.setLastModifiedTime(new Date());
//        });

        int num = eleConfigMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link EleConfigDTO }
     */
    public EleConfigDTO selectByPrimaryKey(Integer id) {

        EleConfig eleConfigDTO = eleConfigMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(eleConfigDTO, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return eleConfigMapStruct.toDto(eleConfigDTO);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link EleConfigDTO }
     *
     * @return {@link List<EleConfigDTO> }
     */
    public List<EleConfigDTO> selectSelective(EleConfigDTO record) {
        EleConfig eleConfigDTO = eleConfigMapStruct.toEntity(record);

        List<EleConfig> eleConfigDTOList = eleConfigMapper.select(eleConfigDTO);
        return eleConfigMapStruct.toDto(eleConfigDTOList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link EleConfigDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(EleConfigDTO record) {
        EleConfig eleConfigDTO = eleConfigMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        eleConfigDTO.setLastModifiedBy(userId);
//        eleConfigDTO.setLastModifiedTime(new Date());

        int num = eleConfigMapper.updateByPrimaryKey(eleConfigDTO);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link EleConfigDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(EleConfigDTO record) {
        EleConfig eleConfigDTO = eleConfigMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        eleConfigDTO.setLastModifiedBy(userId);
//        eleConfigDTO.setLastModifiedTime(new Date());

        int num = eleConfigMapper.updateByPrimaryKeySelective(eleConfigDTO);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     *
     * @param id {@link Integer }
     *
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = eleConfigMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     *
     * @param id {@link Integer }
     *
     * @return int
     */
//    public int deleteLogicByPrimaryKey(Integer id) {
//        return eleConfigMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
        return eleConfigMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
//    public int deleteMoreLogic(List<String> ids){
//        return eleConfigMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<EleConfigDTO> }
     */
    public GridReturnData<EleConfigDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<EleConfigDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if(gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null){
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<EleConfig> list = eleConfigMapper.selectPage(map);

        PageInfo<EleConfig> pageInfo = new PageInfo<>(list);
        PageInfo<EleConfigDTO> pageInfoFinal = new PageInfo<>(eleConfigMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
