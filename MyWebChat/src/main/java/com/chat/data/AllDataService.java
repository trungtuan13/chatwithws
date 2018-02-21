package com.chat.data;

import java.util.Arrays;
import java.util.List;

import com.chat.controller.User;

public class AllDataService {
	public static List<User> allUsersData(){
		User user1 = new User(1, "Trung Tuan", "tuannt", "123");
		User user2 = new User(2, "Thu Dong", "thudong", "123456");
		List<User> users = Arrays.asList(user1,user2);
		return users;
	}

}
