package za.co.ashtech.jpa_demo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import za.co.ashtech.jpa_demo.simplilearn.db.entities.Player;
import za.co.ashtech.jpa_demo.simplilearn.db.entities.Team;

public class HibernateDemo {

	public static void main(String[] args) {
		System.out.println("*****	HIBERNATE  *****");
		
		Configuration configuration = new Configuration().configure();
		System.out.println("*****	Create configuration  *****");
		configuration.addAnnotatedClass(za.co.ashtech.jpa_demo.simplilearn.db.entities.Player.class);
		configuration.addAnnotatedClass(za.co.ashtech.jpa_demo.simplilearn.db.entities.Team.class);
		System.out.println("*****	Add annotated classes to config  *****");
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
				
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		System.out.println("*****	Create session factory  *****");
		
		Session session = sessionFactory.openSession();
		System.out.println("*****	Open session  *****");
		
		Transaction transaction = session.beginTransaction();
		System.out.println("*****	Begin transaction  *****");
		
		//List of players
		List<Team> teams = Arrays.asList(
												new Team("Entertainers FC Belhar"),
												new Team("Belhar FC"),
												new Team("Delft FC"),
												new Team("Bishop Lavis FC"),
												new Team("Elsies United")			
											);
		teams.forEach(team -> {			
			session.save(team);
			System.out.println("*****	Team "+team.getTeamName()+" persisted  *****");
		});

		
		//List of players
		List<Player> players = Arrays.asList(
												new Player("Rick James", session.get(Team.class, 1), 38),
												new Player("Donnie Steve",session.get(Team.class, 2) , 39),
												new Player("Ashy Scholy",session.get(Team.class, 2) , 19),
												new Player("Pelo Vamelo",session.get(Team.class, 4) , 17),
												new Player("Marvie Jay",session.get(Team.class, 3), 15)			
											);
		
		players.forEach(player -> {			
			session.save(player);
			System.out.println("*****	Player "+player.getFullname()+" persisted  *****");
		});

		
		transaction.commit();
		System.out.println("*****	Commit transaction  *****");
		
		System.out.println("*****	SELECT LIST  *****");
		 List<?> teamQueryList = session.createQuery("from Team").list();
		 teamQueryList.forEach(team -> {
			 						System.out.println(team.toString());
								 });
		 System.out.println("*****	----------------------  *****");
		
		System.out.println("*****	SELECT LIST  *****");
		 List<?> queryList = session.createQuery("from Player").list();
		 queryList.forEach(player -> {
			 						System.out.println(player.toString());
								 });
		 System.out.println("*****	----------------------  *****");
		

		 Query<Player> playerQuery = session.createQuery("from Player where  fullname = :fn", Player.class);
		 playerQuery.setParameter("fn", "Donnie Steve");
		 
		 System.out.println("*****	Player "+playerQuery.getSingleResult().toString()+" persisted  *****");
		 

		System.out.println("*****	CRITERIA  *****");
        // Create a Criteria object for the Employee entity
        Criteria criteria = session.createCriteria(Player.class);
        criteria.add(Restrictions.gt("age", 20));
        
        // Execute the query and get the list of results
        List<Player> playersCriteriaAge = criteria.list();
	    
        playersCriteriaAge.forEach(p ->  System.out.println(p.toString()));
	        
		 System.out.println("*****	----------------------  *****");
		
		session.close();
		System.out.println("*****	Close session  *****");

	}

}
