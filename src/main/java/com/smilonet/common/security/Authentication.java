package com.smilonet.common.security;

import java.io.Serializable;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class Authentication implements Serializable {

    private static final long serialVersionUID = -1748602382963711884L;
    private String id;
    private String name;

    public Authentication(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return name + "(id:" + id + ")";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
