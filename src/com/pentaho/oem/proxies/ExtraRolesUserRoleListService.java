package com.pentaho.oem.proxies;

import org.pentaho.platform.api.engine.IUserRoleListService;
import org.pentaho.platform.api.mt.ITenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ExtraRolesUserRoleListService implements IUserRoleListService {

  private IUserRoleListService delegate;
  Logger logger = LoggerFactory.getLogger( ExtraRolesUserRoleListService.class );

  public ExtraRolesUserRoleListService( IUserRoleListService delegate ) {
    if( delegate == null ){
      throw new IllegalStateException( "ExtraRolesUserRoleListService requires a delegate implementation" );
    }
    this.delegate = delegate;
  }

  @Override
  public List<String> getAllRoles() {
    logger.info( "Adding extra roles " );
    List<String> fromDelegate = delegate.getAllRoles();

    // assume unmodifiable collection
    ArrayList<String> roles = new ArrayList<String>( fromDelegate );
    roles.add( "customRole" );
    return roles;
  }

  @Override
  public List<String> getSystemRoles() {
    return delegate.getSystemRoles();
  }

  @Override
  public List<String> getAllRoles( ITenant iTenant ) {
    logger.info( "Adding extra roles by tenant" );
    List<String> fromDelegate = delegate.getAllRoles( iTenant );

    // assume unmodifiable collection
    ArrayList<String> roles = new ArrayList<String>( fromDelegate );
    roles.add( iTenant.getId() + "-customRole" );
    return roles;
  }

  @Override
  public List<String> getAllUsers() {
    return delegate.getAllUsers();
  }

  @Override
  public List<String> getAllUsers( ITenant iTenant ) {
    return delegate.getAllUsers( iTenant );
  }

  @Override
  public List<String> getUsersInRole( ITenant iTenant, String s ) {
    return delegate.getUsersInRole( iTenant, s );
  }

  @Override
  public List<String> getRolesForUser( ITenant iTenant, String s ) {
    logger.info( "Adding extra roles by tenant and user" );

    List<String> fromDelegate = delegate.getRolesForUser( iTenant, s );

    // assume unmodifiable collection
    ArrayList<String> roles = new ArrayList<String>( fromDelegate );
    roles.add( iTenant.getId() + "-" + s + "-" + "customRole" );
    return roles;
  }
}
