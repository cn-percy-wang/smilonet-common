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
package com.smilonet.common.zk;

import java.io.Serializable;

import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

/**
 * Extended messagebox that can show multilined messages. <br>
 * Lines can be breaked with the \n . <br>
 * <br>
 * 
 * @changes 04.07.2009/sge extended for showing the icons <br>
 *          05.07.2009/sge added an empty line before and after the message. <br>
 *          08.07.2009/sge added for the EventListener
 * 
 * @author sgerth
 */
public class MultiLineMessageBox extends Messagebox implements Serializable {

	private static final long serialVersionUID = 1L;

	// path of the messagebox zul-template
	private transient static String _templ = "/WEB-INF/pages/util/multiLineMessageBox.zul";

	public MultiLineMessageBox() {
	}

	public static void doSetTemplate() {
		setTemplate(_templ);
	}

	/**
	 * Shows a message box and returns what button is pressed. A shortcut to
	 * show(message, null, OK, INFORMATION). <br>
	 * <br>
	 * Simple MessageBox with customizable message and title. <br>
	 * 
	 * @param message
	 *            The message to display.
	 * @param title
	 *            The title to display.
	 * @param icon
	 *            The icon to display. <br>
	 *            QUESTION = "z-msgbox z-msgbox-question"; <br>
	 *            EXCLAMATION = "z-msgbox z-msgbox-exclamation"; <br>
	 *            INFORMATION = "z-msgbox z-msgbox-imformation"; <br>
	 *            ERROR = "z-msgbox z-msgbox-error"; <br>
	 * @param buttons
	 *            MultiLineMessageBox.CANCEL<br>
	 *            MultiLineMessageBox.YES<br>
	 *            MultiLineMessageBox.NO<br>
	 *            MultiLineMessageBox.ABORT<br>
	 *            MultiLineMessageBox.RETRY<br>
	 *            MultiLineMessageBox.IGNORE<br>
	 * @param padding
	 *            true = Added an empty line before and after the message.<br>
	 * 
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static final int show(String message, String title, int buttons, String icon, boolean padding) throws InterruptedException {

		String msg = message;

		if (padding == true) {
			msg = "\n" + message + "\n\n";
		}

		if (icon.equals("QUESTION")) {
			icon = "z-msgbox z-msgbox-question";
		} else if (icon.equals("EXCLAMATION")) {
			icon = "z-msgbox z-msgbox-exclamation";
		} else if (icon.equals("INFORMATION")) {
			icon = "z-msgbox z-msgbox-imformation";
		} else if (icon.equals("ERROR")) {
			icon = "z-msgbox z-msgbox-error";
		}

		return show(msg, title, buttons, icon, 0, null);
	}

	/**
	 * Shows a message box and returns what button is pressed. A shortcut to
	 * show(message, null, OK, INFORMATION). <br>
	 * <br>
	 * Simple MessageBox with customizable message and title. <br>
	 * 
	 * @param message
	 *            The message to display.
	 * @param title
	 *            The title to display.
	 * @param icon
	 *            The icon to display. <br>
	 *            QUESTION = "z-msgbox z-msgbox-question"; <br>
	 *            EXCLAMATION = "z-msgbox z-msgbox-exclamation"; <br>
	 *            INFORMATION = "z-msgbox z-msgbox-imformation"; <br>
	 *            ERROR = "z-msgbox z-msgbox-error"; <br>
	 * @param buttons
	 *            MultiLineMessageBox.CANCEL<br>
	 *            MultiLineMessageBox.YES<br>
	 *            MultiLineMessageBox.NO<br>
	 *            MultiLineMessageBox.ABORT<br>
	 *            MultiLineMessageBox.RETRY<br>
	 *            MultiLineMessageBox.IGNORE<br>
	 * @param padding
	 *            true = Added an empty line before and after the message.<br>
	 * 
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public static final int show(String message, String title, int buttons, String icon, boolean padding, EventListener listener) throws InterruptedException {

		String msg = message;

		if (padding == true) {
			msg = "\n" + message + "\n\n";
		}

		if (icon.equals("QUESTION")) {
			icon = "z-msgbox z-msgbox-question";
		} else if (icon.equals("EXCLAMATION")) {
			icon = "z-msgbox z-msgbox-exclamation";
		} else if (icon.equals("INFORMATION")) {
			icon = "z-msgbox z-msgbox-imformation";
		} else if (icon.equals("ERROR")) {
			icon = "z-msgbox z-msgbox-error";
		}

		return show(msg, title, buttons, icon, 0, listener);
	}

}