package com.smilonet.common.exception;

public class OperationException extends RuntimeException {

    private static final long serialVersionUID = -5501045889316388056L;

    public static final String DEFAULT_SYSTEM_EXCEPTION_CODE = "100000";

    protected String code;

    public OperationException(String message) {
        super(message);
        this.code = DEFAULT_SYSTEM_EXCEPTION_CODE;
    }
    //
    // public ServiceException(String code, String... messageParams) {
    // super(messageParams[0]);
    // this.code = code;
    // }

}
