package com.danidemi.tddclass.arquillan;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * We want to verify that {@link Greeter} class behaves properly when invoked as a 
 * CDI (Context and Dependency Injection) bean. 
 */
// The Arquillian runner looks for a public static method annotated with the @Deployment annotation to retrieve the test archive (i.e., micro-deployment). 
// Then some magic happens and each @Test method is run inside the container environment.
@RunWith(Arquillian.class)
public class GreeterTest {
	
	/** A public static method annotated with @Deployment that returns a ShrinkWrap archive. 
	 * ShrinkWrap is a Java API for Archive Manipulation http://jboss.org/shrinkwrap. 
	 */
	// The main idea is to deploy in an embedddable container only what is really needed for the integration test.
	@Deployment
	public static JavaArchive createDeployment() {
		JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClass(Greeter.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		// this is just to print the content of the jar
		System.out.println( jar.toString(true) );
		return jar;
	}

	@Inject
	// Identifies injectable constructors, methods, and fields.
	Greeter greeter;

	@Test
	public void should_create_greeting() {
		Assert.assertEquals("Hello, Earthling!",
				greeter.createGreeting("Earthling"));
		greeter.greet(System.out, "Earthling");
	}

}
