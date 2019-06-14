package com.wisdom.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Object to return as body in JWT Authentication
 */
public class JWTToken {
    private String idToken;

    private String socketToken;

    public JWTToken(String idToken) {
        this.idToken = idToken;
    }

    public JWTToken(String idToken, String socketToken) {
        this.idToken = idToken;
        this.socketToken = socketToken;
    }

    @JsonProperty("id_token")
    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getSocketToken() {
        return socketToken;
    }

    public void setSocketToken(String socketToken) {
        this.socketToken = socketToken;
    }
}
