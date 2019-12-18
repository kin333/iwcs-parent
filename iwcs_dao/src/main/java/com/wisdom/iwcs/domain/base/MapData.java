package com.wisdom.iwcs.domain.base;

import lombok.Data;

@Data
public class MapData {
    private String mapCode;
    private String version;

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
