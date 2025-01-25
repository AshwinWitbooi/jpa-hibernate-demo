package za.co.ashtech.jpa_demo;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import za.co.ashtech.jpa_demo.simplilearn.db.entities.Person;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World! JPA" );
        setUpH2DBWithJPA();
        
        System.out.println( "Hello World! Hibernate Refresher" );
//        setUpH2DBWithHibernate(); 
    }
    
    
    
    //Method to setup H2 database with JPA
    @SuppressWarnings("resource")
	public static void setUpH2DBWithJPA () {
    	
        // Create EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("H2PU");

        // Create EntityManager
        EntityManager em = emf.createEntityManager();

        // Create a new User instance
        Person person = new Person("John Doe");

        // Begin transaction
        em.getTransaction().begin();

        // Persist the entity
        em.persist(person);

        // Commit transaction
        em.getTransaction().commit();

        // Fetch the entity
        Person fetchedUser = em.find(Person.class, 1L);
        System.out.println("Fetched Person: " + fetchedUser.getName());

        // Close EntityManager
        em.close();
        emf.close();
    	
    }
    
    
    //Method to setup H2 database with Hibernate
    @SuppressWarnings("resource")
	public static void setUpH2DBWithHibernate () {

    	//List of users to add to database
        List<String> fullNames = Arrays.asList(
                "Jane Doe", 
                "Jane Smith", 
                "Michael Johnson", 
                "Emily Brown", 
                "James White", 
                "Linda Black", 
                "David Green", 
                "Sarah Taylor", 
                "William Harris", 
                "Elizabeth Clark", 
                "Daniel Lewis", 
                "Mary Walker"
            );
        
        
        // Create a session factory from the configuration
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml") // Use the configuration file
                .addAnnotatedClass(Person.class) // Register the entity class
                .buildSessionFactory();
        System.out.println( "Set Hibernate config and create session factory" );

        // Get the current session
        Session session = factory.getCurrentSession();
        System.out.println( "Obtain session from session factory");
        
        // Start a transaction
        session.beginTransaction();
        
            try {
            	
                for(String name: fullNames) {                	
            	
	                // Create a new Person object
	                Person newPerson = new Person(name);
	
	                // Save the person object to the database
	                session.save(newPerson);
	                System.out.println("Person saved: " + newPerson.toString());
                
                }

                // Commit the transaction
                session.getTransaction().commit();

                
            } finally {  
            	// Close the session factory
                factory.close();       
            }
        

        System.out.println( "H2 Database setup and populated with test data..." );
    	
	}
}
