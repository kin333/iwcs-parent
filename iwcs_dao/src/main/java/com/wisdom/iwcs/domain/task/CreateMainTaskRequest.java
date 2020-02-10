package com.wisdom.iwcs.domain.task;

import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.task.dto.PublicContextDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 通用创建主任务请求类
 * @author han
 */
@Getter
@Setter
public class CreateMainTaskRequest {
    /**
     * 任务编号
     */
    private String taskCode;
    /**

     * 请求编码
     */
    private String reqCode;
    /**
     * 主任务类型
     */
    private String mainTaskType;
    /**
     * 货架号
     */
    private String podCode;
    /**
     * 站点集合
     */
    private List<String> staticViaPaths;
    /**
     * 地图编号
     */
    private String mapCode;
    /**
     * 上下文信息,里面的信息将以JSON的格式放入数据库
     */
    private PublicContextDTO context;

    /**
     * 获取点位资源
     * @param params
     * @return
     */
    public PointPodResource pointResource(ResourceLocateParams params) {
        if (staticViaPaths == null || staticViaPaths.size() <= 0) {
            throw new MesBusinessException("站点集合为空,无法执行获取点位资源");
        }
        if (params.getIndex() == null) {
            throw new MesBusinessException("获取资源时参数不能为空");
        }
        PointPodResource pointPodResource = new PointPodResource();
        pointPodResource.setPointAlias(staticViaPaths.get(params.getIndex()));
        pointPodResource.setPodCode(podCode);
        pointPodResource.setTaskCode(taskCode);
        return pointPodResource;
    }
    /**
     * 获取货架资源
     * @return
     */
    public PointPodResource podResource() {
        if (podCode == null) {
            throw new MesBusinessException("货架为空,无法执行获取货架资源");
        }
        PointPodResource pointPodResource = new PointPodResource();
        pointPodResource.setPodCode(podCode);
        pointPodResource.setTaskCode(taskCode);
        return pointPodResource;
    }

}
