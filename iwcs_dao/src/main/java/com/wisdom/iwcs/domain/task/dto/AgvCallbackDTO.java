package com.wisdom.iwcs.domain.task.dto;

import javax.persistence.*;

@Table(name = "agv_callback")
public class AgvCallbackDTO {
    @Id
    private Integer id;

    /**
     * 请求编号
     */
    @Column(name = "req_code")
    private String reqCode;

    /**
     * 请求时间戳
     */
    @Column(name = "req_time")
    private String reqTime;

    /**
     * 客户端编号
     */
    @Column(name = "client_code")
    private String clientCode;

    /**
     * 令牌号
     */
    @Column(name = "token_code")
    private String tokenCode;

    /**
     * agvCallback TCP 协议必传，REST 协议不用传
     */
    @Column(name = "interface_name")
    private String interfaceName;

    /**
     * 方法名, 可使用任务类型做为方法名
     */
    private String method;

    /**
     * 当前任务单号
     */
    @Column(name = "task_code")
    private String taskCode;

    /**
     * 工作位
     */
    @Column(name = "wb_code")
    private String wbCode;

    /**
     * 货架编号
     */
    @Column(name = "pod_code")
    private String podCode;

    /**
     * 货架所在区域编号
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 物料批次
     */
    @Column(name = "material_lot")
    private String materialLot;

    /**
     * 当前位置编号
     */
    @Column(name = "current_position_code")
    private String currentPositionCode;

    /**
     * 自定义字段
     */
    private String data;

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
     * 获取请求编号
     *
     * @return req_code - 请求编号
     */
    public String getReqCode() {
      return reqCode;
    }

    /**
     * 设置请求编号
     *
     * @param reqCode 请求编号
     */
    public void setReqCode(String reqCode) {
      this.reqCode = reqCode == null ? null : reqCode.trim();
    }

    /**
     * 获取请求时间戳
     *
     * @return req_time - 请求时间戳
     */
    public String getReqTime() {
      return reqTime;
    }

    /**
     * 设置请求时间戳
     *
     * @param reqTime 请求时间戳
     */
    public void setReqTime(String reqTime) {
      this.reqTime = reqTime == null ? null : reqTime.trim();
    }

    /**
     * 获取客户端编号
     *
     * @return client_code - 客户端编号
     */
    public String getClientCode() {
      return clientCode;
    }

    /**
     * 设置客户端编号
     *
     * @param clientCode 客户端编号
     */
    public void setClientCode(String clientCode) {
      this.clientCode = clientCode == null ? null : clientCode.trim();
    }

    /**
     * 获取令牌号
     *
     * @return token_code - 令牌号
     */
    public String getTokenCode() {
      return tokenCode;
    }

    /**
     * 设置令牌号
     *
     * @param tokenCode 令牌号
     */
    public void setTokenCode(String tokenCode) {
      this.tokenCode = tokenCode == null ? null : tokenCode.trim();
    }

    /**
     * 获取agvCallback TCP 协议必传，REST 协议不用传
     *
     * @return interface_name - agvCallback TCP 协议必传，REST 协议不用传
     */
    public String getInterfaceName() {
      return interfaceName;
    }

    /**
     * 设置agvCallback TCP 协议必传，REST 协议不用传
     *
     * @param interfaceName agvCallback TCP 协议必传，REST 协议不用传
     */
    public void setInterfaceName(String interfaceName) {
      this.interfaceName = interfaceName == null ? null : interfaceName.trim();
    }

    /**
     * 获取方法名, 可使用任务类型做为方法名
     *
     * @return method - 方法名, 可使用任务类型做为方法名
     */
    public String getMethod() {
      return method;
    }

    /**
     * 设置方法名, 可使用任务类型做为方法名
     *
     * @param method 方法名, 可使用任务类型做为方法名
     */
    public void setMethod(String method) {
      this.method = method == null ? null : method.trim();
    }

    /**
     * 获取当前任务单号
     *
     * @return task_code - 当前任务单号
     */
    public String getTaskCode() {
      return taskCode;
    }

    /**
     * 设置当前任务单号
     *
     * @param taskCode 当前任务单号
     */
    public void setTaskCode(String taskCode) {
      this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    /**
     * 获取工作位
     *
     * @return wb_code - 工作位
     */
    public String getWbCode() {
      return wbCode;
    }

    /**
     * 设置工作位
     *
     * @param wbCode 工作位
     */
    public void setWbCode(String wbCode) {
      this.wbCode = wbCode == null ? null : wbCode.trim();
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
     * 获取货架所在区域编号
     *
     * @return area_code - 货架所在区域编号
     */
    public String getAreaCode() {
      return areaCode;
    }

    /**
     * 设置货架所在区域编号
     *
     * @param areaCode 货架所在区域编号
     */
    public void setAreaCode(String areaCode) {
      this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取物料批次
     *
     * @return material_lot - 物料批次
     */
    public String getMaterialLot() {
      return materialLot;
    }

    /**
     * 设置物料批次
     *
     * @param materialLot 物料批次
     */
    public void setMaterialLot(String materialLot) {
      this.materialLot = materialLot == null ? null : materialLot.trim();
    }

    /**
     * 获取当前位置编号
     *
     * @return current_position_code - 当前位置编号
     */
    public String getCurrentPositionCode() {
      return currentPositionCode;
    }

    /**
     * 设置当前位置编号
     *
     * @param currentPositionCode 当前位置编号
     */
    public void setCurrentPositionCode(String currentPositionCode) {
      this.currentPositionCode = currentPositionCode == null ? null : currentPositionCode.trim();
    }

    /**
     * 获取自定义字段
     *
     * @return data - 自定义字段
     */
    public String getData() {
      return data;
    }

    /**
     * 设置自定义字段
     *
     * @param data 自定义字段
     */
    public void setData(String data) {
    this.data = data == null ? null : data.trim();
  }
}
