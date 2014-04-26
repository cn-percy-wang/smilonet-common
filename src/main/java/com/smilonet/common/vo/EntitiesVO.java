///////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2011 smilonet.
// All rights reserved
///////////////////////////////////////////////////////////////////////////////////////////////////
package com.smilonet.common.vo;

import java.util.List;

/**
 * @author 王龙 email:wanglong(a)smilonet.com
 * @version 1.0
 * @since 1.0
 */
public class EntitiesVO<E> extends BaseVO {

    private static final long serialVersionUID = 4807295835543465920L;

    private List<E> data;

    public EntitiesVO() {
        super(SUCCESS);
    }

    public EntitiesVO(List<E> data) {
        super(SUCCESS);
        this.data = data;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

}
