package com.wisdom.iwcs.domain.base.dto;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/4 10:25
 */
public class BizAllowExecutePodsDetailDTO {
    /**
     * 允许执行任务货架
     */
    private List<String> allowExecuteTaskPods;
    /**
     * 不允许执行任务货架
     */
    private List<String> notAllowExecuteTaskPods;

    @Override
    public String toString() {
        return "BizAllowExecutePodsDetailDTO{" +
                "allowExecuteTaskPods=" + allowExecuteTaskPods +
                ", notAllowExecuteTaskPods=" + notAllowExecuteTaskPods +
                '}';
    }

    public List<String> getAllowExecuteTaskPods() {
        return allowExecuteTaskPods;
    }

    public void setAllowExecuteTaskPods(List<String> allowExecuteTaskPods) {
        this.allowExecuteTaskPods = allowExecuteTaskPods;
    }

    public List<String> getNotAllowExecuteTaskPods() {
        return notAllowExecuteTaskPods;
    }

    public void setNotAllowExecuteTaskPods(List<String> notAllowExecuteTaskPods) {
        this.notAllowExecuteTaskPods = notAllowExecuteTaskPods;
    }
}
