package projectmissinformation.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the QUESTIONS database table.
 * 
 */
@Entity
@Table(name="QUESTIONS")
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private double ticketid;

	private String answer;

	private String name;

	private String question;

	public Question() {
	}

	public double getTicketid() {
		return this.ticketid;
	}

	public void setTicketid(double ticketid) {
		this.ticketid = ticketid;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}