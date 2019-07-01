package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseWhArea;
import com.wisdom.iwcs.domain.base.dto.BaseWhAreaDTO;
import com.wisdom.iwcs.domain.system.SUser;
import com.wisdom.iwcs.domain.system.SUserWhArea;
import com.wisdom.iwcs.domain.system.dto.LoginDTO;
import com.wisdom.iwcs.mapper.base.BaseWhAreaMapper;
import com.wisdom.iwcs.mapper.system.SUserMapper;
import com.wisdom.iwcs.mapstruct.base.BaseWhAreaMapStruct;
import com.wisdom.iwcs.service.base.IBaseWhAreaService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class BaseWhAreaService implements IBaseWhAreaService {
    private final Logger logger = LoggerFactory.getLogger(BaseWhAreaService.class);

    private final BaseWhAreaMapper baseWhAreaMapper;

    private final BaseWhAreaMapStruct baseWhAreaMapStruct;

    @Autowired
    private SUserMapper sUserMapper;

    @Autowired
    public BaseWhAreaService(BaseWhAreaMapStruct baseWhAreaMapStruct, BaseWhAreaMapper baseWhAreaMapper) {
        this.baseWhAreaMapStruct = baseWhAreaMapStruct;
        this.baseWhAreaMapper = baseWhAreaMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseWhAreaDTO }
     * @return int
     */
    @Override
    public int insert(BaseWhAreaDTO record) {
        BaseWhArea baseWhArea = baseWhAreaMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWhArea.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseWhArea.setCreatedTime(new Date());
        baseWhArea.setCreatedBy(userId);
        baseWhArea.setLastModifiedBy(userId);
        baseWhArea.setLastModifiedTime(new Date());

        int num = baseWhAreaMapper.insert(baseWhArea);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseWhAreaDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseWhAreaDTO> records) {
        List<BaseWhArea> recordList = baseWhAreaMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseWhAreaMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseWhAreaDTO }
     */
    @Override
    public BaseWhAreaDTO selectByPrimaryKey(Integer id) {

        BaseWhArea baseWhArea = baseWhAreaMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseWhArea, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseWhAreaMapStruct.toDto(baseWhArea);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseWhAreaDTO }
     * @return {@link List<BaseWhAreaDTO> }
     */
    @Override
    public List<BaseWhAreaDTO> selectSelective(BaseWhAreaDTO record) {
        BaseWhArea baseWhArea = baseWhAreaMapStruct.toEntity(record);

        List<BaseWhArea> baseWhAreaList = baseWhAreaMapper.select(baseWhArea);
        return baseWhAreaMapStruct.toDto(baseWhAreaList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseWhAreaDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseWhAreaDTO record) {
        BaseWhArea baseWhArea = baseWhAreaMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWhArea.setLastModifiedBy(userId);
        baseWhArea.setLastModifiedTime(new Date());

        int num = baseWhAreaMapper.updateByPrimaryKey(baseWhArea);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseWhAreaDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseWhAreaDTO record) {
        BaseWhArea baseWhArea = baseWhAreaMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWhArea.setLastModifiedBy(userId);
        baseWhArea.setLastModifiedTime(new Date());

        int num = baseWhAreaMapper.updateByPrimaryKeySelective(baseWhArea);
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
        int num = baseWhAreaMapper.deleteByPrimaryKey(id);
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
        return baseWhAreaMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseWhAreaMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseWhAreaMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseWhAreaDTO> }
     */
    @Override
    public GridReturnData<BaseWhAreaDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseWhAreaDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseWhArea> list = baseWhAreaMapper.selectPage(map);

        PageInfo<BaseWhArea> pageInfo = new PageInfo<>(list);
        PageInfo<BaseWhAreaDTO> pageInfoFinal = new PageInfo<>(baseWhAreaMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    @Override
    public Result checkWhAreaAndUser(LoginDTO loginDTO) {
        //先判断库区是否存在
        BaseWhArea baseWhArea= baseWhAreaMapper.selectByAreaCodeAndDeleteFlag(loginDTO.getAreaCode(),DeleteFlagEnum.NOT_DELETED.getStatus());
        Preconditions.checkBusinessError(baseWhArea == null,"库区不存在");

        //判断用户是否存在
        Optional<SUser> userFromDatabase = Optional.ofNullable(sUserMapper.findOneByUserNameAndStatus(loginDTO.getUsername(), "1"));
        Preconditions.checkBusinessError(!userFromDatabase.isPresent(),"该用户不存在");
        SUser sUser = userFromDatabase.get();
        Preconditions.checkBusinessError(sUser == null,"该用户不存在");

        //判断该库存是否存在该用户
        SUserWhArea sUserWhArea = baseWhAreaMapper.selectAreaUser(loginDTO.getAreaCode(),sUser.getId());
        Preconditions.checkBusinessError(sUserWhArea == null,"库区("+baseWhArea.getAreaName()+")不存在该用户");

        return new Result();
    }
}
