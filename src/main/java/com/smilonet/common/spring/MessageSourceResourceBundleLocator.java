///////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2012 smilonet.
// All rights reserved
///////////////////////////////////////////////////////////////////////////////////////////////////
package com.smilonet.common.spring;

import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.validator.resourceloading.ResourceBundleLocator;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceResourceBundle;

/**
 * 将ResourceBundleLocator代理为spring的MessageSource
 * 
 * @author wanglong(a)smilonet.com email:wanglong(a)smilonet.com(a)smilonet.com
 * @version 1.0
 * @since 1.0
 */
public class MessageSourceResourceBundleLocator implements ResourceBundleLocator {

    private MessageSource messageSource;

    public MessageSourceResourceBundleLocator() {
    }

    public MessageSourceResourceBundleLocator(MessageSource messageSource) {
        setMessageSource(messageSource);
    }

    public void setMessageSource(MessageSource messageSource) {
        if (messageSource == null)
            throw new IllegalArgumentException("'messageSource' must be not null");
        this.messageSource = messageSource;
    }

    public ResourceBundle getResourceBundle(Locale locale) {
        return new MessageSourceResourceBundle(messageSource, locale);
    }

}
