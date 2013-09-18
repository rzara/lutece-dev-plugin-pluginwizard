--
-- Data for table pluginwizard_plugin_model
--

INSERT INTO `pluginwizard_plugin_model` (`id_plugin`, `name`, `model_json`) VALUES (1,'myplugin','{\"locale\":null,\"features\":[{\"id\":1,\"jspName\":null,\"featureName\":\"ManageMyplugin\",\"featureDescription\":\"My Feature description\",\"featureRight\":\"MYPLUGIN_MANAGEMENT\",\"featureTitle\":\"My Feature\",\"featureLevel\":\"0\"}],\"pluginClass\":null,\"portlets\":[{\"id\":1,\"portletTypeName\":\"EXAMPLE_PORTLET\",\"portletClass\":\"fr.paris.lutece.plugins.myplugin.business.portlet.ExamplePortletHome\",\"portletCreationUrl\":\"plugins/myplugin/CreatePortletExample.jsp\",\"portletUpdateUrl\":\"plugins/myplugin/ModifyPortletExample.jsp\"}],\"pluginName\":\"myplugin\",\"idPlugin\":1,\"pluginDescription\":\"My Plugin description\",\"pluginDocumentation\":null,\"pluginInstallation\":null,\"pluginChanges\":null,\"pluginUserGuide\":null,\"pluginVersion\":\"1.0.0\",\"pluginCopyright\":\"Copyright (c) 2013 My Company\",\"pluginIconUrl\":\"images/admin/skin/plugins/myplugin/myplugin.png\",\"pluginProvider\":\"My Company\",\"pluginProviderUrl\":\"http://www.mycompany.com\",\"pluginDbPoolRequired\":\"1\",\"businessClasses\":[{\"id\":1,\"attributes\":[{\"id\":1,\"attributeName\":\"id_person\",\"maxLength\":0,\"notNull\":true,\"isPrimary\":true,\"attributeTypeId\":1,\"isDescription\":false},{\"id\":2,\"attributeName\":\"person_name\",\"maxLength\":50,\"notNull\":true,\"isPrimary\":false,\"attributeTypeId\":2,\"isDescription\":true}],\"businessClass\":\"Person\",\"idFeature\":1,\"businessTableName\":\"myplugin_person\",\"classDescription\":\"person_name\",\"primaryKey\":\"id_person\"},{\"id\":2,\"attributes\":[{\"id\":1,\"attributeName\":\"id_product\",\"maxLength\":0,\"notNull\":true,\"isPrimary\":true,\"attributeTypeId\":1,\"isDescription\":false},{\"id\":2,\"attributeName\":\"product_name\",\"maxLength\":255,\"notNull\":true,\"isPrimary\":false,\"attributeTypeId\":3,\"isDescription\":true}],\"businessClass\":\"Product\",\"idFeature\":1,\"businessTableName\":\"myplugin_product\",\"classDescription\":\"product_name\",\"primaryKey\":\"id_product\"}],\"applications\":[{\"id\":1,\"applicationClass\":\"MyPluginApp\",\"applicationName\":\"myplugin\"}]}');