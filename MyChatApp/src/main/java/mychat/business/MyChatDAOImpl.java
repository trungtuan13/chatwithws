package mychat.business;

import mychat.data.MyData;
import mychat.model.UserModel;

public class MyChatDAOImpl implements MyChatDAO {

	public UserModel getUserByUsername(String username) {
		for(UserModel user:MyData.allUser()) {
			if(user.getUsername().equals(username)) return user;
		}
		return null;
	}

}
