///////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2011 smilonet.
// All rights reserved
///////////////////////////////////////////////////////////////////////////////////////////////////
package com.smilonet.common.vo;

/**
 * @author 王龙 email:wanglong(a)smilonet.com
 * @version 1.0
 * @since 1.0
 */
public class EntityVO<E> extends BaseVO {

    private static final long serialVersionUID = -911670345510335958L;

    private E data;

    public EntityVO() {
        super(SUCCESS);
    }

    public EntityVO(E data) {
        super(SUCCESS);
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

}
