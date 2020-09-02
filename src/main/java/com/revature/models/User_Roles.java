package com.revature.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ers_user_roles")
public class User_Roles implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_role_id")
	private int user_role_id;
	
	@Column(name = "user_role")
	private String user_role;
	
	public User_Roles() {
		super();
	}

	public User_Roles(String user_role) {
		super();
		this.user_role = user_role;
	}

	public User_Roles(int user_role_id, String user_role) {
		super();
		this.user_role_id = user_role_id;
		this.user_role = user_role;
	}

	public int getuser_role_id() {
		return user_role_id;
	}

	public void setuser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + user_role_id;
		result = prime * result + ((user_role == null) ? 0 : user_role.hashCode());
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
		User_Roles other = (User_Roles) obj;
		if (user_role_id != other.user_role_id)
			return false;
		if (user_role == null) {
			if (other.user_role != null)
				return false;
		} else if (!user_role.equals(other.user_role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User_Roles [user_role_id=" + user_role_id + ", user_role=" + user_role + "]";
	}
	
	
	
	

}
