package com.wl.many_steps.exception;

import com.wl.many_steps.constant.Status;
import lombok.Getter;

/**
 * author : WYH
 * e-mail : wenyuhang@qinjia001.com
 * date   : 2020/8/6 11:09
 * desc   : JSON异常
 */
@Getter
public class JsonException extends BaseException {

    public JsonException(Status status) {
        super(status);
    }

    public JsonException(Integer code, String message) {
        super(code, message);
    }
}
