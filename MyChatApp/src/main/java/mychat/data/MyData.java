package mychat.data;

import java.util.Arrays;
import java.util.List;

import mychat.model.UserModel;

public class MyData {
	public static List<UserModel> allUser(){
		UserModel user1 = new UserModel("tuannt", "1");
		UserModel user2 = new UserModel("thudong", "123");
		UserModel user3 = new UserModel("cunu", "1234");
		UserModel user4 = new UserModel("tiencoi", "1");
		List<UserModel> allUser = Arrays.asList(user1,user2,user3,user4);
		return allUser;
	}

}
