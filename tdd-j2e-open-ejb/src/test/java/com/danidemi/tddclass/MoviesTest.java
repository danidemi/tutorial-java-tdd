package com.danidemi.tddclass;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import junit.framework.TestCase;

import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatefulBean;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.Module;
import org.junit.Test;
import org.junit.runner.RunWith;

//The org.apache.openejb.junit.ApplicationComposer is a JUnit test runner. 
//It involves no classpath scanning at all. 
//If you want something to be in the app, you must build it directly in your testcase.
@RunWith(ApplicationComposer.class)
public class MoviesTest extends TestCase {

    @EJB
    private Movies movies;

    @Resource
    private UserTransaction userTransaction;

    @PersistenceContext
    private EntityManager entityManager;

    //An open EJB annotation that mark a method that define the structure of the app to be deployed.
    //Multiple methods can be marked with @Module. This for instance configure persistence.
    @Module
    public PersistenceUnit persistence() {
        PersistenceUnit unit = new PersistenceUnit("movie-unit");
        unit.setJtaDataSource("movieDatabase");
        unit.setNonJtaDataSource("movieDatabaseUnmanaged");
        unit.getClazz().add(Movie.class.getName());
        unit.setProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
        return unit;
    }

    // This one configures the EJBs 
    @Module
    public EjbJar beans() {
        EjbJar ejbJar = new EjbJar("movie-beans");
        ejbJar.addEnterpriseBean(new StatefulBean(Movies.class));
        return ejbJar;
    }

    // This one returns the configuration as a Properties.
    @Configuration
    public Properties config() throws Exception {
        Properties p = new Properties();
        p.put("movieDatabase", "new://Resource?type=DataSource");
        p.put("movieDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("movieDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");
        return p;
    }

    @Test
    public void test00_shouldSaveAndDeleteMovies() throws Exception {

        userTransaction.begin();

        try {
            entityManager.persist(new Movie("Quentin Tarantino", "Reservoir Dogs", 1992));
            entityManager.persist(new Movie("Joel Coen", "Fargo", 1996));
            entityManager.persist(new Movie("Joel Coen", "The Big Lebowski", 1998));

            List<Movie> list = movies.getMovies();
            assertEquals("List.size()", 3, list.size());

            for (Movie movie : list) {
                movies.deleteMovie(movie);
            }

            assertEquals("Movies.getMovies()", 0, movies.getMovies().size());

        } finally {
            userTransaction.rollback();
        }
    }
    
    @Test
    public void test10_shouldSaveMovies() throws Exception {

        userTransaction.begin();

        try {
        	
            entityManager.persist(new Movie("Quentin Tarantino", "Reservoir Dogs", 1992));
            entityManager.persist(new Movie("Joel Coen", "Fargo", 1996));
            entityManager.persist(new Movie("Joel Coen", "The Big Lebowski", 1998));

            List<Movie> list = movies.getMovies();
            assertEquals("List.size()", 3, list.size());
            
        } finally {
            userTransaction.rollback();
        }
    }
        
}