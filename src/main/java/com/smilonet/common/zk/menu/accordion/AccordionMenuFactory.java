package com.smilonet.common.zk.menu.accordion;

import java.net.URL;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treechildren;

import com.smilonet.common.zk.UserWorkspace;
import com.smilonet.common.zk.menu.ILabelElement;
import com.smilonet.common.zk.menu.MenuFactory;
import com.smilonet.common.zk.menu.domain.IMenuDomain;
import com.smilonet.common.zk.menu.domain.MenuDomain;
import com.smilonet.common.zk.menu.domain.MenuFactoryDto;
import com.smilonet.common.zk.menu.tree.TreeMenuFactory;

/**
 * 最顶层为according，内部为tree
 * @author WangLong
 *
 */
public class AccordionMenuFactory extends MenuFactory {

	private static final long serialVersionUID = 2392350126775231367L;

	public static void addMenu(UserWorkspace userWorkspace, Component component, URL url) {
		new AccordionMenuFactory(userWorkspace, component, url);
	}

	public static void addMenu(UserWorkspace userWorkspace, Component component, List<IMenuDomain> items) {
		new AccordionMenuFactory(userWorkspace, component, items);
	}

	protected AccordionMenuFactory(UserWorkspace userWorkspace, Component component, URL url) {
		super(userWorkspace, component, url);
	}

	protected AccordionMenuFactory(UserWorkspace userWorkspace, Component component, List<IMenuDomain> items) {
		super(userWorkspace, component, items);
	}

	@Override
	protected void createMenu(List<IMenuDomain> items) {
		if (items.isEmpty()) {
			return;
		}
		for (final IMenuDomain menuDomain : items) {
			if (menuDomain instanceof MenuDomain) {
				final MenuDomain menu = (MenuDomain) menuDomain;
				if (addSubMenuImpl(menu)) {
					TreeMenuFactory.addMenu(getUserWorkspace(), getCurrentComponent(), menu.getItems());
					ebeneHoch();
				}
			} else {
				addItemImpl(menuDomain);
			}
		}
	}

	@Override
	protected MenuFactoryDto createMenuComponent(Component parent, boolean open) {
		DefaultAccordionMenuTab menuTab = new DefaultAccordionMenuTab();
		Tabpanel menuTabpanel = new Tabpanel();
		menuTabpanel.setStyle("overflow: auto");
		
		((Tabs)(parent.getChildren().get(0))).appendChild(menuTab);
		((Tabpanels)(parent.getChildren().get(1))).appendChild(menuTabpanel);
		
		Tree menuTree = new Tree();
		menuTree.setStyle("overflow:auto; border: none;");
		menuTree.setParent(menuTabpanel);

		Treechildren menuTreechildren = new Treechildren();
		menuTree.appendChild(menuTreechildren);

		return new MenuFactoryDto(menuTreechildren, menuTab);
	}

	@Override
	protected ILabelElement createItemComponent(Component parent) {
		DefaultAccordionMenuTab menuTab = new DefaultAccordionMenuTab();
		Tabpanel menuTabpanel = new Tabpanel();
		((Tabs)(parent.getChildren().get(0))).appendChild(menuTab);
		((Tabpanels)(parent.getChildren().get(1))).appendChild(menuTabpanel);
		return menuTab;
	}

}
