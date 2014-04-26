package com.smilonet.common.vo;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class SelectListItemVO implements Serializable {

    private static final long serialVersionUID = 3785610683068904722L;

    private String id;

    private String name;
    
    public SelectListItemVO(Object target) {
        try {
            this.id = BeanUtils.getProperty(target, "id");
            this.name = BeanUtils.getProperty(target, "name");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
