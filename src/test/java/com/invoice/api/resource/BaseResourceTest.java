package com.invoice.api.resource;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;

public abstract class BaseResourceTest extends JerseyTest{
	@Override
	protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(InvoiceBeanResource.class,ItemBeanResource.class).property("contextConfigLocation",
				"classpath:application-config-test.xml");
	}

}
