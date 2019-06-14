package com.wisdom.iwcs.domain.codec;

public enum GroupTypeEnum {

    OperGroup(1, "制单"),
    CanvassGroup(2, "揽货"),
    BUSINESS(3, "商务");

    private Integer typeId;
    private String remark;

    GroupTypeEnum(Integer typeId, String remark) {
        this.setTypeId(typeId);
        this.setRemark(remark);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getTypeId());
    }

    /**
     * 获得描述字符串--用于记录log
     *
     * @param
     * @return
     */
    public String getDisplayStr() {
        return "{ 'typeId': '" + this.typeId + "';'" + "'remark':'" + this.remark + "'}";
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}