package com.example.config.base;

import lombok.Data;

@Data
public class DbProperties {
	private String url;
	private String user;
	private String password;
}