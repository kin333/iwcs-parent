package com.wisdom.iwcs.common.utils;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

public class GridReturnData<T> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8396583412935147674L;
    private PageInfo<T> pageInfo;
    private List<GridColumDef> gridColumDefs;
    private String gridState;

    public PageInfo<T> getPageInfo() {

        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {

        this.pageInfo = pageInfo;
    }

    public List<GridColumDef> getGridColumDefs() {

        return gridColumDefs;
    }

    public void setGridColumDefs(List<GridColumDef> gridColumDefs) {

        this.gridColumDefs = gridColumDefs;
    }

    /**
     * @param pageInfo
     * @param gridColumDefs
     */
    public GridReturnData(PageInfo<T> pageInfo,
                          List<GridColumDef> gridColumDefs) {
        super();
        this.pageInfo = pageInfo;
        this.gridColumDefs = gridColumDefs;
    }

    /**
     * @param pageInfo
     */
    public GridReturnData(PageInfo<T> pageInfo) {
        super();
        this.pageInfo = pageInfo;
    }

    /**
     * @param gridColumDefs
     */
    public GridReturnData(List<GridColumDef> gridColumDefs) {
        super();
        this.gridColumDefs = gridColumDefs;
    }

    /**
     *
     */
    public GridReturnData() {
        super();
    }


    public String getGridState() {

        return gridState;
    }


    public void setGridState(String gridState) {

        this.gridState = gridState;
    }

    /**
     * @param pageInfo
     * @param gridColumDefs
     * @param gridState
     */
    public GridReturnData(PageInfo<T> pageInfo,
                          List<GridColumDef> gridColumDefs, String gridState) {
        super();
        this.pageInfo = pageInfo;
        this.gridColumDefs = gridColumDefs;
        this.gridState = gridState;
    }


}
