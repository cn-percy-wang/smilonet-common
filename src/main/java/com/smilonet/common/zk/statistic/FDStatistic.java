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

/**
 * @author bbruhns
 * 
 */
public final class FDStatistic {

    public static FDStatistic getStatistic() {
        return FDStatisticMonitor.getStatistic();
    }

    FDStatistic(long startTime, int activeDesktopCount, int totalSessionCount, int activeSessionCount, int totalDesktopCount, int totalUpdateCount, int activeUpdateCount) {
        super();
        this.startTime = startTime;
        this.activeDesktopCount = activeDesktopCount;
        this.totalSessionCount = totalSessionCount;
        this.activeSessionCount = activeSessionCount;
        this.totalDesktopCount = totalDesktopCount;
        this.totalUpdateCount = totalUpdateCount;
        this.activeUpdateCount = activeUpdateCount;
    }

    private final long currentTime = System.currentTimeMillis();
    private final long startTime;
    private final int activeDesktopCount;
    private final int totalSessionCount;
    private final int activeSessionCount;
    private final int totalDesktopCount;
    private final int totalUpdateCount;
    private final int activeUpdateCount;

    public int getActiveDesktopCount() {
        return this.activeDesktopCount;
    }

    public int getActiveSessionCount() {
        return this.activeSessionCount;
    }

    public int getActiveUpdateCount() {
        return this.activeUpdateCount;
    }

    public double getAverageDesktopCount() {
        return this.activeDesktopCount / getRuningHours();
    }

    public double getAverageSessionCount() {
        return this.totalSessionCount / getRuningHours();
    }

    public double getAverageUpdateCount() {
        return this.totalUpdateCount / getRuningHours();
    }

    public double getRuningHours() {
        return (this.currentTime - this.startTime) / 3600000d;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public int getTotalDesktopCount() {
        return this.totalDesktopCount;
    }

    public int getTotalSessionCount() {
        return this.totalSessionCount;
    }

    public int getTotalUpdateCount() {
        return this.totalUpdateCount;
    }
}