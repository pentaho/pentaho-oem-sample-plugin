# oem-sample-plugin

## Introduction
This project produces a Pentaho BA-Server platform plugin "oem-sample-plugin" which demonstrates overriding
core PentahoSystem Spring beans with ones from it's plugin.spring.xml.

1. TenantedDatasourceService demonstrates overriding a built-in type with a plugin's bean. It does this by simply
registering itself as an implementation with a higher than default priority (20).
2. ExtraRolesUserRoleListService is a more sophesticated example showing how you can wrap the built-in implementation with
a class to decorate the built-in's behaviors. This example adds a custom role to the physical list from the built-in
implementation.
3. MyAction is an example of an ABS Action being supplied by a plugin.
4. MyService is a plugin REST service utilizing the MyAction permission
