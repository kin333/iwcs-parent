package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_pod_layer_stk")
public class BasePodLayerStk {
    @Id
    private Integer id;

    /**
     * 货架编号
     */
    @Column(name = "pod_code")
    private String podCode;

    /**
     * 货架类别
     */
    @Column(name = "pod_type")
    private String podType;

    /**
     * 该货架类型共几层
     */
    @Column(name = "total_layer")
    private Integer totalLayer;

    /**
     * 第几层
     */
    private Integer layer;

    /**
     * 改层bincode总数量
     */
    @Column(name = "bincode_count")
    private Integer bincodeCount;

    /**
     * 空bincode数量
     */
    @Column(name = "e_bincode")
    private Integer eBincode;

    /**
     * 有货bincode数量
     */
    @Column(name = "f_bincode")
    private Integer fBincode;

    /**
     * 更新时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 库区编码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 地图编码
     */
    @Column(name = "map_code")
    private String mapCode;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取货架编号
     *
     * @return pod_code - 货架编号
     */
    public String getPodCode() {
        return podCode;
    }

    /**
     * 设置货架编号
     *
     * @param podCode 货架编号
     */
    public void setPodCode(String podCode) {
        this.podCode = podCode == null ? null : podCode.trim();
    }

    /**
     * 获取货架类别
     *
     * @return pod_type - 货架类别
     */
    public String getPodType() {
        return podType;
    }

    /**
     * 设置货架类别
     *
     * @param podType 货架类别
     */
    public void setPodType(String podType) {
        this.podType = podType == null ? null : podType.trim();
    }

    /**
     * 获取该货架类型共几层
     *
     * @return total_layer - 该货架类型共几层
     */
    public Integer getTotalLayer() {
        return totalLayer;
    }

    /**
     * 设置该货架类型共几层
     *
     * @param totalLayer 该货架类型共几层
     */
    public void setTotalLayer(Integer totalLayer) {
        this.totalLayer = totalLayer;
    }

    /**
     * 获取第几层
     *
     * @return layer - 第几层
     */
    public Integer getLayer() {
        return layer;
    }

    /**
     * 设置第几层
     *
     * @param layer 第几层
     */
    public void setLayer(Integer layer) {
        this.layer = layer;
    }

    /**
     * 获取改层bincode总数量
     *
     * @return bincode_count - 改层bincode总数量
     */
    public Integer getBincodeCount() {
        return bincodeCount;
    }

    /**
     * 设置改层bincode总数量
     *
     * @param bincodeCount 改层bincode总数量
     */
    public void setBincodeCount(Integer bincodeCount) {
        this.bincodeCount = bincodeCount;
    }

    /**
     * 获取空bincode数量
     *
     * @return e_bincode - 空bincode数量
     */
    public Integer geteBincode() {
        return eBincode;
    }

    /**
     * 设置空bincode数量
     *
     * @param eBincode 空bincode数量
     */
    public void seteBincode(Integer eBincode) {
        this.eBincode = eBincode;
    }

    /**
     * 获取有货bincode数量
     *
     * @return f_bincode - 有货bincode数量
     */
    public Integer getfBincode() {
        return fBincode;
    }

    /**
     * 设置有货bincode数量
     *
     * @param fBincode 有货bincode数量
     */
    public void setfBincode(Integer fBincode) {
        this.fBincode = fBincode;
    }

    /**
     * 获取更新时间
     *
     * @return last_modified_time - 更新时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置更新时间
     *
     * @param lastModifiedTime 更新时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}