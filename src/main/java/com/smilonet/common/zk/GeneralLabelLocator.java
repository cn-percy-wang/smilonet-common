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

import java.io.File;
import java.net.URL;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.util.resource.LabelLocator;
import org.zkoss.zk.ui.Sessions;

public class GeneralLabelLocator implements LabelLocator {

    private static final String MENU_FILE_NAME = "i3-label";
    private static final String MENU_FILE_SUFFIX = ".properties";
    private String context;

    /**
     * Constructor
     * 
     * @param context
     *            wam context
     */
    public GeneralLabelLocator(String context) {
        this.context = context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.zkoss.util.resource.LabelLocator#locate(java.util.Locale)
     */
    public URL locate(Locale locale) throws Exception {

        // String menu_res_filename =
        // (locale.getLanguage().equals(Locale.ITALIAN.getLanguage())) ?
        // MENU_FILE_NAME + MENU_FILE_SUFFIX
        // : MENU_FILE_NAME + "_" + locale.getLanguage() + MENU_FILE_SUFFIX;

        String menu_res_filename = "";

        if (StringUtils.isEmpty(context)) {
            // Locale.setDefault(new Locale("en", "EN"));
            context = "en_EN";
        }

        if (context.equals("en_EN")) {
            // default property-file without locale
            Sessions.getCurrent().setAttribute("px_preferred_locale", new Locale("en", "EN"));

            menu_res_filename = MENU_FILE_NAME + MENU_FILE_SUFFIX;
        } else if (context.equals("de_DE")) {
            Sessions.getCurrent().setAttribute("px_preferred_locale", new Locale("de", "DE"));

            menu_res_filename = MENU_FILE_NAME + "_" + "de_DE" + MENU_FILE_SUFFIX;
        }

        // String menu_res_filename = MENU_FILE_NAME + "_" + context +
        // MENU_FILE_SUFFIX;

        // real path
        String menu_res_path = Sessions.getCurrent().getWebApp().getRealPath("/WEB-INF/" + menu_res_filename);

        // check if the file exists
        File fmr = new File(menu_res_path);
        if (!fmr.exists())
            throw new Exception("...........");

        // return url
        return fmr.toURL();
    }
}
