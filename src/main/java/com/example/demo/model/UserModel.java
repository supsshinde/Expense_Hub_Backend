package com.example.demo.model;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

	private int uid;
	private String uname;
	private String email;
	private String password;
	private Date created_date;
	private Long mobile;
	private String city;
	private int pincode;
}
