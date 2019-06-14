package com.wisdom.iwcs.domain.system.dto;

import com.wisdom.iwcs.domain.system.Depart;

/**
 * @author Devin
 * @date 2018-03-08.
 */
public class DepartmentDTO extends Depart {

    private Integer relationType;

    private Boolean checked = false;

    private Boolean selectAll = false;

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getSelectAll() {
        return selectAll;
    }

    public void setSelectAll(Boolean selectAll) {
        this.selectAll = selectAll;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }
}
