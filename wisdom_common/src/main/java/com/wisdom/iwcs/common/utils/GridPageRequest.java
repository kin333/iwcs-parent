/*
 * GridPageRequest.java
 *
 * Created Date: 2016年12月30日
 *
 * Copyright (c)  Centling Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Centling Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Centling Technologies Co., Ltd.
 */

package com.wisdom.iwcs.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ted.Ma
 * @version <br>
 * <p>高级grid查询,包含对应筛选、排序、分页信息</p>
 */

public class GridPageRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6082624858102176557L;
    /**
     * 筛选条件列表
     */
    private List<GridFilterInfo> filterList;
    /**
     * 是否是第一次获取该grid 数据，若是，需要返回column defs.反之，不返回
     */
    private boolean firstRequest = false;
    private int pageNum;
    private int pageSize;
    private List<GridSortInfo> sortList;
    private Boolean showHistory;
    /**
     * 关键字搜索
     */
    private String searchKey;

    public List<GridFilterInfo> getFilterList() {

        return filterList;
    }

    public void setFilterList(List<GridFilterInfo> filterList) {

        this.filterList = filterList;
    }

    public boolean isFirstRequest() {

        return firstRequest;
    }

    public void setFirstRequest(boolean firstRequest) {

        this.firstRequest = firstRequest;
    }

    public int getPageNum() {

        return pageNum;
    }

    public void setPageNum(int pageNum) {

        this.pageNum = pageNum;
    }

    public int getPageSize() {

        return pageSize;
    }

    public void setPageSize(int pageSize) {

        this.pageSize = pageSize;
    }

    public List<GridSortInfo> getSortList() {

        return sortList;
    }

    public void setSortList(List<GridSortInfo> sortList) {

        this.sortList = sortList;
    }

    public String getSortMybatisString() {
        String mybatis = "";
        final StringBuffer mybatisStr = new StringBuffer();
        if (this.sortList != null) {
            sortList.stream().forEach(s -> {
                mybatisStr.append(CamelCaseUtils.toUnderlineName(s.getMybatisStr())).append(",");
            });
            if (mybatisStr.length() > 0) {
                mybatis = mybatisStr.substring(0, mybatisStr.length() - 1);
            }
        }
        return mybatis;
    }


    public String getSearchKey() {

        return searchKey;
    }


    public void setSearchKey(String searchKey) {

        this.searchKey = searchKey;
    }

    public Boolean getShowHistory() {
        return showHistory;
    }

    public void setShowHistory(Boolean showHistory) {
        this.showHistory = showHistory;
    }
}
