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
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpSession;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import org.zkoss.zk.ui.Sessions;

import com.smilonet.common.security.Authentication;

/**
 * Workspace for the user. One workspace per userSession. <br>
 * <br>
 * Every logged in user have his own workspace. <br>
 * Here are stored several properties for the user. <br>
 * <br>
 * 1. Access the rights that the user have. <br>
 * 2. The office for that the user are logged in. <br>
 * 
 * @author bbruhns
 * @author sgerth
 * 
 */
@Data
@Slf4j
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UserWorkspace implements Serializable, DisposableBean {

    private static final long serialVersionUID = -3936210543827830197L;

    private String userLanguage;
    private String browserType;

    private Set<String> grantedPermissionSet = null;

    /**
     * Default Constructor
     */
    public UserWorkspace() {
        if (log.isDebugEnabled()) {
            log.debug("create new Workspace [" + this + "]");
        }
    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public Authentication getAuthentication() {
        return (Authentication) getSubject().getPrincipal();
    }

    /**
     * Logout with the spring-security logout action-URL.<br>
     * Therefore we make a sendRedirect() to the logout uri we <br>
     * have configured in the spring-config.br>
     */
    public void doLogout() {
        destroy();
        /* ++++++ Kills the Http session ++++++ */
         ((HttpSession) Sessions.getCurrent().getNativeSession()).invalidate();
        /* ++++++ Kills the zk session +++++ */
        Sessions.getCurrent().invalidate();

        getSubject().logout();

    }

    /**
     * Checks if a right is in the <b>granted rights</b> that the logged in user
     * have. <br>
     * 
     * @param permission
     * @return true, if the right is in the granted user rights.<br>
     * false, if the right is not granted to the user.<br>
     */
    public boolean isAllowed(String permission) {
        return true;
        // return getSubject().isPermitted(permission);
    }

    public Properties getUserLanguageProperty() {

        // // TODO only for testing. we must get the language from
        // // the users table filed
        // userLanguageProperty =
        // ApplicationWorkspace.getInstance().getPropEnglish();
        // userLanguageProperty =
        // ApplicationWorkspace.getInstance().getPropGerman();
        //
        // return userLanguageProperty;
        return null;
    }

    @Override
    public void destroy() {
        this.grantedPermissionSet = null;
        Session session = getSubject().getSession(false);
        if (session != null) {
            session.stop();
        }

        if (log.isDebugEnabled()) {
            log.debug("destroy Workspace [" + this + "]");
        }
    }

}
