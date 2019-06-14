package com.wisdom.iwcs.commonDto.fliterConOptions;

import com.wisdom.iwcs.commonDto.fliterCondition.BaseFliterCondition;

/**
 * 基础货架静态属性可筛选项
 *
 * @author ted
 * @create 2019-02-28 上午10:57
 **/
public class BasePodDetailConOptions extends BaseFliterCondition {


    /**
     * 存储区类型代码
     */
    private String stgTypeCode;

    /**
     * 存储区代码
     */
    private String stgCode;

    /**
     * 货架预留属性，如货主
     */
    private String podProp1;

    /**
     * 货架预留属性
     */
    private String podProp2;

    /**
     * 货架预留属性
     */
    private String podProp3;

    /**
     * 货架预留属性
     */
    private String podProp4;

    /**
     * 货架预留属性
     */
    private String podProp5;


    public String getStgTypeCode() {
        return stgTypeCode;
    }

    public void setStgTypeCode(String stgTypeCode) {
        this.stgTypeCode = stgTypeCode;
    }

    public String getStgCode() {
        return stgCode;
    }

    public void setStgCode(String stgCode) {
        this.stgCode = stgCode;
    }


    public String getPodProp1() {
        return podProp1;
    }

    public void setPodProp1(String podProp1) {
        this.podProp1 = podProp1;
    }

    public String getPodProp2() {
        return podProp2;
    }

    public void setPodProp2(String podProp2) {
        this.podProp2 = podProp2;
    }

    public String getPodProp3() {
        return podProp3;
    }

    public void setPodProp3(String podProp3) {
        this.podProp3 = podProp3;
    }

    public String getPodProp4() {
        return podProp4;
    }

    public void setPodProp4(String podProp4) {
        this.podProp4 = podProp4;
    }

    public String getPodProp5() {
        return podProp5;
    }

    public void setPodProp5(String podProp5) {
        this.podProp5 = podProp5;
    }

}
