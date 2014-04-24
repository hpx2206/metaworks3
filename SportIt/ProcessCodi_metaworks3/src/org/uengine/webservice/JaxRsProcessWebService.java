package org.uengine.webservice;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@Path("helloworld")
public class JaxRsProcessWebService extends ProcessWebService{

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String helloPlain() throws Exception {
		String definitionName = "aa5.process";
		this._start(definitionName);
        return "hello world";
    }
	
	@POST
	@Path("user")
    @Produces("text/plain")
    public String _sendMessage(@FormParam("instanceId") String instanceId, @FormParam("definitionName") String definitionName, @FormParam("messageName")  String messageName) {
		if( instanceId == null || "".equals(instanceId)){
			
		}else{
			
		}
		
        return "Hello World!";
    }
}
