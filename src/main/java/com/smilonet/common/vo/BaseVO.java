///////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2011 smilonet.
// All rights reserved
///////////////////////////////////////////////////////////////////////////////////////////////////
package com.smilonet.common.vo;

import java.io.Serializable;

/**
 * @author 王龙 email:wanglong(a)smilonet.com
 * @version 1.0
 * @since 1.0
 */
public class BaseVO implements Serializable {
    private static final long serialVersionUID = -431654657299507461L;

    public static final String SUCCESS = "0";

    protected String code;
    protected String message;

    public BaseVO() {
        this(SUCCESS, "操作成功");
    }

    public BaseVO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseVO(String code) {
        this(code, null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
