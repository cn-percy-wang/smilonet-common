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
package com.smilonet.common.zk.menu.dropdown;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Menupopup;

import com.smilonet.common.zk.UserWorkspace;
import com.smilonet.common.zk.menu.ILabelElement;
import com.smilonet.common.zk.menu.MenuFactory;
import com.smilonet.common.zk.menu.domain.IMenuDomain;
import com.smilonet.common.zk.menu.domain.MenuFactoryDto;

/**
 * @author bbruhns
 * 
 */
public class DropDownMenuFactory extends MenuFactory {

	private static final long serialVersionUID = -6930474675371322560L;

	public static void addMenu(UserWorkspace userWorkspace, Component component, List<IMenuDomain> items) {
		new DropDownMenuFactory(userWorkspace, component, items);
	}

	/**
	 * @param component
	 */
	private DropDownMenuFactory(UserWorkspace userWorkspace, Component component, List<IMenuDomain> items) {
		super(userWorkspace, component, items);
	}

	@Override
	protected MenuFactoryDto createMenuComponent(Component parent, boolean open) {
		final DefaultDropDownMenu menu = new DefaultDropDownMenu();
		parent.appendChild(menu);

		final Menupopup menupopup = new Menupopup();
		menu.appendChild(menupopup);

		return new MenuFactoryDto(menupopup, menu);
	}

	@Override
	protected ILabelElement createItemComponent(Component parent) {
		final DefaultDropDownMenuItem item = new DefaultDropDownMenuItem();
		parent.appendChild(item);
		return item;
	}

	@Override
	protected void setAttributes(IMenuDomain treecellValue, ILabelElement defaultTreecell) {
		super.setAttributes(treecellValue, defaultTreecell);
		defaultTreecell.setImage(treecellValue.getIconName());
	}

}
