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

import java.io.Serializable;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Messagebox;

import com.smilonet.common.utils.StringUtils;
import com.smilonet.common.zk.menu.ILabelElement;

class DefaultDropDownMenu extends Menu implements Serializable, ILabelElement, EventListener {

	private static final long serialVersionUID = -3196075413623639125L;

	private String zulNavigation;

	@Override
	public void onEvent(Event event) throws Exception {
		try {
			/* get an instance of the borderlayout defined in the zul-file */
			Borderlayout bl = (Borderlayout) Path.getComponent("/outerIndexWindow/borderlayoutMain");
			/* get an instance of the searched CENTER layout area */
			Center center = bl.getCenter();
			/* clear the center child comps */
			center.getChildren().clear();
			/*
			 * create the page and put it in the center layout area
			 */
			Executions.createComponents(getZulNavigation(), center, null);

		} catch (Exception e) {
			Messagebox.show(e.toString());
		}
	}

	private String getZulNavigation() {
		return this.zulNavigation;
	}

	@Override
	public void setZulNavigation(String zulNavigation) {
		this.zulNavigation = zulNavigation;
		if (!StringUtils.isEmpty(zulNavigation)) {
			addEventListener("onClick", this);
		}
	}
}
