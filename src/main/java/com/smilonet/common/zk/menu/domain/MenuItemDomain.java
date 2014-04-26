/**
 * Copyright 2010 the original author or authors.
 * 
 * This file is part of Zksample2. http://zksample2.sourceforge.net/
 *
 * Zksample2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Zksample2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Zksample2.  If not, see <http://www.gnu.org/licenses/gpl.html>.
 */
package com.smilonet.common.zk.menu.domain;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * @author bbruhns
 * 
 */
public class MenuItemDomain implements IMenuDomain {
	private String id;

	private String zulNavigation;
	private String label;

	private String requiredPermission = null;

	private Boolean withOnClickAction = null;

	private String iconName;

	/**
	 * @return the iconName
	 */
	@XmlAttribute
	public String getIconName() {
		return this.iconName;
	}

	/**
	 * @return the id
	 */
	@XmlAttribute(required = true)
	public String getId() {
		return this.id;
	}

	/**
	 * @return the label
	 */
	@XmlAttribute
	public String getLabel() {
		return this.label;
	}

	/**
	 * @return the requiredPermission
	 */
	@XmlAttribute
	public String getRequiredPermission() {
		return this.requiredPermission;
	}

	/**
	 * @return the zulNavigation
	 */
	@XmlAttribute
	public String getZulNavigation() {
		return this.zulNavigation;
	}

	/**
	 * @return the withOnClickAction
	 */
	@XmlAttribute
	public Boolean isWithOnClickAction() {
		return this.withOnClickAction;
	}

	/**
	 * @param iconName
	 *            the iconName to set
	 */
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @param requiredPermission
	 *            the requiredPermission to set
	 */
	public void setRequiredPermission(String requiredPermission) {
		this.requiredPermission = requiredPermission;
	}

	/**
	 * @param withOnClickAction
	 *            the withOnClickAction to set
	 */
	public void setWithOnClickAction(Boolean withOnClickAction) {
		this.withOnClickAction = withOnClickAction;
	}

	/**
	 * @param zulNavigation
	 *            the zulNavigation to set
	 */
	public void setZulNavigation(String zulNavigation) {
		this.zulNavigation = zulNavigation;
	}
}
