package com.wisdom.iwcs.domain.codec.dto;

import com.wisdom.iwcs.domain.codec.Area;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Table(name = "codec_area")
public class AreaDto extends Area {

    private Integer value;
    private String label;
    private Boolean loading = false;


    private List<AreaDto> children = new ArrayList<>();

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getLoading() {
        return loading;
    }

    public void setLoading(Boolean loading) {
        this.loading = loading;
    }

    public List<AreaDto> getChildren() {
        return children;
    }

    public void setChildren(List<AreaDto> children) {
        this.children = children;
    }
}