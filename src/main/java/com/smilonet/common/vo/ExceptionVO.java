///////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2011 smilonet.
// All rights reserved
///////////////////////////////////////////////////////////////////////////////////////////////////
package com.smilonet.common.vo;

import java.io.Serializable;

/**
 * 系统异常信息返回封装
 * 
 * @author 王龙 email:wanglong(a)smilonet.com
 * @version 1.0
 * @since 1.0
 */
public class ExceptionVO extends BaseVO {

    private static final long serialVersionUID = -5940400357727396484L;

    private static final String SYSTEM_EXCEPTION_MESSAGE_CODE = "10000";

    private ExceptionInfo exceptionInfo;

    public ExceptionVO(Throwable throwable) {
        this.exceptionInfo = new ExceptionInfo(SYSTEM_EXCEPTION_MESSAGE_CODE, throwable.getMessage());
    }

    public ExceptionVO(Throwable throwable, Object extraInfo) {
        this.exceptionInfo = new ExceptionInfo(SYSTEM_EXCEPTION_MESSAGE_CODE, throwable.getMessage(), extraInfo);
    }

    /**
     * @author 王龙 email:wanglong(a)smilonet.com
     * @version 1.0
     * @since 1.0
     */
    private static class ExceptionInfo implements Serializable {

        private static final long serialVersionUID = 5487095384864194142L;

        private String exceptionCode;

        private String exceptionMessage;

        private Object extraInfo;

        public ExceptionInfo(String exceptionCode, String exceptionMessage) {
            this(exceptionCode, exceptionMessage, null);
        }

        public ExceptionInfo(String exceptionCode, String exceptionMessage, Object extraInfo) {
            super();
            this.exceptionCode = exceptionCode;
            this.exceptionMessage = exceptionMessage;
            this.extraInfo = extraInfo;
        }

        public String getExceptionCode() {
            return exceptionCode;
        }

        public void setExceptionCode(String exceptionCode) {
            this.exceptionCode = exceptionCode;
        }

        public String getExceptionMessage() {
            return exceptionMessage;
        }

        public void setExceptionMessage(String exceptionMessage) {
            this.exceptionMessage = exceptionMessage;
        }
    }

    public ExceptionInfo getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(ExceptionInfo exceptionInfo) {
        this.exceptionInfo = exceptionInfo;
    }

}
