package com.revature.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "ers_users")
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int users_id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String first_name;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "user_email")
	private String user_email;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_role_id")
	private int user_role_id;
	
	
	public Users() {
		super();
	}


	public Users(String username, String password, String first_name, String last_name,
			String user_email, int user_role_id) {
		super();
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_email = user_email;
		this.user_role_id = user_role_id;
	}


	public Users(int users_id, String username, String password, String first_name,
			String last_name, String user_email) {
		super();
		this.users_id = users_id;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_email = user_email;
	}


	public Users(int users_id, String username, String password, String first_name,
			String last_name, String user_email, int user_role_id) {
		super();
		this.users_id = users_id;
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_email = user_email;
		this.user_role_id = user_role_id;
	}


	public Users(String username, String password, String first_name, String last_name,
			String user_email) {
		super();
		this.username = username;
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_email = user_email;
	}


	public int getUsers_id() {
		return users_id;
	}


	public void setUsers_id(int Users_id) {
		this.users_id = Users_id;
	}


	public String getusername() {
		return username;
	}


	public void setusername(String username) {
		this.username = username;
	}


	public String getpassword() {
		return password;
	}


	public void setpassword(String password) {
		this.password = password;
	}


	public String getfirst_name() {
		return first_name;
	}


	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getlast_name() {
		return last_name;
	}


	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}


	public String getUser_email() {
		return user_email;
	}


	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}


	public int getUser_role_id() {
		return user_role_id;
	}


	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + users_id;
		result = prime * result + ((user_email == null) ? 0 : user_email.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + user_role_id;
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
		Users other = (Users) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (users_id != other.users_id)
			return false;
		if (user_email == null) {
			if (other.user_email != null)
				return false;
		} else if (!user_email.equals(other.user_email))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (user_role_id != other.user_role_id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Users [users_id=" + users_id + ", username=" + username + ", password="
				+ password + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", user_email=" + user_email + ", user_role_id=" + user_role_id + "]";
	}
	
	
	
	
}
