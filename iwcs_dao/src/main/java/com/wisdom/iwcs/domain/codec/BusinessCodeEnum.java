package com.wisdom.iwcs.domain.codec;

public enum BusinessCodeEnum {

    ContainerModel("CTNCLCD", "尺寸箱型"),
    PackageType("PKTP", "包装类型");

    private String code;
    private String remark;

    BusinessCodeEnum(String code, String remark) {
        this.setCode(code);
        this.setRemark(remark);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getCode());
    }

    /**
     * 获得描述字符串--用于记录log
     *
     * @param
     * @return
     */
    public String getDisplayStr() {
        return "{ 'code': '" + this.code + "';'" + "'remark':'" + this.remark + "'}";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
