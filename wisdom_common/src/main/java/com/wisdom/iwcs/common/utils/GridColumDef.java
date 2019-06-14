package com.wisdom.iwcs.common.utils;

import java.io.Serializable;

public class GridColumDef implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -8896743335843806809L;
    private String field;
    private String name;
    private String width;
    private SearchFilter filter;

    public String getField() {

        return field;
    }

    public void setField(String field) {

        this.field = field;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getWidth() {

        return width;
    }

    public void setWidth(String width) {

        this.width = width;
    }


    public SearchFilter getFilter() {

        return filter;
    }

    public void setFilter(SearchFilter filter) {

        this.filter = filter;
    }

    /**
     * @param filed
     * @param name
     * @param width
     * @param filterDataType
     * @param fliterSelectDataList
     * @param gridState
     */
    public GridColumDef(String filed, String name, String width, SearchFilter filter) {
        super();
        this.field = filed;
        this.name = name;
        this.width = width;
        this.filter = filter;
    }

    /**
     * @param filed
     * @param name
     * @param width
     */
    public GridColumDef(String filed, String name, String width) {
        super();
        this.field = filed;
        this.name = name;
        this.width = width;
    }

    /**
     *
     */
    public GridColumDef() {
        super();
    }


}
