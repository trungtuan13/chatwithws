package mychat.business;

import mychat.model.UserModel;

public interface MyChatDAO {
	public UserModel getUserByUsername(String username);
}
