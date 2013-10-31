package com.pentaho.oem.opsecurity;

import org.pentaho.platform.api.engine.IAuthorizationPolicy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

/**
 * Created with IntelliJ IDEA.
 * User: nbaker
 * Date: 10/25/13
 * Time: 10:15 AM
 * To change this template use File | Settings | File Templates.
 */
@Path("/oem-sample-plugin/api/opsecurity")
public class MyService {
  IAuthorizationPolicy authorizationPolicy;

  public MyService(IAuthorizationPolicy authorizationPolicy) {
    this.authorizationPolicy = authorizationPolicy;
  }

  @GET
  @Path("/canaccess")
  @Produces( { APPLICATION_JSON })
  public String canAccess(){

    return (this.authorizationPolicy.isAllowed(MyAction.NAME)) ? "User Authorized to continue" : "Not Authorized";

  }
}



