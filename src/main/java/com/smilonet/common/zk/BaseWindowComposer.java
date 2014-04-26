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
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Window;

/**
 * Base controller for creating the controllers of the zul files with the
 * <b>use</b> tag.
 * 
 * @author bbruhns
 * @author sgerth
 */
public abstract class BaseWindowComposer extends Window implements AfterCompose, Serializable {

    private static final long serialVersionUID = -2179229704315045689L;
    protected transient AnnotateDataBinder binder;

    protected transient Map<String, Object> args;

    public void doOnCreateCommon(Window w) throws Exception {
        binder = new AnnotateDataBinder(w);
        binder.loadAll();
    }

    @SuppressWarnings("unchecked")
    public void doOnCreateCommon(Window w, Event fe) throws Exception {
        doOnCreateCommon(w);
        CreateEvent ce = (CreateEvent) ((ForwardEvent) fe).getOrigin();
        args = (Map<String, Object>) ce.getArg();
    }

    public BaseWindowComposer() {
        super();
        this.setStyle("body {padding 0 0;}");
    }

    @Override
    public void afterCompose() {
        processRecursive(this, this);

        // Components.wireVariables(this, this); // auto wire variables
        // Components.addForwards(this, this); // auto forward
    }

    /**
     * Go recursive through all founded components and wires all vars and added
     * all forwarders for <b>ALL window</b> components. <br>
     * 
     * @param main
     * @param child
     */
    @SuppressWarnings("unchecked")
    private void processRecursive(Window main, Window child) {
        Components.wireVariables(main, child);
        Components.addForwards(main, this);
        List<Component> winList = child.getChildren();
        for (Component window : winList) {
            if (window instanceof Window) {
                processRecursive(main, (Window) window);
            }
        }
    }

}
