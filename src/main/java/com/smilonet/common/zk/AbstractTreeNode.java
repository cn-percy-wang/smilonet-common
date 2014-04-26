package com.smilonet.common.zk;

import java.io.Serializable;

public abstract class AbstractTreeNode<E> implements Serializable {

    private static final long serialVersionUID = -6524451097969867490L;

    protected E data;

    public AbstractTreeNode(E data) {
        this.data = data;
    }

    public abstract boolean isLeaf();

    public abstract boolean isOpen();

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
