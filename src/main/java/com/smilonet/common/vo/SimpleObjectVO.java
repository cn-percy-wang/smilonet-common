package com.smilonet.common.vo;

public class SimpleObjectVO extends BaseVO {

    private static final long serialVersionUID = -3510612883892214545L;
    private Object data;

    public SimpleObjectVO(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
