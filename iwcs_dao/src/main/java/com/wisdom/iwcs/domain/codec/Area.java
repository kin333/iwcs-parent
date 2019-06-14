package com.wisdom.iwcs.domain.codec;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "codec_area")
public class Area {
    @Id
    private Integer id;

    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 父级id
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 级别：0，国家；1，省；2市；3.区；4街道；5；居委会
     */
    @Column(name = "area_level")
    private Integer areaLevel;

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
     * 获取代码
     *
     * @return code - 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取父级id
     *
     * @return parent_id - 父级id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级id
     *
     * @param parentId 父级id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取级别：0，国家；1，省；2市；3.区；4街道；5；居委会
     *
     * @return area_level - 级别：0，国家；1，省；2市；3.区；4街道；5；居委会
     */
    public Integer getAreaLevel() {
        return areaLevel;
    }

    /**
     * 设置级别：0，国家；1，省；2市；3.区；4街道；5；居委会
     *
     * @param areaLevel 级别：0，国家；1，省；2市；3.区；4街道；5；居委会
     */
    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }
}