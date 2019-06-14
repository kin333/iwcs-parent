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
@Table(name = "inv_task_bincode_process")
public class InvTaskBincodeProcess implements Serializable {

    private static final long serialVersionUID = 5869795557273517502L;

    @Id
    private Integer id;

    /**
     * 上游盘点号
     */
    @Column(name = "src_inv_no")
    private String srcInvNo;

    /**
     * 任务bincode
     */
    @Column(name = "task_bincode")
    private String taskBincode;

    /**
     * 货架编号
     */
    @Column(name = "podcode")
    private String podcode;

    /**
     * 盘点状态，0未盘点，1已盘点
     */
    @Column(name = "inv_status")
    private Integer invStatus;

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
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 盘点上报结果时间
     */
    @Column(name = "inv_reporting_result_time")
    private Date invReportingResultTime;

    /**
     * 盘点货架是否到达工作台，0是，1否
     */
    @Column(name = "if_out")
    private Integer ifOut;

}
