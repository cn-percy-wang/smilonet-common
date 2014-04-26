package com.smilonet.common.zk;

import java.io.Serializable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Executions;

/**
 * Util class for setting/reading cookies.<br>
 * 
 * @author sge
 * 
 */
public class ZkCookieUtils implements Serializable {

    private static final long serialVersionUID = 1L;

    public ZkCookieUtils() {
    }

    public static void setCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        ((HttpServletResponse) Executions.getCurrent().getNativeResponse()).addCookie(cookie);
    }

    public static String getCookie(String name) {
        Cookie[] cookies = ((HttpServletRequest) Executions.getCurrent().getNativeRequest()).getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void getAllCookies() {
        Cookie[] cookies = ((HttpServletRequest) Executions.getCurrent().getNativeRequest()).getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + " = " + cookie.getValue());
            }
        }

    }

}
