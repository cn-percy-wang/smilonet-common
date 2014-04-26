package com.smilonet.common.entity;

import java.io.Serializable;

/**
 * Interface marks class which cannot be deleted. If someone calls one of DAO's
 * delete methods object will be hidden instead of deleted.
 * 
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public interface IValidable<ID extends Serializable> extends IPersistable<ID> {

    /**
     * Property which represents hidden flag.
     */
    String P_IS_VALID = "isValid";

    /**
     * Check if object is hidden.
     * 
     * @return true when object is hidden
     */
    Boolean getIsValid();

    /**
     * Set object as default one.
     * 
     * @param isValid value of hidden flag
     */
    void setIsValid(Boolean isValid);
}
