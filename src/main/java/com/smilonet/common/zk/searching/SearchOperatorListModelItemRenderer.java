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
package com.smilonet.common.zk.searching;

import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 * Item renderer for listitems in the listbox.
 * 
 * @author bbruhns
 * @author sgerth
 * 
 */

@Data
@Slf4j
public class SearchOperatorListModelItemRenderer implements ListitemRenderer, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public void render(Listitem item, Object data, int index) throws Exception {

        // final SearchOperators searchOp = (SearchOperators) data;
        //
        // final Listcell lc = new Listcell(searchOp.getSearchOperatorSign());
        // lc.setParent(item);
        //
        // item.setAttribute("data", data);
    }

}
