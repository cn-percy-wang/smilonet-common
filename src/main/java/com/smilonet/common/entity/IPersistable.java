package com.smilonet.common.entity;

import java.io.Serializable;

/**
 * Interface marks class which can be persisted.
 * 
 * @param <I>
 *            type of primary key, it must be serializable
 * 
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public interface IPersistable<ID extends Serializable> extends Serializable {

    /**
     * Property which represents id.
     */
    String P_ID = "id";

    /**
     * Property which represents id.
     */
    String P_VERSION = "version";

    /**
     * Get primary key.
     * 
     * @return primary key
     */
    ID getId();

    /**
     * Set primary key.
     * 
     * @param id
     *            primary key
     */
    void setId(ID id);

}
