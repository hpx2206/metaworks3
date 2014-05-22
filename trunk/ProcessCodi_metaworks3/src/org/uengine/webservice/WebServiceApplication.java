package org.uengine.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.imageio.spi.RegisterableService;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/webs")
public class WebServiceApplication extends Application{

	@Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register root resource
//        classes.add(JaxRsProcessWebService.class);
        classes.add(JaxRsDbrepoWebService.class);
        classes.add(JaxRsSSOWebService.class);
        return classes;
    }
}
