package com.pentaho.oem.opsecurity;

import org.pentaho.platform.api.engine.IAuthorizationAction;

/**
 * New ABS Action (Operational Security Action) "MyAction"
 */
public class MyAction implements IAuthorizationAction {
  public static final String NAME = "MyAction";
  @Override
  public String getName() {
    return NAME;
  }
}