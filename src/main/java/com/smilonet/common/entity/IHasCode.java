package com.smilonet.common.entity;

import java.io.Serializable;

/**
 * 是否有code字段
 * 
 * @author WangLong
 * 
 */
public interface IHasCode extends Serializable {

    /**
     * Property which represents parent.
     */
    String P_CODE = "code";

    /**
     * 获取code生成器的id
     * 
     * @return
     */
    String getSerialId();

    /**
     * Get object's code.
     * 
     * @return parent
     */
    String getCode();

    /**
     * Set object's code.
     * 
     * @param parent
     *            parent
     */
    void setCode(String code);

}
