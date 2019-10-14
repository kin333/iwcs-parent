package com.wisdom.iwcs.domain.task.dto;

import javax.persistence.*;

public class ImitateTestDTO {
    @Id
    private Long id;

    /**
     * 下发状态
     */
    @Column(name = "DeliveryStatus")
    private String deliverystatus;

    /**
     * 任务号
     */
    private String taskcode;

    /**
     * 任务类型
     */
    @Column(name = "taskType")
    private String tasktype;

    /**
     * 上料点
     */
    @Column(name = "OutSkuPoint")
    private String outskupoint;

    /**
     * 上料数量
     */
    @Column(name = "feedingQuantity")
    private Integer feedingquantity;

    /**
     * 下料点一
     */
    @Column(name = "InSkuPoint1")
    private String inskupoint1;

    /**
     * 下料点一下料数量
     */
    @Column(name = "InSkuPoint1_InSkuQuantity")
    private Integer inskupoint1Inskuquantity;

    /**
     * 下料点一回收数量
     */
    @Column(name = "InSkuPoint1_RecyclingQuantity")
    private Integer inskupoint1Recyclingquantity;

    /**
     * 下料点二
     */
    @Column(name = "InSkuPoint2")
    private String inskupoint2;

    /**
     * 下料点二下料数量
     */
    @Column(name = "InSkuPoint2_InSkuQuantity")
    private Integer inskupoint2Inskuquantity;

    /**
     * 下料点二回收数量
     */
    @Column(name = "InSkuPoint2_RecyclingQuantity")
    private Integer inskupoint2Recyclingquantity;

    /**
     * 回收点
     */
    @Column(name = "RecyclingPoint")
    private String recyclingpoint;

    /**
     * 上空箱点
     */
    @Column(name = "EmptyBoxPoint")
    private String emptyboxpoint;

    /**
     * 上空箱数量
     */
    @Column(name = "EmptyBoxNumber")
    private Integer emptyboxnumber;

    /**
     * 货架号
     */
    @Column(name = "ShelfNumber")
    private String shelfnumber;

    /**
     * 起点
     */
    @Column(name = "StartingPoint")
    private String startingpoint;

    /**
     * 终点
     */
    @Column(name = "EndPoint")
    private String endpoint;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取下发状态
     *
     * @return DeliveryStatus - 下发状态
     */
    public String getDeliverystatus() {
        return deliverystatus;
    }

    /**
     * 设置下发状态
     *
     * @param deliverystatus 下发状态
     */
    public void setDeliverystatus(String deliverystatus) {
        this.deliverystatus = deliverystatus == null ? null : deliverystatus.trim();
    }

    /**
     * 获取任务号
     *
     * @return taskcode - 任务号
     */
    public String getTaskcode() {
        return taskcode;
    }

    /**
     * 设置任务号
     *
     * @param taskcode 任务号
     */
    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode == null ? null : taskcode.trim();
    }

    /**
     * 获取任务类型
     *
     * @return taskType - 任务类型
     */
    public String getTasktype() {
        return tasktype;
    }

    /**
     * 设置任务类型
     *
     * @param tasktype 任务类型
     */
    public void setTasktype(String tasktype) {
        this.tasktype = tasktype == null ? null : tasktype.trim();
    }

    /**
     * 获取上料点
     *
     * @return OutSkuPoint - 上料点
     */
    public String getOutskupoint() {
        return outskupoint;
    }

    /**
     * 设置上料点
     *
     * @param outskupoint 上料点
     */
    public void setOutskupoint(String outskupoint) {
        this.outskupoint = outskupoint == null ? null : outskupoint.trim();
    }

    /**
     * 获取上料数量
     *
     * @return feedingQuantity - 上料数量
     */
    public Integer getFeedingquantity() {
        return feedingquantity;
    }

    /**
     * 设置上料数量
     *
     * @param feedingquantity 上料数量
     */
    public void setFeedingquantity(Integer feedingquantity) {
        this.feedingquantity = feedingquantity;
    }

    /**
     * 获取下料点一
     *
     * @return InSkuPoint1 - 下料点一
     */
    public String getInskupoint1() {
        return inskupoint1;
    }

    /**
     * 设置下料点一
     *
     * @param inskupoint1 下料点一
     */
    public void setInskupoint1(String inskupoint1) {
        this.inskupoint1 = inskupoint1 == null ? null : inskupoint1.trim();
    }

    /**
     * 获取下料点一下料数量
     *
     * @return InSkuPoint1_InSkuQuantity - 下料点一下料数量
     */
    public Integer getInskupoint1Inskuquantity() {
        return inskupoint1Inskuquantity;
    }

    /**
     * 设置下料点一下料数量
     *
     * @param inskupoint1Inskuquantity 下料点一下料数量
     */
    public void setInskupoint1Inskuquantity(Integer inskupoint1Inskuquantity) {
        this.inskupoint1Inskuquantity = inskupoint1Inskuquantity;
    }

    /**
     * 获取下料点一回收数量
     *
     * @return InSkuPoint1_RecyclingQuantity - 下料点一回收数量
     */
    public Integer getInskupoint1Recyclingquantity() {
        return inskupoint1Recyclingquantity;
    }

    /**
     * 设置下料点一回收数量
     *
     * @param inskupoint1Recyclingquantity 下料点一回收数量
     */
    public void setInskupoint1Recyclingquantity(Integer inskupoint1Recyclingquantity) {
        this.inskupoint1Recyclingquantity = inskupoint1Recyclingquantity;
    }

    /**
     * 获取下料点二
     *
     * @return InSkuPoint2 - 下料点二
     */
    public String getInskupoint2() {
        return inskupoint2;
    }

    /**
     * 设置下料点二
     *
     * @param inskupoint2 下料点二
     */
    public void setInskupoint2(String inskupoint2) {
        this.inskupoint2 = inskupoint2 == null ? null : inskupoint2.trim();
    }

    /**
     * 获取下料点二下料数量
     *
     * @return InSkuPoint2_InSkuQuantity - 下料点二下料数量
     */
    public Integer getInskupoint2Inskuquantity() {
        return inskupoint2Inskuquantity;
    }

    /**
     * 设置下料点二下料数量
     *
     * @param inskupoint2Inskuquantity 下料点二下料数量
     */
    public void setInskupoint2Inskuquantity(Integer inskupoint2Inskuquantity) {
        this.inskupoint2Inskuquantity = inskupoint2Inskuquantity;
    }

    /**
     * 获取下料点二回收数量
     *
     * @return InSkuPoint2_RecyclingQuantity - 下料点二回收数量
     */
    public Integer getInskupoint2Recyclingquantity() {
        return inskupoint2Recyclingquantity;
    }

    /**
     * 设置下料点二回收数量
     *
     * @param inskupoint2Recyclingquantity 下料点二回收数量
     */
    public void setInskupoint2Recyclingquantity(Integer inskupoint2Recyclingquantity) {
        this.inskupoint2Recyclingquantity = inskupoint2Recyclingquantity;
    }

    /**
     * 获取回收点
     *
     * @return RecyclingPoint - 回收点
     */
    public String getRecyclingpoint() {
        return recyclingpoint;
    }

    /**
     * 设置回收点
     *
     * @param recyclingpoint 回收点
     */
    public void setRecyclingpoint(String recyclingpoint) {
        this.recyclingpoint = recyclingpoint == null ? null : recyclingpoint.trim();
    }

    /**
     * 获取上空箱点
     *
     * @return EmptyBoxPoint - 上空箱点
     */
    public String getEmptyboxpoint() {
        return emptyboxpoint;
    }

    /**
     * 设置上空箱点
     *
     * @param emptyboxpoint 上空箱点
     */
    public void setEmptyboxpoint(String emptyboxpoint) {
        this.emptyboxpoint = emptyboxpoint == null ? null : emptyboxpoint.trim();
    }

    /**
     * 获取上空箱数量
     *
     * @return EmptyBoxNumber - 上空箱数量
     */
    public Integer getEmptyboxnumber() {
        return emptyboxnumber;
    }

    /**
     * 设置上空箱数量
     *
     * @param emptyboxnumber 上空箱数量
     */
    public void setEmptyboxnumber(Integer emptyboxnumber) {
        this.emptyboxnumber = emptyboxnumber;
    }

    /**
     * 获取货架号
     *
     * @return ShelfNumber - 货架号
     */
    public String getShelfnumber() {
        return shelfnumber;
    }

    /**
     * 设置货架号
     *
     * @param shelfnumber 货架号
     */
    public void setShelfnumber(String shelfnumber) {
        this.shelfnumber = shelfnumber == null ? null : shelfnumber.trim();
    }

    /**
     * 获取起点
     *
     * @return StartingPoint - 起点
     */
    public String getStartingpoint() {
        return startingpoint;
    }

    /**
     * 设置起点
     *
     * @param startingpoint 起点
     */
    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint == null ? null : startingpoint.trim();
    }

    /**
     * 获取终点
     *
     * @return EndPoint - 终点
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * 设置终点
     *
     * @param endpoint 终点
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint == null ? null : endpoint.trim();
    }
}