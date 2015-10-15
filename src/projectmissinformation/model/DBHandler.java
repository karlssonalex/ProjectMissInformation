package projectmissinformation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBHandler {
	
private static final String PERSISTENCE_UNIT_NAME = "ProjectMissInformation";
	
	private EntityManagerFactory factory;
	
	private List<User> userList;
	
	/***
	 * @author Charlotte
	 */
	public DBHandler() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		userList = new ArrayList<User>();
	}
	
	/***
	 * @author Charlotte
	 * @return
	 */
	public List<User> getUsers() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u");
		userList = q.getResultList();
		em.close();
		
		return userList;
	}
	
	/***
	 * @author Charlotte
	 * @param user
	 */
	public void addUserToDB(User user) {
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		
	}
	
	/***
	 * @author Charlotte
	 */
	public void createUser(String username, String password) {
		
		User user = new User(username, password);
		addUserToDB(user);
		System.out.println(username + password);
		
		
	}

}
