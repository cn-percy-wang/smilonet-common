package com.smilonet.common.entity;

/**
 * Interface marks class which have default flag. DAO will check if there is
 * only one default object using <code>getExample()</code> method.
 * 
 * @author Maciej Szczytowski <mszczytowski-genericdao@gmail.com>
 * @since 1.0
 */
public interface IDefaultable {

    /**
     * Property which represents default flag.
     */
    String P_IS_DEFAULT = "isDefault";

    /**
     * Check if object is default one.
     * 
     * @return true when object is default one
     */
    boolean isDefault();

    /**
     * Set object as default one.
     * 
     * @param isDefault
     *            value of default flag
     * @see #getExample()
     */
    void setDefault(boolean isDefault);

    /**
     * Get examplary object using in <code>IDao.setAsDefault()</code> method.
     * 
     * Examplary object should have setted only this properties which are
     * commons in group with one default object, for example parent or category.
     * 
     * If method returns null none of entities will be changed into not default.
     * 
     * @see IDao#setAsDefault(IDefaultable)
     * @return examplary object
     */
    IPersistable getExample();
}
