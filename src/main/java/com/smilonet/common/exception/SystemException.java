package com.smilonet.common.exception;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -4443463540744514295L;

    public static final String DEFAULT_SYSTEM_EXCEPTION_CODE = "100000";

    protected String code;

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
        this.code = DEFAULT_SYSTEM_EXCEPTION_CODE;
    }
    //
    // public ServiceException(String code, String... messageParams) {
    // super(messageParams[0]);
    // this.code = code;
    // }

}
