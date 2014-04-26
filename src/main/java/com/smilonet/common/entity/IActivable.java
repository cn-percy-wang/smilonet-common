package com.smilonet.common.entity;

/**
 * Interface marks class which can be enabled or disabled.
 * 
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public interface IActivable {

    /**
     * Property which represents active flag.
     */
    String P_IS_ACTIVE = "isActive";

    /**
     * Check if object is active.
     * 
     * @return true when object is active
     */
    boolean isActive();

    /**
     * Set object's active flag.
     * 
     * @param isActive
     *            value of active flag
     */
    void setActive(boolean isActive);
}
