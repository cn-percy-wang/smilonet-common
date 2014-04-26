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

import org.zkoss.zk.ui.Component;

import com.smilonet.common.zk.menu.ILabelElement;

/**
 * @author bbruhns
 * 
 */
public class MenuFactoryDto {

	public MenuFactoryDto(Component parent, ILabelElement node) {
		super();
		this.parent = parent;
		this.node = node;
	}

	public MenuFactoryDto(ILabelElement node) {
		this(node, node);
	}

	/**
	 * @return the parent
	 */
	public Component getParent() {
		return this.parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Component parent) {
		this.parent = parent;
	}

	/**
	 * @return the node
	 */
	public ILabelElement getNode() {
		return this.node;
	}

	/**
	 * @param node
	 *            the node to set
	 */
	public void setNode(ILabelElement node) {
		this.node = node;
	}

	private Component parent;
	private ILabelElement node;

}
