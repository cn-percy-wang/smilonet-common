package com.smilonet.common.spring;

public class ViewModel {

    private String code;
    private String message;
    private String userId;
    private Object data;

    public ViewModel() {
        super();
    }

    public ViewModel(Object data) {
        super();
        this.code = "0";
        this.data = data;
    }

    public ViewModel(String code, String userId) {
        super();
        this.code = code;
        this.userId = userId;
    }

    public ViewModel(String code, String message, String userId) {
        super();
        this.code = code;
        this.message = message;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
