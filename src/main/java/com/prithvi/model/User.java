package com.prithvi.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "User")
public class User {
	@Id
	@Column(name = "userId")
	private int userId;

	@Column(name = "UserName")
	private String userName;

	@NotBlank
	@Column(name = "UserGender")
	private String userGender;

	@NotBlank
	@Column(name = "UserCity")
	private String userCity;

	@NotBlank
	@Column(name = "UserAge")
	private String userAge;

	@NotNull(message = "Registration date is required")
	@Column(name = "UserRegisterDate")
	private Date userRegisterDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public Date getUserRegisterDate() {
		return userRegisterDate;
	}

	public void setUserRegisterDate(Date userRegisterDate) {
		this.userRegisterDate = userRegisterDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userGender=" + userGender + ", userCity="
				+ userCity + ", userAge=" + userAge + ", userRegisterDate=" + userRegisterDate + "]";
	}

}
