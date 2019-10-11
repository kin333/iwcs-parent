package com.wisdom.iwcs.domain.task;

import javax.persistence.*;

public class imitateTest {
    @Id
    private Long id;

    /**
     * �·�״̬
     */
    @Column(name = "DeliveryStatus")
    private String deliverystatus;

    /**
     * �����
     */
    private String taskcode;

    /**
     * ��������
     */
    @Column(name = "taskType")
    private String tasktype;

    /**
     * ���ϵ�
     */
    @Column(name = "OutSkuPoint")
    private String outskupoint;

    /**
     * ��������
     */
    @Column(name = "feedingQuantity")
    private Integer feedingquantity;

    /**
     * ���ϵ�һ
     */
    @Column(name = "InSkuPoint1")
    private String inskupoint1;

    /**
     * ���ϵ�һ��������
     */
    @Column(name = "InSkuPoint1_InSkuQuantity")
    private Integer inskupoint1Inskuquantity;

    /**
     * ���ϵ�һ��������
     */
    @Column(name = "InSkuPoint1_RecyclingQuantity")
    private Integer inskupoint1Recyclingquantity;

    /**
     * ���ϵ��
     */
    @Column(name = "InSkuPoint2")
    private String inskupoint2;

    /**
     * ���ϵ����������
     */
    @Column(name = "InSkuPoint2_InSkuQuantity")
    private Integer inskupoint2Inskuquantity;

    /**
     * ���ϵ����������
     */
    @Column(name = "InSkuPoint2_RecyclingQuantity")
    private Integer inskupoint2Recyclingquantity;

    /**
     * ���յ�
     */
    @Column(name = "RecyclingPoint")
    private String recyclingpoint;

    /**
     * �Ͽ����
     */
    @Column(name = "EmptyBoxPoint")
    private String emptyboxpoint;

    /**
     * �Ͽ�������
     */
    @Column(name = "EmptyBoxNumber")
    private Integer emptyboxnumber;

    /**
     * ���ܺ�
     */
    @Column(name = "ShelfNumber")
    private String shelfnumber;

    /**
     * ���
     */
    @Column(name = "StartingPoint")
    private String startingpoint;

    /**
     * �յ�
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
     * ��ȡ�·�״̬
     *
     * @return DeliveryStatus - �·�״̬
     */
    public String getDeliverystatus() {
        return deliverystatus;
    }

    /**
     * �����·�״̬
     *
     * @param deliverystatus �·�״̬
     */
    public void setDeliverystatus(String deliverystatus) {
        this.deliverystatus = deliverystatus == null ? null : deliverystatus.trim();
    }

    /**
     * ��ȡ�����
     *
     * @return taskcode - �����
     */
    public String getTaskcode() {
        return taskcode;
    }

    /**
     * ���������
     *
     * @param taskcode �����
     */
    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode == null ? null : taskcode.trim();
    }

    /**
     * ��ȡ��������
     *
     * @return taskType - ��������
     */
    public String getTasktype() {
        return tasktype;
    }

    /**
     * ������������
     *
     * @param tasktype ��������
     */
    public void setTasktype(String tasktype) {
        this.tasktype = tasktype == null ? null : tasktype.trim();
    }

    /**
     * ��ȡ���ϵ�
     *
     * @return OutSkuPoint - ���ϵ�
     */
    public String getOutskupoint() {
        return outskupoint;
    }

    /**
     * �������ϵ�
     *
     * @param outskupoint ���ϵ�
     */
    public void setOutskupoint(String outskupoint) {
        this.outskupoint = outskupoint == null ? null : outskupoint.trim();
    }

    /**
     * ��ȡ��������
     *
     * @return feedingQuantity - ��������
     */
    public Integer getFeedingquantity() {
        return feedingquantity;
    }

    /**
     * ������������
     *
     * @param feedingquantity ��������
     */
    public void setFeedingquantity(Integer feedingquantity) {
        this.feedingquantity = feedingquantity;
    }

    /**
     * ��ȡ���ϵ�һ
     *
     * @return InSkuPoint1 - ���ϵ�һ
     */
    public String getInskupoint1() {
        return inskupoint1;
    }

    /**
     * �������ϵ�һ
     *
     * @param inskupoint1 ���ϵ�һ
     */
    public void setInskupoint1(String inskupoint1) {
        this.inskupoint1 = inskupoint1 == null ? null : inskupoint1.trim();
    }

    /**
     * ��ȡ���ϵ�һ��������
     *
     * @return InSkuPoint1_InSkuQuantity - ���ϵ�һ��������
     */
    public Integer getInskupoint1Inskuquantity() {
        return inskupoint1Inskuquantity;
    }

    /**
     * �������ϵ�һ��������
     *
     * @param inskupoint1Inskuquantity ���ϵ�һ��������
     */
    public void setInskupoint1Inskuquantity(Integer inskupoint1Inskuquantity) {
        this.inskupoint1Inskuquantity = inskupoint1Inskuquantity;
    }

    /**
     * ��ȡ���ϵ�һ��������
     *
     * @return InSkuPoint1_RecyclingQuantity - ���ϵ�һ��������
     */
    public Integer getInskupoint1Recyclingquantity() {
        return inskupoint1Recyclingquantity;
    }

    /**
     * �������ϵ�һ��������
     *
     * @param inskupoint1Recyclingquantity ���ϵ�һ��������
     */
    public void setInskupoint1Recyclingquantity(Integer inskupoint1Recyclingquantity) {
        this.inskupoint1Recyclingquantity = inskupoint1Recyclingquantity;
    }

    /**
     * ��ȡ���ϵ��
     *
     * @return InSkuPoint2 - ���ϵ��
     */
    public String getInskupoint2() {
        return inskupoint2;
    }

    /**
     * �������ϵ��
     *
     * @param inskupoint2 ���ϵ��
     */
    public void setInskupoint2(String inskupoint2) {
        this.inskupoint2 = inskupoint2 == null ? null : inskupoint2.trim();
    }

    /**
     * ��ȡ���ϵ����������
     *
     * @return InSkuPoint2_InSkuQuantity - ���ϵ����������
     */
    public Integer getInskupoint2Inskuquantity() {
        return inskupoint2Inskuquantity;
    }

    /**
     * �������ϵ����������
     *
     * @param inskupoint2Inskuquantity ���ϵ����������
     */
    public void setInskupoint2Inskuquantity(Integer inskupoint2Inskuquantity) {
        this.inskupoint2Inskuquantity = inskupoint2Inskuquantity;
    }

    /**
     * ��ȡ���ϵ����������
     *
     * @return InSkuPoint2_RecyclingQuantity - ���ϵ����������
     */
    public Integer getInskupoint2Recyclingquantity() {
        return inskupoint2Recyclingquantity;
    }

    /**
     * �������ϵ����������
     *
     * @param inskupoint2Recyclingquantity ���ϵ����������
     */
    public void setInskupoint2Recyclingquantity(Integer inskupoint2Recyclingquantity) {
        this.inskupoint2Recyclingquantity = inskupoint2Recyclingquantity;
    }

    /**
     * ��ȡ���յ�
     *
     * @return RecyclingPoint - ���յ�
     */
    public String getRecyclingpoint() {
        return recyclingpoint;
    }

    /**
     * ���û��յ�
     *
     * @param recyclingpoint ���յ�
     */
    public void setRecyclingpoint(String recyclingpoint) {
        this.recyclingpoint = recyclingpoint == null ? null : recyclingpoint.trim();
    }

    /**
     * ��ȡ�Ͽ����
     *
     * @return EmptyBoxPoint - �Ͽ����
     */
    public String getEmptyboxpoint() {
        return emptyboxpoint;
    }

    /**
     * �����Ͽ����
     *
     * @param emptyboxpoint �Ͽ����
     */
    public void setEmptyboxpoint(String emptyboxpoint) {
        this.emptyboxpoint = emptyboxpoint == null ? null : emptyboxpoint.trim();
    }

    /**
     * ��ȡ�Ͽ�������
     *
     * @return EmptyBoxNumber - �Ͽ�������
     */
    public Integer getEmptyboxnumber() {
        return emptyboxnumber;
    }

    /**
     * �����Ͽ�������
     *
     * @param emptyboxnumber �Ͽ�������
     */
    public void setEmptyboxnumber(Integer emptyboxnumber) {
        this.emptyboxnumber = emptyboxnumber;
    }

    /**
     * ��ȡ���ܺ�
     *
     * @return ShelfNumber - ���ܺ�
     */
    public String getShelfnumber() {
        return shelfnumber;
    }

    /**
     * ���û��ܺ�
     *
     * @param shelfnumber ���ܺ�
     */
    public void setShelfnumber(String shelfnumber) {
        this.shelfnumber = shelfnumber == null ? null : shelfnumber.trim();
    }

    /**
     * ��ȡ���
     *
     * @return StartingPoint - ���
     */
    public String getStartingpoint() {
        return startingpoint;
    }

    /**
     * �������
     *
     * @param startingpoint ���
     */
    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint == null ? null : startingpoint.trim();
    }

    /**
     * ��ȡ�յ�
     *
     * @return EndPoint - �յ�
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * �����յ�
     *
     * @param endpoint �յ�
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint == null ? null : endpoint.trim();
    }
}