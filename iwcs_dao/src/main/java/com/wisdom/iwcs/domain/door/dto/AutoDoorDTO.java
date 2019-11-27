package com.wisdom.iwcs.domain.door.dto;

import java.util.Date;
import javax.persistence.*;

@Table(name = "auto_door")
public class AutoDoorDTO {
        @Id
        private Integer id;

        /**
         * 通信编号（三方通信协议使用(地址编号)）
         */
        @Column(name = "msg_code")
        private String msgCode;

        /**
         * 门编号
         */
        @Column(name = "door_code")
        private String doorCode;

        /**
         * 电梯状态: 正常、异常1，异常2等
         */
        @Column(name = "door_status")
        private String doorStatus;

        /**
         * 状态更新时间
         */
        @Column(name = "status_update_time")
        private Date statusUpdateTime;

        /**
         * 模式：1自动、2手动
         */
        @Column(name = "door_model")
        private String doorModel;

        /**
         * 任务状态：1关门到位，2关门中，3开门中，4开门到位
         */
        @Column(name = "task_status")
        private String taskStatus;

        /**
         * 门任务状态变更时间
         */
        @Column(name = "door_task_update_time")
        private Date doorTaskUpdateTime;

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

        public String getMsgCode() {
            return msgCode;
        }

        public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

        /**
         * 获取门编号
         *
         * @return door_code - 门编号
         */
        public String getDoorCode() {
            return doorCode;
        }

        /**
         * 设置门编号
         *
         * @param doorCode 门编号
         */
        public void setDoorCode(String doorCode) {
            this.doorCode = doorCode == null ? null : doorCode.trim();
        }

        /**
         * 获取电梯状态: 正常、异常1，异常2等
         *
         * @return door_status - 电梯状态: 正常、异常1，异常2等
         */
        public String getDoorStatus() {
            return doorStatus;
        }

        /**
         * 设置电梯状态: 正常、异常1，异常2等
         *
         * @param doorStatus 电梯状态: 正常、异常1，异常2等
         */
        public void setDoorStatus(String doorStatus) {
            this.doorStatus = doorStatus == null ? null : doorStatus.trim();
        }

        /**
         * 获取状态更新时间
         *
         * @return status_update_time - 状态更新时间
         */
        public Date getStatusUpdateTime() {
            return statusUpdateTime;
        }

        /**
         * 设置状态更新时间
         *
         * @param statusUpdateTime 状态更新时间
         */
        public void setStatusUpdateTime(Date statusUpdateTime) {
            this.statusUpdateTime = statusUpdateTime;
        }

        /**
         * 获取模式：1自动、2手动
         *
         * @return door_model - 模式：1自动、2手动
         */
        public String getDoorModel() {
            return doorModel;
        }

        /**
         * 设置模式：1自动、2手动
         *
         * @param doorModel 模式：1自动、2手动
         */
        public void setDoorModel(String doorModel) {
            this.doorModel = doorModel == null ? null : doorModel.trim();
        }

        /**
         * 获取任务状态：1关门到位，2关门中，3开门中，4开门到位
         *
         * @return task_status - 任务状态：1关门到位，2关门中，3开门中，4开门到位
         */
        public String getTaskStatus() {
            return taskStatus;
        }

        /**
         * 设置任务状态：1关门到位，2关门中，3开门中，4开门到位
         *
         * @param taskStatus 任务状态：1关门到位，2关门中，3开门中，4开门到位
         */
        public void setTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus == null ? null : taskStatus.trim();
        }

        /**
         * 获取门任务状态变更时间
         *
         * @return door_task_update_time - 门任务状态变更时间
         */
        public Date getDoorTaskUpdateTime() {
            return doorTaskUpdateTime;
        }

        /**
         * 设置门任务状态变更时间
         *
         * @param doorTaskUpdateTime 门任务状态变更时间
         */
        public void setDoorTaskUpdateTime(Date doorTaskUpdateTime) {
            this.doorTaskUpdateTime = doorTaskUpdateTime;
        }
    }
