package org.xi.quick.common.model;

import org.xi.quick.common.constant.OperationConstants;

import lombok.*;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseVo<T> implements Serializable {

    public ResponseVo(T data) {
        this(true, 0, null, data);
    }

    public ResponseVo(OperationConstants constants) {
        this(false, constants.getCode(), constants.getMessage(), null);
    }

    public ResponseVo(OperationConstants constants, Object extData) {
        this(false, constants.getCode(), constants.getMessage(), null, extData);
    }

    public ResponseVo(boolean success, int code, String msg) {
        this(success, code, msg, null, null);
    }

    public ResponseVo(boolean success, int code, String msg, T data) {
        this(success, code, msg, data, null);
    }

    public ResponseVo(boolean success, int code, String msg, T data, Object extData) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.extData = extData;
    }

    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 状态码
     */
    private int code;
    /**
     * 信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 额外数据
     */
    private Object extData;
}
