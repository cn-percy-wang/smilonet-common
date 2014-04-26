/**
 * Copyright 2010 the original author or authors.
 * 
 * This file is part of Zksample2. http://zksample2.sourceforge.net/
 * 
 * Zksample2 is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * Zksample2 is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Zksample2. If not, see <http://www.gnu.org/licenses/gpl.html>.
 */
package com.smilonet.common.zk.menu.tree;

import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Treecell;

import com.smilonet.common.utils.StringUtils;
import com.smilonet.common.zk.menu.ILabelElement;

/**
 * @author bbruhns
 * @changes 12/10/2009:sge opens the zul-file window in a tab.<br>
 * 
 */

@Data
@Slf4j
public class DefaultTreecell extends Treecell implements EventListener<Event>, Serializable, ILabelElement {

	private static final long serialVersionUID = 5221385297281381652L;

	private String zulNavigation;

	@Override
	public void onEvent(Event event) throws Exception {

		try {

			/* get an instance of the borderlayout defined in the zul-file */
			final Borderlayout mainBorderlayout = (Borderlayout) Path.getComponent("/indexMainWindow/indexMainBorderlayout");
			/* get an instance of the searched CENTER layout area */
			final Center center = mainBorderlayout.getCenter();

			final Tabs indexTabs = (Tabs) center.getFellow("indexTabBox").getFellow("indexTabs");

			// Check if the tab is already open, if not than create them
			Tab checkTab = null;
			try {
				checkTab = (Tab) indexTabs.getFellow("tab_" + this.getId());
				checkTab.setSelected(true);
			} catch (final ComponentNotFoundException ex) {
				// Ignore if can not get tab.
			}

			if (checkTab == null) {

				final Tab tab = new Tab();
				tab.setId("tab_" + this.getId());
				tab.setLabel(this.getLabel().trim());
				tab.setClosable(true);

				tab.setParent(indexTabs);

				final Tabpanels indexTabpanels = (Tabpanels) center.getFellow("indexTabBox").getFellow("indexTabs").getFellow("indexTabpanels");
				final Tabpanel tabpanel = new Tabpanel();
				tabpanel.setHeight("100%");
				tabpanel.setStyle("padding: 0px;");
				tabpanel.setParent(indexTabpanels);

				Executions.createComponents(getZulNavigation(), tabpanel, null);
				tab.setSelected(true);
			}

			if (log.isDebugEnabled()) {
				log.debug("-->[" + getId() + "] calling zul-file: " + getZulNavigation());
			}
		} catch (final Exception e) {
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

