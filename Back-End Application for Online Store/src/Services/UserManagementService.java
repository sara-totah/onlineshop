package Services;

import enteties.User;

public interface UserManagementService {
	static String regirsterUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	  User[] getUsers() ;
		

	User getUserByEmail(String userEmail);

}
