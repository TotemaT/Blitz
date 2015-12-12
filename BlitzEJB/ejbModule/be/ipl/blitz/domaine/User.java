package be.ipl.blitz.domaine;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import be.ipl.blitz.utils.PasswordTools;
import be.ipl.blitz.utils.Util;

@SuppressWarnings("serial")
@Entity
@Table(name = "USERS", schema = "BLITZ", indexes = { @Index(name = "user_name", columnList = "name", unique = true) })
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@NotNull
	private String name;

	// TODO: verif si pwd et salt est bien necessaire
	@Column
	@NotNull
	private byte[] pwd;

	@Column
	@NotNull
	private byte[] salt;
	
	@ManyToMany(mappedBy="players")
	public Set<Game> gamesPlayed;

	public User() {
	}

	public User(String name, String pwd) throws Exception {
		super();
		Util.checkString(name);
		Util.checkString(pwd);
		this.name = name;
		this.pwd = pwd.getBytes();
		salt = PasswordTools.generateSalt();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		Util.checkPositiveOrZero(id);
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Util.checkString(name);
		this.name = name;
	}

	public byte[] getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		Util.checkString(pwd);
		this.pwd = pwd.getBytes();
	}

	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.id + " " + this.name;
	}

}