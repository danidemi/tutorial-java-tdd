package com.danidemi.tddclass.arquillan;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class MasterSlaveTest {

	// Let's embed in the jar just the master and the slave
	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap
				.create(JavaArchive.class)
				.addClass(Master.class)
				.addClass(Slave.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println( jar.toString(true) );
		return jar;
	}
	
	@Inject
	Master master;
	
	@Test
	public void shouldConcatenateMasterWithSlaveDescription() {
		assertThat( master.toString(), equalTo("master->slave") );
	}
	
}
