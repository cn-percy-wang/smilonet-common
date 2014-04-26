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
import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.Resource;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.Window;

/**
 * Base controller for creating the controllers of the zul files with the spring
 * framework.
 * 
 * @changes 05/18/2010 sge cleaned up from old stuff.
 * 
 * @author bbruhns
 * @author sgerth
 */
@Data
@EqualsAndHashCode(callSuper = true)
abstract public class BaseCtrl<T extends Component> extends SelectorComposer<T> implements Serializable, EventListener<Event> {

    private static final long serialVersionUID = -1171206258809472640L;

    protected transient Map<String, Object> args;

    /**
     * Get the params map that are overhanded at creation time. <br>
     * Reading the params that are binded to the createEvent.<br>
     * 
     * @param event
     * @return params map
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCreationArgsMap(Event event) {
        final CreateEvent ce = (CreateEvent) ((ForwardEvent) event).getOrigin();
        return (Map<String, Object>) ce.getArg();
    }

    @SuppressWarnings("unchecked")
    public void doOnCreateCommon(Window w, Event fe) throws Exception {
        final CreateEvent ce = (CreateEvent) ((ForwardEvent) fe).getOrigin();
        this.args = (Map<String, Object>) ce.getArg();
    }

    @Resource
    private transient UserWorkspace userWorkspace;
    
    /**
     * Workaround! Do not use it otherwise!
     */
    @Override
    public void onEvent(Event evt) throws Exception {
        // final Object controller = getController();
        // final Method mtd =
        // ComponentsCtrl.getEventMethod(controller.getClass(), evt.getName());
        //
        // if (mtd != null) {
        // isAllowed(mtd);
        // }
    }

    /**
     * With this method we get the @Secured Annotation for a method.<br>
     * Captured the method call and check if it's allowed. <br>
     * sample: @Secured({"permission"}) <br>
     * 
     * <pre>
     * @Secured({ "button_BranchMain_btnNew" })
     * public void onClick$btnNew(Event event) throws Exception {
     *   [...]
     * }
     * </pre>
     * 
     * @param mtd
     * @exception SecurityException
     */
    private void isAllowed(Method mtd) {
        // final Annotation[] annotations = mtd.getAnnotations();
        // for (final Annotation annotation : annotations) {
        // if (annotation instanceof Secured) {
        // final Secured secured = (Secured) annotation;
        // for (final String permission : secured.value()) {
        // if (!this.userWorkspace.isAllowed(permission)) {
        // throw new
        // SecurityException("Call of this method is not allowed! Missing right: \n\n"
        // + "needed Permission: " + permission + "\n\n" + "Method: " + mtd);
        // }
        // }
        // return;
        // }
        // }
    }

}
