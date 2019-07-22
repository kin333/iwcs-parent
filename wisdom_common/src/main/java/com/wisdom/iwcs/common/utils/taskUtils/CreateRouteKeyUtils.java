package com.wisdom.iwcs.common.utils.taskUtils;

/**
 * 生成routeKey的工具类
 */
public class CreateRouteKeyUtils {
    /**
     * 生成货架释放的routeKey
     * @return
     */
    public static String createPodRelease(String mapCode, String areaCode) {
        return "resources." + mapCode + "." + areaCode + ".pod.release";
    }
    /**
     * 生成储位释放的routeKey
     * @return
     */
    public static String createPosRelease(String mapCode, String areaCode) {
        return "resources." + mapCode + "." + areaCode + ".pos.release";
    }
}
