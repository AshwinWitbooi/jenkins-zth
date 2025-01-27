package za.co.ashtech.jenkins.zth;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import za.co.ashtech.jenkins.zth.db.entities.Author;
import za.co.ashtech.jenkins.zth.db.entities.Book;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "*****	JENKINS SKILLS REFRESHER	*****" );
        
        Transaction transaction = null;
        
        try {
        	
    		Configuration configuration = new Configuration().configure();
    		System.out.println("*****	Create configuration  *****");
    		configuration.addAnnotatedClass(za.co.ashtech.jenkins.zth.db.entities.Author.class);
    		configuration.addAnnotatedClass(za.co.ashtech.jenkins.zth.db.entities.Book.class);
    		System.out.println("*****	Add annotated classes to config  *****");
    		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
    				
    		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
    		System.out.println("*****	Create session factory  *****");
    		
    		Session session = sessionFactory.openSession();
    		System.out.println("*****	Open session  *****");
    		
    		transaction = session.beginTransaction();
    		System.out.println("*****	Begin transaction  *****");
    		    		
            getBooks().forEach(book -> {
                // Save the person object to the database
                session.save(book);
                System.out.println("Book saved: " + book.toString());
            });
            
            transaction.commit();
            
            int recs = session.createQuery("FROM Book").getResultList().size();
           if(recs > 0) {
        	   System.out.println("Book records found:"+recs);
           }else {
        	   System.out.println("NO Book records found");
           }            
            
    		session.close();
    		System.out.println("*****	Close session  *****");
    		sessionFactory.close();
    		System.out.println("*****	Close session factory  *****");

        	
        }catch (Exception e) {
        	transaction.rollback();
			e.printStackTrace();
		}

    }
    
    
	/* Method setup books to be used in the main App */
    private static List<Book> getBooks(){
    	
    	Set<Author> authors1 = new HashSet<Author>();
    		authors1.add(new Author("Joshua Bloch"));
    	Set<Author> authors2 = new HashSet<Author>();
    		authors2.add(new Author("Herbert Schildt"));
    	Set<Author> authors3 = new HashSet<Author>();
    		authors3.add(new Author("Kathy Sierra"));
    		authors3.add(new Author("Bert Bates")); 
    	Set<Author> authors4 = new HashSet<Author>();
    		authors4.add(new Author("Robert C. Martin"));
    	Set<Author> authors5 = new HashSet<Author>();
    		authors5.add(new Author("Brian Goetz"));
    	Set<Author> authors6 = new HashSet<Author>();
    		authors6.add(new Author("Gene Kim"));
    		authors6.add(new Author("Patrick Debois"));
    		authors6.add(new Author("John Willis"));
    		authors6.add(new Author("Jez Humble"));
    	Set<Author> authors7 = new HashSet<Author>();
    		authors7.add(new Author("Niall Richard Murphy"));
    		authors7.add(new Author("Betsy Beyer"));
    		authors7.add(new Author("Chris Jones"));
    		authors7.add(new Author("Jennifer Petoff"));
    	
    	System.out.println("Books set up......");
		return Arrays.asList(
    							new Book("Effective Java (3rd Edition)", authors1 ),
    							new Book("Java: The Complete Reference (11th Edition)", authors2 ),
    							new Book("Head First Java (2nd Edition)", authors3 ),
    							new Book("Clean Code: A Handbook of Agile Software Craftsmanship", authors4 ),
    							new Book("Java Concurrency in Practice", authors5 ),
    							new Book("The DevOps Handbook: How to Create World-Class Agility, Reliability, & Security in Technology Organizations", authors6 ),
    							new Book("Site Reliability Engineering: How Google Runs Production Systems", authors7 )
    						);

    }

}
