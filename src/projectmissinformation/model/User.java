package projectmissinformation.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name="USERS")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String name;

	private Object admin;

	private String password;

	public User() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getAdmin() {
		return this.admin;
	}

	public void setAdmin(Object admin) {
		this.admin = admin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}