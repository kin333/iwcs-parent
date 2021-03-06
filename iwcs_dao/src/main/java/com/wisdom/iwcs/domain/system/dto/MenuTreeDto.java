package com.wisdom.iwcs.domain.system.dto;

import com.wisdom.iwcs.domain.system.Depart;

import java.util.ArrayList;
import java.util.List;

public class MenuTreeDto {
    private Integer id;
    private String label;
    private String title;
    private Depart depart;
    private Boolean checked = false;
    private String templCode;
    private String value;
    private String type;

    public String getTemplCode() {
        return templCode;
    }

    public void setTemplCode(String templCode) {
        this.templCode = templCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private List<MenuTreeDto> children = new ArrayList<>();

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<MenuTreeDto> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeDto> children) {
        this.children = children;
    }
}
