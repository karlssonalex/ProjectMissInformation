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
	 * Get specific user
	 * @param name
	 * @return
	 */
	public User getUser(String name) {
		
		List<User> userList = getUsers();
		for(User u : userList) {
			if(u.equals(name)) {
				return u;
			}
		}
		return new User();
	}
	
	/***
	 * @author Charlotte
	 */
	public void createUser(String username, String password) {
		EntityManager em = factory.createEntityManager();
		User tempUser = new User();
		em.getTransaction().begin();
		
		tempUser.setName(username);
		tempUser.setPassword(password);
		tempUser.setAdmin(0);
		
		em.persist(tempUser);
		em.getTransaction().commit();	
		em.close();
		
		System.out.println(username + password);
	}
	
	/**
	 * @author Axel
	 * @param question The question asked by the user
	 * @param user The name of the user asking the question
	 */
	public void addQuestion(String question_, String user_) {
		Question question = new Question();
		question.setQuestion(question_);
		question.setName(user_);
		question.setTicketid(createQuestionID()); //Alex
		
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(question);
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * @author Alex
	 * @return The ticket id
	 */
	public Integer createQuestionID(){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		return (Integer) em.createQuery("select max(q.ticketid) from Question q").getSingleResult()+1;
	}
	
	/**
	 * @author Alex
	 * @return The boolean is true if user exists, otherwise false.
	 */
	public boolean userExists(String username){
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u");
		List<User> userList = q.getResultList();
		
		for(User u : userList){
			if(username.equals(u.getName())){
				return true;
			}
		}
		return false;
	}

	
	/**
	 * @author Axel
	 * @param u The user calling the method
	 * @return The list of questions returned depending on admin state
	 */
	@SuppressWarnings("unchecked")
	public List<Question> listQuestions(User u) {
		
		EntityManager em = factory.createEntityManager();
		List<Question> questionList = null;
		Query q = null;
		
		switch (u.getAdmin()) {
		case 0:
			q = em.createQuery("SELECT q FROM Question as q where q.name = " +u.getName());
			break;
		case 1:
			q = em.createQuery("SELECT q FROM Question as q where q.answer is null");
			break;
		}
		questionList = q.getResultList();
		return questionList;
	}
	
	/**
	 * @author Axel
	 * @param ticketId
	 * @param answer
	 */
	public void answerQuestion(int ticketId, String answer) {
		
		EntityManager em = factory.createEntityManager();
		Question updatedQ = em.find(Question.class, ticketId);
		
		em.getTransaction().begin();
		updatedQ.setAnswer(answer);
		em.getTransaction().commit();
	}

}
