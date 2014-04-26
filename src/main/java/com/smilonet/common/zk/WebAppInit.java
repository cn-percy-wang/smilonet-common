package com.smilonet.common.zk;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.zkoss.zk.ui.WebApp;

/**
 * At time only used for debuging to find an error while Tomcat is crashed.
 * 
 * This clas must be declared in the zk.xml.
 * 
 * @author Stephan Gerth
 */

@Data
@Slf4j
public class WebAppInit implements org.zkoss.zk.ui.util.WebAppInit {

    @Override
    public void init(WebApp arg0) throws Exception {

        // Turn on the debug level for serialization errors
//        org.zkoss.util.logging.Log.lookup("org.zkoss.io.serializable").setLevel("DEBUG");

        System.out.println("#### Logger enabled for serialization errors!");

    }

}
