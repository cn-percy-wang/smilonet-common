package com.smilonet.common.entity;

import java.util.Set;

/**
 * Interface marks class which is inheritable. There is unlimited number of
 * levels. Parent must have the same type as child.
 * 
 * @param <T>
 *            parent's type, must be the same as child's type
 * 
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public interface IInheritable<E> {

    /**
     * Property which represents parent.
     */
    String P_PARENT = "parent";

    /**
     * Get object's parent.
     * 
     * @return parent
     */
    E getParent();

    /**
     * Set object's parent. Null means root level object (no parent).
     * 
     * @param parent
     *            parent
     */
    void setParent(E parent);

    Set<E> getChildren();

}
