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

import org.apache.commons.lang3.time.FastDateFormat;
import org.zkoss.util.resource.Labels;

/**
 * DateFormatter for working with format strings from our i3labels.properties
 * files.<br>
 * 
 * @author bbruhns
 * @author sgerth
 * 
 */
final public class ZkDateFormat {

    static public FastDateFormat getDayNumberFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.dayNumber", "dd"));
    }

    static public FastDateFormat getDaynameFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.dayName", "EEE"));
    }

    static public FastDateFormat getDayMonthFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.DayMonth", "MM-dd"));
    }

    static public FastDateFormat getDateFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.date", "yyyy-MM-dd"));
    }

    static public FastDateFormat getMonth3DigitsFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.month3digits", "MMM"));
    }

    static public FastDateFormat getTimeFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.time", "HH:mm"));
    }

    static public FastDateFormat getTimeLongFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.timelong", "HH:mm:ss"));
    }

    static public FastDateFormat getDateTimeFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.datetime", "yyyy-MM-dd HH:mm"));
    }

    static public FastDateFormat getDateTimeLongFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.datetimelong", "yyyy-MM-dd HH:mm:ss"));
    }

    static public FastDateFormat getDaynameDateMonthFormater() {
        return FastDateFormat.getInstance(Labels.getLabel("format.dayNameDateMonth", "EE MM.dd"));
    }
}
