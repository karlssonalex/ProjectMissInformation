package projectmissinformation.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u");
		userList = q.getResultList();
		em.close();

		return userList;
	}

	/***
	 * @author Charlotte Get specific user
	 * @param name
	 * @return
	 */
	public User getUser(String name) {

		List<User> userList = getUsers();
		for (User u : userList) {
			if (u.equals(name)) {
				return u;
			}
		}
		return new User();
	}

	/***
	 * @author Charlotte
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public void createUser(String username, String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		EntityManager em = factory.createEntityManager();
		User tempUser = new User();
		String salt = makeSalt();

		em.getTransaction().begin();
		tempUser.setName(username);
		tempUser.setPassword(encryptThis(password, salt));
		tempUser.setAdmin(0);
		tempUser.setSalt(salt);
		em.persist(tempUser);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @auth Axel
	 * @return
	 */
	private String makeSalt() {
		char[] chars = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * @author Axel
	 * @param question
	 *            The question asked by the user
	 * @param user
	 *            The name of the user asking the question
	 */
	public void addQuestion(String question_, String user_) {
		Question question = new Question();
		question.setQuestion(question_);
		question.setName(user_);
		question.setTicketid(createQuestionID()); // Alex

		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
		em.persist(question);
		em.getTransaction().commit();
		em.close();
	}
	

	/**
	 * @auth
	 * @param input
	 * @return
	 */
	public boolean validateInput(String input) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890@_.";
		if (input == null || input.equals("")) {
			return false;
		}

		boolean pass = true;
		char[] inputToCharArray = input.toCharArray();

		for (char character : inputToCharArray) {
			if (allowedChars.indexOf(character) == -1) {
				pass = false;
				break;
			}
		}
		return pass;
	}

	/**
	 * @author Alex
	 * @return The ticket id
	 */
	public Integer createQuestionID() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		return (Integer) em.createQuery("select max(q.ticketid) from Question q").getSingleResult() + 1;
	}

	/**
	 * @author Alex
	 * @return The boolean is true if user exists, otherwise false.
	 */
	public boolean userExists(String username) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u");
		List<User> userList = q.getResultList();

		for (User u : userList) {
			if (username.equals(u.getName())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @author Axel
	 * @param u
	 *            The user calling the method
	 * @return The list of questions returned depending on admin state
	 */
	@SuppressWarnings("unchecked")
	public List<Question> listQuestions(User u) {

		EntityManager em = factory.createEntityManager();
		List<Question> questionList;
		Query q = null;

		switch (u.getAdmin()) {
		case 0:
			q = em.createQuery("SELECT q FROM Question as q where q.name like :name");
			q.setParameter("name", u.getName());
			break;
		case 1:
			q = em.createNamedQuery("Question.findUnanswered", Question.class);
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

	/**
	 * @author Axel
	 * @param password
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	private static String encryptThis(String password, String salt) {
		StringBuffer sb = new StringBuffer();
		try {

			String saltedpassword = password + salt;
			byte[] bytesOfText;
			bytesOfText = saltedpassword.getBytes("UTF-8");

			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			byte[] theDigest = md.digest(bytesOfText);

			for (int i = 0; i < theDigest.length; i++) {
				sb.append(Integer.toHexString((theDigest[i] & 0xFF) | 0x100).substring(1, 3));
			}
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("Failed to encrypt ggwp");
		}
		return sb.toString();
	}

	/**
	 * Check login.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return true, if successful
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public boolean checkLogin(String username, String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u");
		List<User> userList = q.getResultList();

		for (User u : userList) {
			if (u.getName().equals(username)) {
				if (u.getPassword().equals(encryptThis(password, u.getSalt()))) {
					em.close();
					return true;
				}
			}
		}

		em.close();
		return false;
	}
}
