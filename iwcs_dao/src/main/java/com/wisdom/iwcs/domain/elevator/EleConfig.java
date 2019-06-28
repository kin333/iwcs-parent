package com.wisdom.iwcs.domain.elevator;

import javax.persistence.*;

@Table(name = "ele_config")
public class EleConfig {
    @Id
    private Integer id;

    @Column(name = "plc_url")
    private String plcUrl;

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
     * @return plc_url
     */
    public String getPlcUrl() {
        return plcUrl;
    }

    /**
     * @param plcUrl
     */
    public void setPlcUrl(String plcUrl) {
        this.plcUrl = plcUrl == null ? null : plcUrl.trim();
    }
}
