package com.wisdom.iwcs.service.log.logImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.log.InterfaceLog;
import com.wisdom.iwcs.domain.log.dto.InterfaceLogDTO;
import com.wisdom.iwcs.mapper.log.InterfaceLogMapper;
import com.wisdom.iwcs.mapstruct.log.InterfaceLogMapStruct;
import com.wisdom.iwcs.service.log.IInterfaceLogService;
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
public class InterfaceLogService implements IInterfaceLogService {
    private final Logger logger = LoggerFactory.getLogger(InterfaceLogService.class);

    private final InterfaceLogMapper interfaceLogMapper;

    private final InterfaceLogMapStruct interfaceLogMapStruct;

    @Autowired
    public InterfaceLogService(InterfaceLogMapStruct interfaceLogMapStruct, InterfaceLogMapper interfaceLogMapper) {
        this.interfaceLogMapStruct = interfaceLogMapStruct;
        this.interfaceLogMapper = interfaceLogMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link InterfaceLogDTO }
     * @return int
     */
    @Override
    public int insert(InterfaceLogDTO record) {
        InterfaceLog interfaceLog = interfaceLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        interfaceLog.setCreatedTime(new Date());

        int num = interfaceLogMapper.insert(interfaceLog);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<InterfaceLogDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<InterfaceLogDTO> records) {
        List<InterfaceLog> recordList = interfaceLogMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
        });

        int num = interfaceLogMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link InterfaceLogDTO }
     */
    @Override
    public InterfaceLogDTO selectByPrimaryKey(Integer id) {

        InterfaceLog interfaceLog = interfaceLogMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(interfaceLog, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return interfaceLogMapStruct.toDto(interfaceLog);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link InterfaceLogDTO }
     * @return {@link List<InterfaceLogDTO> }
     */
    @Override
    public List<InterfaceLogDTO> selectSelective(InterfaceLogDTO record) {
        InterfaceLog interfaceLog = interfaceLogMapStruct.toEntity(record);

        List<InterfaceLog> interfaceLogList = interfaceLogMapper.select(interfaceLog);
        return interfaceLogMapStruct.toDto(interfaceLogList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link InterfaceLogDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(InterfaceLogDTO record) {
        InterfaceLog interfaceLog = interfaceLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        int num = interfaceLogMapper.updateByPrimaryKey(interfaceLog);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link InterfaceLogDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(InterfaceLogDTO record) {
        InterfaceLog interfaceLog = interfaceLogMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = interfaceLogMapper.updateByPrimaryKeySelective(interfaceLog);
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
        int num = interfaceLogMapper.deleteByPrimaryKey(id);
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
        return interfaceLogMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<InterfaceLogDTO> }
     */
    @Override
    public GridReturnData<InterfaceLogDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<InterfaceLogDTO> mGridReturnData = new GridReturnData<>();
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

        List<InterfaceLog> list = interfaceLogMapper.selectPage(map);

        PageInfo<InterfaceLog> pageInfo = new PageInfo<>(list);
        PageInfo<InterfaceLogDTO> pageInfoFinal = new PageInfo<>(interfaceLogMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
