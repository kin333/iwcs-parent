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
 * @Author
 * @Date 2019-03-20
 */

@Setter
@Getter
@ToString
@Entity
@Table(name = "inv_task")
public class InvTask implements Serializable {

    private static final long serialVersionUID = 8626039522434354939L;

    @Id
    private Integer id;

    /**
     * 上游盘点任务唯一编号
     */
    @Column(name = "src_inv_no")
    private String srcInvNo;

    /**
     * 物料最近操作天数
     */
    @Column(name = "days")
    private Integer days;

    /**
     * 盘点状态，0未盘点，1已盘点
     */
    @Column(name = "inv_status")
    private Integer invStatus;

    /**
     * 盘点任务唯一编号
     */
    @Column(name = "sys_inv_num")
    private String sysInvNum;

    /**
     * 任务描述
     */
    @Column(name = "task_desc")
    private String taskDesc;

    /**
     * 上游用户id（发起盘点任务的操作人
     */
    @Column(name = "src_user_code")
    private Integer srcUserCode;

    /**
     * 盘点类型 0：盘点库存 1：盘点库存和发生变动+天数days
     */
    @Column(name = "inv_type")
    private Integer invType;

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

}


