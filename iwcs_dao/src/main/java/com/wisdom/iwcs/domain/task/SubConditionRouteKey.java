package com.wisdom.iwcs.domain.task;

import javax.persistence.*;

@Table(name = "sub_condition_route_key")
public class SubConditionRouteKey {
    @Id
    private Integer id;

    /**
     * routeKey中文名
     */
    @Column(name = "route_key_name")
    private String routeKeyName;

    /**
     * routeKey代码
     */
    @Column(name = "route_key_code")
    private String routeKeyCode;

    /**
     * routeKey
     */
    @Column(name = "route_key")
    private String routeKey;

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
     * 获取routeKey中文名
     *
     * @return route_key_name - routeKey中文名
     */
    public String getRouteKeyName() {
        return routeKeyName;
    }

    /**
     * 设置routeKey中文名
     *
     * @param routeKeyName routeKey中文名
     */
    public void setRouteKeyName(String routeKeyName) {
        this.routeKeyName = routeKeyName == null ? null : routeKeyName.trim();
    }

    /**
     * 获取routeKey代码
     *
     * @return route_key_code - routeKey代码
     */
    public String getRouteKeyCode() {
        return routeKeyCode;
    }

    /**
     * 设置routeKey代码
     *
     * @param routeKeyCode routeKey代码
     */
    public void setRouteKeyCode(String routeKeyCode) {
        this.routeKeyCode = routeKeyCode == null ? null : routeKeyCode.trim();
    }

    /**
     * 获取routeKey
     *
     * @return route_key - routeKey
     */
    public String getRouteKey() {
        return routeKey;
    }

    /**
     * 设置routeKey
     *
     * @param routeKey routeKey
     */
    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey == null ? null : routeKey.trim();
    }
}