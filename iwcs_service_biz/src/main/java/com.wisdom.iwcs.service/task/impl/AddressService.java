package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.Address;
import com.wisdom.iwcs.domain.task.dto.AddressDTO;
import com.wisdom.iwcs.mapper.task.AddressMapper;
import com.wisdom.iwcs.mapstruct.task.AddressMapStruct;
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

import com.wisdom.iwcs.common.utils.exception.Preconditions;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddressService {
    private final Logger logger = LoggerFactory.getLogger(AddressService.class);

    private final AddressMapper addressMapper;

    private final AddressMapStruct addressMapStruct;

    @Autowired
    public AddressService(AddressMapStruct addressMapStruct, AddressMapper addressMapper) {
        this.addressMapStruct = addressMapStruct;
        this.addressMapper = addressMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link AddressDTO }
     *
     * @return int
     */
    public int insert(AddressDTO record) {
        Address address = addressMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = addressMapper.insert(address);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<AddressDTO> }
     *
     * @return int
     */
    public int insertBatch(List<AddressDTO> records) {
        List<Address> recordList = addressMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
        });

        int num = addressMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link AddressDTO }
     */
    public AddressDTO selectByPrimaryKey(Integer id) {

        Address address = addressMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(address, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return addressMapStruct.toDto(address);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link AddressDTO }
     *
     * @return {@link List<AddressDTO> }
     */
    public List<AddressDTO> selectSelective(AddressDTO record) {
        Address address = addressMapStruct.toEntity(record);

        List<Address> addressList = addressMapper.select(address);
        return addressMapStruct.toDto(addressList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link AddressDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(AddressDTO record) {
        Address address = addressMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = addressMapper.updateByPrimaryKey(address);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link AddressDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(AddressDTO record) {
        Address address = addressMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = addressMapper.updateByPrimaryKeySelective(address);
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
        int num = addressMapper.deleteByPrimaryKey(id);
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
//        return addressMapper.deleteLogicByPrimaryKey(id);
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
        return addressMapper.deleteByIds(String.join(",", ids));
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
//        return addressMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<AddressDTO> }
     */
    public GridReturnData<AddressDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<AddressDTO> mGridReturnData = new GridReturnData<>();
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

        List<Address> list = addressMapper.selectPage(map);

        PageInfo<Address> pageInfo = new PageInfo<>(list);
        PageInfo<AddressDTO> pageInfoFinal = new PageInfo<>(addressMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
