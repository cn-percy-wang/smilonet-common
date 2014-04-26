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
package com.smilonet.common.zk.menu;

import java.io.Serializable;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;

import com.smilonet.common.utils.StringUtils;
import com.smilonet.common.zk.UserWorkspace;
import com.smilonet.common.zk.menu.domain.IMenuDomain;
import com.smilonet.common.zk.menu.domain.MenuDomain;
import com.smilonet.common.zk.menu.domain.MenuFactoryDto;
import com.smilonet.common.zk.menu.domain.MetaMenuFactory;

/**
 * @author bbruhns
 * 
 */
abstract public class MenuFactory implements Serializable {

	private static final long serialVersionUID = 142621423557135573L;
	private final Log loger = LogFactory.getLog(getClass());

	final private LinkedList<Component> stack;
	final private UserWorkspace userWorkspace;

	protected MenuFactory(UserWorkspace userWorkspace, Component component, URL url) {
		this(userWorkspace, component, MetaMenuFactory.getRootMenuDomain(url).getItems());
	}

	protected MenuFactory(UserWorkspace userWorkspace, Component component, List<IMenuDomain> items) {
		super();
		this.userWorkspace = userWorkspace;

		assert component != null : "Parent component is null!";
		assert this.userWorkspace != null : "No UserWorkspace exists!";

		this.stack = new LinkedList<Component>();
		push(component);

		createMenu(items);
	}

	protected void createMenu(List<IMenuDomain> items) {
		if (items.isEmpty()) {
			return;
		}
		for (final IMenuDomain menuDomain : items) {
			if (menuDomain instanceof MenuDomain) {
				final MenuDomain menu = (MenuDomain) menuDomain;
				if (addSubMenuImpl(menu)) {
					createMenu(menu.getItems());
					ebeneHoch();
				}
			} else {
				addItemImpl(menuDomain);
			}
		}
	}

	protected void addItemImpl(IMenuDomain itemDomain) {
		if (isAllowed(itemDomain)) {
			setAttributes(itemDomain, createItemComponent(getCurrentComponent()));
		}
	}

	abstract protected ILabelElement createItemComponent(Component parent);

	protected boolean addSubMenuImpl(MenuDomain menu) {
		if (isAllowed(menu)) {
			final MenuFactoryDto dto = createMenuComponent(getCurrentComponent(), menu.isOpen());

			setAttributes(menu, dto.getNode());

			push(dto.getParent());

			return true;
		}
		return false;
	}

	abstract protected MenuFactoryDto createMenuComponent(Component parent, boolean open);

	protected boolean isAllowed(IMenuDomain treecellValue) {
		return isAllowed(treecellValue.getRequiredPermission());
	}

	protected void ebeneHoch() {
		poll();
	}

	protected Component getCurrentComponent() {
		return peek();
	}

	protected UserWorkspace getUserWorkspace() {
		return this.userWorkspace;
	}

	protected boolean isAllowed(String requiredPermission) {
		if (StringUtils.isEmpty(requiredPermission)) {
			return true;
		}
		return getUserWorkspace().isAllowed(requiredPermission);
	}

	protected Component peek() {
		return this.stack.peek();
	}

	protected Component poll() {
		try {
			return this.stack.poll();
		} finally {
			if (this.stack.isEmpty()) {
				throw new RuntimeException("Root no longer exists!");
			}
		}
	}

	protected void push(Component e) {
		this.stack.push(e);
	}

	protected void setAttributes(IMenuDomain treecellValue, ILabelElement defaultTreecell) {
		if (treecellValue.isWithOnClickAction() == null || treecellValue.isWithOnClickAction().booleanValue()) {
			defaultTreecell.setZulNavigation(treecellValue.getZulNavigation());

			if (!StringUtils.isEmpty(treecellValue.getIconName())) {
				defaultTreecell.setImage(treecellValue.getIconName());
			}
		}

		setAttributesWithoutAction(treecellValue, defaultTreecell);
	}

	protected void setAttributesWithoutAction(IMenuDomain treecellValue, ILabelElement defaultTreecell) {
		assert treecellValue.getId() != null : "In indexMenu.xml file is a node who's ID is missing!";

		defaultTreecell.setId(treecellValue.getId());
		String label = treecellValue.getLabel();
		if (StringUtils.isEmpty(label)) {
			label = Labels.getLabel(treecellValue.getId());
		} else {
			label = Labels.getLabel(label);
		}
		defaultTreecell.setLabel(" " + label);
	}
}
