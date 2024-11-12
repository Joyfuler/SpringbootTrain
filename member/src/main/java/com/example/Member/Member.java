package com.example.Member;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private Long id;
	private String email;
	private String password;
	private String username;
	private Date regDate;

	public Member(String email, String password, String username) {
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public Member(Long id, String password, String username) {
		this.id = id;
		this.password = password;
		this.username = username;
	}
}
