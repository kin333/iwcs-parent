package com.wisdom.iwcs.domain.inv;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author panqingzun
 * @Date 2019-03-20
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "inv_task_cond_detail")
public class InvTaskCondDetail implements Serializable {

    private static final long serialVersionUID = 6645115519029615568L;

    @Id
    private Integer id;

    /**
     * 盘点任务唯一编号
     */
    @Column(name = "inv_num")
    private String invNum;

    /**
     * 盘点类型，0-明盘；1-暗盘
     */
    @Column(name = "inv_type")
    private Integer invType;

    @Column(name = "stk_order_no")
    private String stkOrderNo;

    @Column(name = "stk_sub_order_no")
    private String stkSubOrderNo;

    /**
     * 货主
     */
    @Column(name = "cargo_owner")
    private String cargoOwner;

    /**
     * 物料号
     */
    @Column(name = "mat_code")
    private String matCode;

    /**
     * 特征值1
     */
    @Column(name = "stk_character1")
    private String stkCharacter1;

    /**
     * 特征值2
     */
    @Column(name = "stk_character2")
    private String stkCharacter2;

    /**
     * 特征值3
     */
    @Column(name = "stk_character3")
    private String stkCharacter3;

    /**
     * 特征值4
     */
    @Column(name = "stk_character4")
    private String stkCharacter4;

    /**
     * 特征值5
     */
    @Column(name = "stk_character5")
    private String stkCharacter5;

    /**
     * 盘点物料在几天内有变化
     */
    @Column(name = "days")
    private Integer days;

    /**
     * 是否按SN盘点 0-NO 1-YES
     */
    @Column(name = "per_sn")
    private Integer perSn;

    /**
     * 有效标记，0有效，1无效
     */
    @Column(name = "valid_flag")
    private Integer validFlag;

    /**
     * 删除标记，0未删除，1删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 最后修改人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 库区编号
     */
    @Column(name = "stg_area_code")
    private String stgAreaCode;

}
