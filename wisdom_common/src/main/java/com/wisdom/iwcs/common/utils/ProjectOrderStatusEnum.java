package com.wisdom.iwcs.common.utils;

/**
 * 工程订单状态枚举类
 * <p>
 * 新建状态（0，INIT）---》
 * 已通知量房状态(2,house_measure_notified)--》
 * 量房数据已提交(3,house_measure_data_submit)--》
 * 量房数据驳回(4,house_measure_data_rejected)--》
 * 量房数据通过(5,house_measure_data_passed)--》
 * 预算编制数据生成并已提交(6,bugetdate_generated) --》
 * 正式开工(7,project_start)-》
 * 完工(8,project_finished)-》
 * 待决算（9，ready_Final accounts）-》
 * 已决算（10，Final accounts）
 *
 * @author Ted.Ma
 * @version <br>
 * <p>类的描述</p>
 */
public enum ProjectOrderStatusEnum {
    INIT(0, "初建立"), HOUSE_MEASURE_NOTIFIED(2, "已通知量房"), HOUSE_MEASURE_DATA_SUBMIT(3, "量房数据已提交"), HOUSE_MEASURE_DATA_REJECTED(4, "量房数据驳回"), HOUSE_MEASURE_DATA_PASSED(5, "量房数据通过"), BUGETDATE_GENERATED(6, "预算编制数据生成并已提交"), PROJECT_START(7, "正式开工"), project_finished(8, "工程结束"), READY_FINAL_ACCOUNTS(9, "待决算"), FINAL_ACCOUNTS(10, "工程结束");
    private int status;
    private String message;

    ProjectOrderStatusEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }


    @Override
    public String toString() {
        return String.valueOf(this.status);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {

        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ProjectOrderStatusEnum getByStatus(int status) {
        ProjectOrderStatusEnum[] enums = ProjectOrderStatusEnum.values();
        for (ProjectOrderStatusEnum projectOrderStatusEnum : enums) {
            if (projectOrderStatusEnum.getStatus() == status) {
                return projectOrderStatusEnum;
            }
        }
        return null;
    }

    public static boolean containsStatus(int status) {
        ProjectOrderStatusEnum statusEnum = getByStatus(status);
        return statusEnum != null;
    }

    /**
     * 获得描述字符串--用于记录log
     *
     * @param status
     * @return
     */
    public String getDisplayStr() {
        return "{ 'status': '" + this.status + "';'" + "'message':'" + this.getMessage() + "'}";
    }


}
