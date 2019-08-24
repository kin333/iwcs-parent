package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 基础的与Mes
 * @author han
 */
@Getter
@Setter
public class MesBaseRequest<T> {
    /**
     * 请求编码
     */
    private String reqcode;
    /**
     * 请求数据
     */
    private T data;
}
