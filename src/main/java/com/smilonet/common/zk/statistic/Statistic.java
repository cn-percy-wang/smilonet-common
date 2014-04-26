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
package com.smilonet.common.zk.statistic;

import java.io.Serializable;
import java.util.List;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.Monitor;

/**
 * An implementation of {@link Monitor} to accumulate statistic data in memory.
 * 
 * <p>
 * It has no effect until you specify it in WEB-INF/zk.xml.
 * 
 * @author tomyeh
 * @changes 16.07.2009 / addapted for static access to the values. <br>
 */
public class Statistic implements Monitor, Serializable {

    private static final long serialVersionUID = 1L;
    private transient final long _startTime;
    private transient int _nsess, _actsess, _ndt, _actdt, _nupd, _actupd;

    // new
    private static Statistic stat;

    public Statistic() {
        _startTime = System.currentTimeMillis();
        stat = this;
    }

    public static Statistic getStatistic() {
        return stat;
    }

    public double getRuningHours() {
        long v = System.currentTimeMillis() - getStartTime();
        return ((double) v) / 3600000;
    }

    /**
     * Returns when the server (actually, this monitor) started.
     */
    public long getStartTime() {
        return _startTime;
    }

    /**
     * Returns the total number of sessions that have been created since the
     * server started.
     */
    public int getTotalSessionCount() {
        return _nsess;
    }

    /**
     * Returns the number of active sessions.
     */
    public int getActiveSessionCount() {
        return _actsess;
    }

    /**
     * Returns the average number of sessions being created in an hour.
     */
    public double getAverageSessionCount() {
        return _nsess / getEscapedHours();
    }

    /**
     * Returns the total number of desktops that have been created since the
     * server started.
     */
    public int getTotalDesktopCount() {
        return _ndt;
    }

    /**
     * Returns the number of active desktops.
     */
    public int getActiveDesktopCount() {
        return _actdt;
    }

    /**
     * Returns the average number of desktops being created in an hour.
     */
    public double getAverageDesktopCount() {
        return _ndt / getEscapedHours();
    }

    /**
     * Returns the total number of asynchronous updates that have been received
     * since the server started.
     */
    public int getTotalUpdateCount() {
        return _nupd;
    }

    /**
     * Returns the number of active asynchronous updates.
     */
    public int getActiveUpdateCount() {
        return _actupd;
    }

    /**
     * Returns the average number of asynchronous updates being created in an
     * hour.
     */
    public double getAverageUpdateCount() {
        return _nupd / getEscapedHours();
    }

    /**
     * Returns how many hours escaped since the server started.
     */
    private double getEscapedHours() {
        long v = System.currentTimeMillis() - _startTime;
        return ((double) v) / 3600000;
    }

    // -- Monitor --//
    synchronized public void sessionCreated(Session sess) {
        ++_nsess;
        ++_actsess;
    }

    synchronized public void sessionDestroyed(Session sess) {
        --_actsess;
    }

    synchronized public void desktopCreated(Desktop desktop) {
        ++_ndt;
        ++_actdt;
    }

    synchronized public void desktopDestroyed(Desktop desktop) {
        --_actdt;
    }

    synchronized public void beforeUpdate(Desktop desktop, List requests) {
        ++_nupd;
        ++_actupd;
    }

    synchronized public void afterUpdate(Desktop desktop) {
        --_actupd;
    }
}
