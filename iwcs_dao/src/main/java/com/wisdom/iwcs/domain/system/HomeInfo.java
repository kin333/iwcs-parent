package com.wisdom.iwcs.domain.system;



public class HomeInfo {
    // 未初始化货架
    private Integer uninitializedPod;
    // 未开始的子任务
    private Integer unStartSubTaskCount;
    // 执行中的子任务
    private Integer startSubTaskCount;
    // 已结束的子任务
    private Integer endSubTaskCount;
    // 未开始的主任务
    private Integer unStartMainTaskCount;
    // 执行中的主任务吧
    private Integer startMainTaskCount;
    // 已结束的主任务
    private Integer endMainTaskCount;
    // 未锁定的货架
    private Integer unLockPod;
    // 无货货架数量
    private Integer unStock;


    public Integer getUninitializedPod() {
        return uninitializedPod;
    }

    public void setUninitializedPod(Integer uninitializedPod) {
        this.uninitializedPod = uninitializedPod;
    }

    public Integer getUnStartSubTaskCount() {
        return unStartSubTaskCount;
    }

    public void setUnStartSubTaskCount(Integer unStartSubTaskCount) {
        this.unStartSubTaskCount = unStartSubTaskCount;
    }

    public Integer getStartSubTaskCount() {
        return startSubTaskCount;
    }

    public void setStartSubTaskCount(Integer startSubTaskCount) {
        this.startSubTaskCount = startSubTaskCount;
    }

    public Integer getEndSubTaskCount() {
        return endSubTaskCount;
    }

    public void setEndSubTaskCount(Integer endSubTaskCount) {
        this.endSubTaskCount = endSubTaskCount;
    }

    public Integer getUnStartMainTaskCount() {
        return unStartMainTaskCount;
    }

    public void setUnStartMainTaskCount(Integer unStartMainTaskCount) {
        this.unStartMainTaskCount = unStartMainTaskCount;
    }

    public Integer getStartMainTaskCount() {
        return startMainTaskCount;
    }

    public void setStartMainTaskCount(Integer startMainTaskCount) {
        this.startMainTaskCount = startMainTaskCount;
    }

    public Integer getEndMainTaskCount() {
        return endMainTaskCount;
    }

    public void setEndMainTaskCount(Integer endMainTaskCount) {
        this.endMainTaskCount = endMainTaskCount;
    }

    public Integer getUnLockPod() {
        return unLockPod;
    }

    public void setUnLockPod(Integer unLockPod) {
        this.unLockPod = unLockPod;
    }

    public Integer getUnStock() {
        return unStock;
    }

    public void setUnStock(Integer unStock) {
        this.unStock = unStock;
    }

}
