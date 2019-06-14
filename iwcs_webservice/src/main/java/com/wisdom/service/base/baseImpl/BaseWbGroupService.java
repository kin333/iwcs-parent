package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.wisdom.controller.mapstruct.base.BaseWbGroupMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseWbGroup;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDTO;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDetailDTO;
import com.wisdom.iwcs.mapper.base.BaseWbGroupDetailMapper;
import com.wisdom.iwcs.mapper.base.BaseWbGroupMapper;
import com.wisdom.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class BaseWbGroupService {
    private final Logger logger = LoggerFactory.getLogger(BaseWbGroupService.class);

    private final BaseWbGroupMapper baseWbGroupMapper;

    private final BaseWbGroupMapStruct baseWbGroupMapStruct;
    @Resource
    private BaseWbGroupDetailMapper baseWbGroupDetailMapper;

    @Autowired
    private com.wisdom.service.base.IBaseWbGroupDetailService IBaseWbGroupDetailService;

    @Autowired
    public BaseWbGroupService(BaseWbGroupMapStruct baseWbGroupMapStruct, BaseWbGroupMapper baseWbGroupMapper) {
        this.baseWbGroupMapStruct = baseWbGroupMapStruct;
        this.baseWbGroupMapper = baseWbGroupMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseWbGroupDTO }
     * @return int
     */
    public int insert(BaseWbGroupDTO record) {
        BaseWbGroup baseWbGroup = baseWbGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWbGroup.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseWbGroup.setCreatedTime(new Date());
        baseWbGroup.setCreatedBy(userId);
        baseWbGroup.setLastModifiedBy(userId);
        baseWbGroup.setLastModifiedTime(new Date());

        int num = baseWbGroupMapper.insert(baseWbGroup);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseWbGroupDTO> }
     * @return int
     */
    public int insertBatch(List<BaseWbGroupDTO> records) {
        List<BaseWbGroup> recordList = baseWbGroupMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseWbGroupMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseWbGroupDTO }
     */
    public BaseWbGroupDTO selectByPrimaryKey(Integer id) {

        BaseWbGroup baseWbGroup = baseWbGroupMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseWbGroup, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseWbGroupMapStruct.toDto(baseWbGroup);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseWbGroupDTO }
     * @return {@link List<BaseWbGroupDTO> }
     */
    public List<BaseWbGroupDTO> selectSelective(BaseWbGroupDTO record) {
        BaseWbGroup baseWbGroup = baseWbGroupMapStruct.toEntity(record);

        List<BaseWbGroup> baseWbGroupList = baseWbGroupMapper.select(baseWbGroup);
        return baseWbGroupMapStruct.toDto(baseWbGroupList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseWbGroupDTO }
     * @return int
     */
    public int updateByPrimaryKey(BaseWbGroupDTO record) {
        BaseWbGroup baseWbGroup = baseWbGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWbGroup.setLastModifiedBy(userId);
        baseWbGroup.setLastModifiedTime(new Date());

        int num = baseWbGroupMapper.updateByPrimaryKey(baseWbGroup);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseWbGroupDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(BaseWbGroupDTO record) {
        BaseWbGroup baseWbGroup = baseWbGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWbGroup.setLastModifiedBy(userId);
        baseWbGroup.setLastModifiedTime(new Date());

        int num = baseWbGroupMapper.updateByPrimaryKeySelective(baseWbGroup);
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
        int num = baseWbGroupMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    public int deleteLogicByPrimaryKey(Integer id) {
        return baseWbGroupMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return baseWbGroupMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMoreLogic(List<String> ids) {
        return baseWbGroupMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseWbGroupDTO> }
     */
    public GridReturnData<BaseWbGroupDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseWbGroupDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseWbGroup> list = baseWbGroupMapper.selectPage(map);

        PageInfo<BaseWbGroup> pageInfo = new PageInfo<>(list);
        PageInfo<BaseWbGroupDTO> pageInfoFinal = new PageInfo<>(baseWbGroupMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 新增工作台组
     */
    public int saveWbGroupAndWbGropDetail(BaseWbGroupDTO record) {
        BaseWbGroup baseWbGroup = baseWbGroupMapStruct.toEntity(record);
        //校验 互斥校验是否已经存在，备货校验是否存在并校验互斥
//        if (baseWbGroup.getGroupTypeCode().equals("metux")){
//
//        }else{
//
//        }

        Integer userId = SecurityUtils.getCurrentUserId();
        if (StringUtil.isEmpty(baseWbGroup.getGroupCode())) {
            String reqcode = UUID.randomUUID().toString().replace("-", "");
            baseWbGroup.setGroupCode(reqcode);
        }
        baseWbGroup.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseWbGroup.setCreatedTime(new Date());
        baseWbGroup.setCreatedBy(userId);

        List<BaseWbGroupDetailDTO> baseWbGroupDetailDTOS = record.getBaseWbGroupDetailDTOList();
        baseWbGroupDetailDTOS.forEach(recordDetail -> {
            recordDetail.setGroupCode(baseWbGroup.getGroupCode());
        });

        IBaseWbGroupDetailService.insertBatch(baseWbGroupDetailDTOS);

        int num = baseWbGroupMapper.insert(baseWbGroup);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据工作台组号查询
     */
    public BaseWbGroupDTO selectWbGroup(String groupCode) {
        BaseWbGroupDTO baseWbGroupDTO = baseWbGroupMapper.selectWbGroupByGroupCode(groupCode);
        List<BaseWbGroupDetailDTO> baseWbGroupDetailDTO = baseWbGroupDetailMapper.selectWbGroupDetailByGroupCode(groupCode);
        baseWbGroupDTO.setBaseWbGroupDetailDTOList(baseWbGroupDetailDTO);
        return baseWbGroupDTO;
    }
}
