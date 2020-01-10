package com.wisdom.iwcs.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.Dictionary;
import com.wisdom.iwcs.domain.system.dto.DictionaryDto;
import com.wisdom.iwcs.mapper.system.DictionaryMapper;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by zhanglisen on 2016/12/28.
 */
@Service
@Transactional
public class DictionaryService {

    private final Logger log = LoggerFactory.getLogger(DictionaryService.class);

    @Autowired
    private DictionaryMapper dictionaryMapper;


    public Result deleteByPrimaryKey(Integer id) {
        Integer count = dictionaryMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            return new Result(200, "操作成功");
        }
        return new Result(200, "操作失败");
    }

    public Result insert(Dictionary dictionary) {
        try {
//            Integer user_id = SecurityUtils.getCurrentUserId();
//            if (dictionary.getDictName() != null || dictionary.getDictValue() != null) {
//                List<Dictionary> dics = dictionaryMapper.selectByDictType(dictionary.getDictType());
//                Dictionary dictionary1 = dics.get(0);
//                if (dics.size() == 1 && dictionary1.getDictName() == null && dictionary1.getDictValue() == null) {
//                    dictionary1.setDictName(dictionary.getDictName());
//                    dictionary1.setDictValue(dictionary.getDictValue());
//                    dictionary.setLastModifiedBy(user_id);
//                    dictionary.setLastModifiedTime(new Date().getTime());
//                    dictionaryMapper.updateByPrimaryKeySelective(dictionary1);
//                }
//            } else  {
//
//            }
            Integer user_id = SecurityUtils.getCurrentUserId();
            dictionary.setStatus((byte) 1);
            dictionary.setCreatedBy(user_id);
            dictionary.setCreatedTime(new Date().getTime());
            dictionary.setLastModifiedBy(user_id);
            dictionary.setLastModifiedTime(new Date().getTime());
            dictionary.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            dictionaryMapper.insertSelective(dictionary);
            return new Result(200, "增加成功", dictionary);
        } catch (Exception e) {
            return new Result(200, "增加失败", dictionary);
        }
    }

    public Result insertSelective(Dictionary dictionary) {
        try {
            dictionaryMapper.insertSelective(dictionary);
            return new Result(200, "添加成功", dictionary);
        } catch (Exception e) {
            return new Result(200, "添加失败", dictionary);
        }
    }

    public Result selectByPrimaryKey(Integer id) {
        Dictionary dictionary = dictionaryMapper.selectByPrimaryKey(id);
        if (dictionary != null) {
            return new Result(200, "查询成功", dictionary);
        }
        return new Result(200, "查询失败", dictionary);
    }

    public Result updateByPrimaryKeySelective(Dictionary dictionary) {
        try {
            Integer user_id = SecurityUtils.getCurrentUserId();
            dictionary.setLastModifiedBy(user_id);
            dictionary.setLastModifiedTime(new Date().getTime());
            dictionaryMapper.updateByPrimaryKeySelective(dictionary);
            return new Result(200, "更新成功", dictionary);
        } catch (Exception e) {
            return new Result(200, "更新失败", dictionary);
        }
    }

    public Result updateByPrimaryKey(Dictionary dictionary) {
        try {
            Integer user_id = SecurityUtils.getCurrentUserId();
            dictionary.setLastModifiedBy(user_id);
            dictionaryMapper.updateByPrimaryKey(dictionary);
            return new Result(200, "更新成功", dictionary);
        } catch (Exception e) {
            return new Result(200, "更新失败", dictionary);
        }
    }

    public Result selectAll() {
        try {
            List dictionaryList = dictionaryMapper.selectAll();
            return new Result(200, "查询所有成功", dictionaryList);
        } catch (Exception e) {
            return new Result(200, "查询所有失败");
        }
    }

    public Result selectByDictTypePager(GridPageRequest gridPageRequest, String dictType) {
        if (gridPageRequest.getSortList() == null) {
        } else {
            gridPageRequest.getSortList().forEach(gridSort -> {
                String sortkey = FieldMap.fieldMap.get(gridSort.getSortKey());
                if (sortkey != null) {
                    gridSort.setSortKey(sortkey);
                }
            });
        }
        String sortMybatisString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMybatisString);
        Map map = new HashMap();
        if (gridPageRequest.getSearchKey() != null && !gridPageRequest.getSearchKey().equals("")) {
            String searchKey = "%" + gridPageRequest.getSearchKey() + "%";
            if (searchKey.equals("%是%")) {
                map.put("status", 1);
            } else if (searchKey.equals("%否%")) {
                map.put("status", 0);
            } else {
                map.put("searchKey", searchKey);
            }
        }
        map.put("dictType", dictType);
        List<DictionaryDto> dictionaryList = dictionaryMapper.selectByDictTypePager(map);
        Byte byte1 = new Byte("0");
        Byte byte2 = new Byte("1");
        for (DictionaryDto dictionaryDto : dictionaryList) {
            if (dictionaryDto.getLastModifiedTime() != null) {
                dictionaryDto.setLastModifiedTimeStr(GZDateUtil.getStampToDate(dictionaryDto.getLastModifiedTime(), YZConstants.DateFormat.DATE_FORMAT));
            }
            if (dictionaryDto.getCreatedTime() != null) {
                dictionaryDto.setCreatedTimeStr(GZDateUtil.getStampToDate(dictionaryDto.getCreatedTime(), YZConstants.DateFormat.DATE_FORMAT));
            }
            if (dictionaryDto.getStatus() != null) {
                if (dictionaryDto.getStatus().equals(byte1)) {//0为总公司 1为分公司
                    dictionaryDto.setStatusStr("否");
                } else if (dictionaryDto.getStatus().equals(byte2)) {
                    dictionaryDto.setStatusStr("是");
                }
            }
        }

        PageInfo<DictionaryDto> pageInfo = new PageInfo<DictionaryDto>(dictionaryList);
        GridReturnData<DictionaryDto> gridReturnData = new GridReturnData<>();
        gridReturnData.setPageInfo(pageInfo);
        return new Result(200, "分页查询成功", gridReturnData);
    }

    public GridReturnData<DictionaryDto> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<DictionaryDto> mGridReturnData = new GridReturnData<>();
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

        List<DictionaryDto> list = dictionaryMapper.selectPage(map);

        PageInfo<DictionaryDto> pageInfo = new PageInfo<>(list);
        PageInfo<DictionaryDto> pageInfoFinal = new PageInfo<>(list);
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /* (non-Javadoc)
     * @see com.wisdom.iwcs.service.system.DictionaryService#selectByDictType(java.lang.String)
     */
    public Result selectByDictType(String dictType) {
        List<Dictionary> dics = dictionaryMapper.selectByDictType(dictType);
        return new Result(200, "分页查询成功", dics);
    }

    public int deleteMoreByIds(List<String> ids) {
        int num = dictionaryMapper.deleteByPrimaryKeyList(ids);

        return num;
    }
    /**
     * 更新
     * @param dictionary
     * @return
     */
    public int updateData(Dictionary dictionary) {

        Integer userId = SecurityUtils.getCurrentUserId();
        dictionary.setLastModifiedBy(userId);
        dictionary.setLastModifiedTime(new Date().getTime());

        int num = dictionaryMapper.updateByPrimaryKey(dictionary);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 新增字典
     * @param dictionary
     * @return
     */
    public int saveData(Dictionary dictionary) {

        Integer userId = SecurityUtils.getCurrentUserId();
        dictionary.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        dictionary.setCreatedTime(new Date().getTime());
        dictionary.setCreatedBy(userId);
        dictionary.setLastModifiedBy(userId);
        dictionary.setLastModifiedTime(new Date().getTime());

        int num = dictionaryMapper.insertSelective(dictionary);

        return num;
    }

    public Result selectBaseTypeList(GridPageRequest gridPageRequest) {
        if (gridPageRequest.getSortList() == null) {
        } else {
            gridPageRequest.getSortList().forEach(gridSort -> {
                String sortkey = FieldMap.fieldMap.get(gridSort.getSortKey());
                if (sortkey != null) {
                    gridSort.setSortKey(sortkey);
                }
            });
        }
        String sortMybatisString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMybatisString);

        Map map = new HashMap();
        if (gridPageRequest.getSearchKey() != null && !gridPageRequest.getSearchKey().equals("")) {
            String searchKey = "%" + gridPageRequest.getSearchKey() + "%";
            if (searchKey.equals("%是%")) {
                map.put("status", 1);
            } else if (searchKey.equals("%否%")) {
                map.put("status", 0);
            } else {
                map.put("searchKey", searchKey);
            }
        }

        List<DictionaryDto> dictionaryList = dictionaryMapper.selectBaseTypeList(map);
        Byte byte1 = new Byte("0");
        Byte byte2 = new Byte("1");
        for (DictionaryDto dictionaryDto : dictionaryList) {
            if (dictionaryDto.getLastModifiedTime() != null) {
                dictionaryDto.setLastModifiedTimeStr(GZDateUtil.getStampToDate(dictionaryDto.getLastModifiedTime(), YZConstants.DateFormat.DATE_FORMAT));
            }
            if (dictionaryDto.getCreatedTime() != null) {
                dictionaryDto.setCreatedTimeStr(GZDateUtil.getStampToDate(dictionaryDto.getCreatedTime(), YZConstants.DateFormat.DATE_FORMAT));
            }
            if (dictionaryDto.getStatus() != null) {
                if (dictionaryDto.getStatus().equals(byte1)) {//0为总公司 1为分公司
                    dictionaryDto.setStatusStr("否");
                } else if (dictionaryDto.getStatus().equals(byte2)) {
                    dictionaryDto.setStatusStr("是");
                }
            }
        }

        PageInfo<DictionaryDto> pageInfo = new PageInfo<DictionaryDto>(dictionaryList);
        GridReturnData<DictionaryDto> gridReturnData = new GridReturnData<>();
        gridReturnData.setPageInfo(pageInfo);
        return new Result(200, "分页查询成功", gridReturnData);
    }

    public Result updateBaseDictionary(Dictionary dictionary) {
        if (dictionary.getId() != null && (dictionary.getDictTypeName() != null || dictionary.getDictType() != null)) {
            Dictionary oldDic = dictionaryMapper.selectByPrimaryKey(dictionary.getId());
            List<Dictionary> dictionaryList = dictionaryMapper.selectByDictType(oldDic.getDictType());
            for (Dictionary dictionary1 : dictionaryList) {
                if (dictionary.getDictTypeName() != null) {
                    dictionary1.setDictTypeName(dictionary.getDictTypeName());
                }
                if (dictionary.getDictType() != null) {
                    dictionary1.setDictType(dictionary.getDictType());
                }
                dictionaryMapper.updateByPrimaryKeySelective(dictionary1);
            }
            return new Result(200, "分页查询成功", dictionaryList);
        }
        return new Result(500, "更改失败", "");
    }

    public Result deleteBaseDictionary(Dictionary dictionary) {
        Integer user_id = SecurityUtils.getCurrentUserId();
        if (dictionary.getId() != null) {
            Dictionary oldDic = dictionaryMapper.selectByPrimaryKey(dictionary.getId());
            List<Dictionary> dictionaryList = dictionaryMapper.selectByDictType(oldDic.getDictType());
            for (Dictionary dictionary1 : dictionaryList) {
                dictionaryMapper.deleteByPrimaryKey(dictionary1.getId());
            }
            return new Result(200, "删除成功", "");
        }
        return new Result(500, "删除失败", "");
    }

    public List<Dictionary> getStrategicType(String dictionary) {

        List<Dictionary> dicList = dictionaryMapper.selectByDictType("TASK_TYPE");
        List<Dictionary> dicResultList = new ArrayList<Dictionary>();
        if (dicList.size() != 0) {
            dicList.forEach(item -> {
                if (!StringUtils.isEmpty(item.getJudgeType())) {
                    String[] judgeTypeList = item.getJudgeType().split(";");
                    for (int i = 0; i < judgeTypeList.length; i++) {
                        if (judgeTypeList[i].equalsIgnoreCase(dictionary)) {
                            dicResultList.add(item);
                        }
                    }
                }
            });
        }
        return dicResultList;
    }

    public List<Dictionary> selectDicByValue(Dictionary dictionary) {

        List<Dictionary> list = dictionaryMapper.selectDicByValue(dictionary);

        return list;
    }
}
