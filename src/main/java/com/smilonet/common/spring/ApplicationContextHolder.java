///////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2012 smilonet.
// All rights reserved
///////////////////////////////////////////////////////////////////////////////////////////////////
package com.smilonet.common.spring;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 用于持有spring的applicationContext,一个系统只能有一个ApplicationContextHolder <br />
 * 
 * <pre>
 * 使用方法:
 * &lt;bean class="com.smilonet.common.spring.ApplicationContextHolder"/>
 * 
 * 在java代码中则可以如此使用: 
 * BlogDao dao = (BlogDao)ApplicationContextHolder.getBean("blogDao");
 * @author wanglong(a)smilonet.com email:wanglong(a)smilonet.com(a)smilonet.com
 * @version 1.0
 * @since 1.0
 */

@Data
@Slf4j
public class ApplicationContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @SuppressWarnings("all")
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        if (this.applicationContext != null) {
            throw new IllegalStateException("ApplicationContextHolder already holded 'applicationContext'.");
        }
        this.applicationContext = context;
        log.info("holded applicationContext,displayName:" + applicationContext.getDisplayName());
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null)
            throw new IllegalStateException("'applicationContext' property is null,ApplicationContextHolder not yet init.");
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    public static void cleanHolder() {
        applicationContext = null;
    }
}
