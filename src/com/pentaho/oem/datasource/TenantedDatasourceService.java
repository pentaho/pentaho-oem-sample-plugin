/**
 * COPYRIGHT (C) 2013 Pentaho. All Rights Reserved.
 * THE SOFTWARE PROVIDED IN THIS SAMPLE IS PROVIDED "AS IS" AND PENTAHO AND ITS 
 * LICENSOR MAKE NO WARRANTIES, WHETHER EXPRESS, IMPLIED, OR STATUTORY REGARDING 
 * OR RELATING TO THE SOFTWARE, ITS DOCUMENTATION OR ANY MATERIALS PROVIDED BY 
 * PENTAHO TO LICENSEE.  PENTAHO AND ITS LICENSORS DO NOT WARRANT THAT THE 
 * SOFTWARE WILL OPERATE UNINTERRUPTED OR THAT THEY WILL BE FREE FROM DEFECTS OR 
 * THAT THE SOFTWARE IS DESIGNED TO MEET LICENSEE'S BUSINESS REQUIREMENTS.  PENTAHO 
 * AND ITS LICENSORS HEREBY DISCLAIM ALL OTHER WARRANTIES, INCLUDING, WITHOUT 
 * LIMITATION, ANY IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR 
 * PURPOSE, TITLE AND NONINFRINGMENT.  IN ADDITION, THERE IS NO MAINTENANCE OR SUPPORT 
 * INCLUDED WITH THIS SAMPLE OF ANY NATURE WHATSOEVER, INCLUDING, BUT NOT LIMITED TO, 
 * HELP-DESK SERVICES. 
 * @author mmohammadi
 * @version 1.01
 */
package com.pentaho.oem.datasource;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.pentaho.platform.api.data.DBDatasourceServiceException;
import org.pentaho.platform.api.engine.IPentahoSession;
import org.pentaho.platform.engine.core.system.PentahoSessionHolder;
import org.pentaho.platform.engine.services.connection.datasource.dbcp.PooledOrJndiDatasourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings( "deprecation" )
public class TenantedDatasourceService extends PooledOrJndiDatasourceService {

  Logger logger = LoggerFactory.getLogger( TenantedDatasourceService.class );

  @Override
  public DataSource getDataSource( String dsName ) throws DBDatasourceServiceException {
    return super.getDataSource( modifyDsNameForTenancy( dsName ) );
  }

  @Override
  public void clearDataSource( String dsName ) {
    super.clearDataSource( modifyDsNameForTenancy( dsName ) );
  }

  private String modifyDsNameForTenancy( String dsName ) {

    logger.debug( "Original DSName is " + dsName );
    IPentahoSession session = PentahoSessionHolder.getSession();
    if ( session == null ) {
      logger.warn( "Session is null; no modifications made to the JNDI dsName." );
      return dsName;
    }

    String tenantId = (String) session.getAttribute( "tenantId" );

    if ( StringUtils.isEmpty( tenantId ) ) {
      logger.warn( "ID not found in session; no modifications made to the JNDI dsName." );
      return dsName;
    }
    String dsname = tenantId.concat( "_" ).concat( dsName );
    logger.debug( "New DSName is " + dsname );
    return dsname;
  }

}
