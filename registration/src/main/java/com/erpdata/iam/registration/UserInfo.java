package com.erpdata.iam.registration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "USER_INFO")
public class UserInfo {

	@Id
	@GeneratedValue
	private Long userId;
	private String userName;
	private String password;

	public Long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
