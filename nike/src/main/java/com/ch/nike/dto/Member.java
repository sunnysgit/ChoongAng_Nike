package com.ch.nike.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("Member")
public class Member {
	private String email;
	private String name;
	private String password;
	private String memberGender;
	private Date date;
	private int memberTel;
	private Date regDate;
	private String memberDel;
}
