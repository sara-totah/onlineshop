package Services.impl;

import java.util.Arrays;

import Services.UserManagementService;
import enteties.User;

public class DefaultUserManagementService implements UserManagementService {

	private static final String NOT_UNIQUE_EMAIL_ERROR_MESSAGE = "This email is already used by another user";
	private static final String EMPTY_EMAIL_UNSUCCESSFUL_VALIDATION = "You have to input email to register. Please, try one more time";
	private static final String NO_ERROR = "";

	private static final int DEFAULT_USERS_CAPACITY = 10;
	private  User[] TotalUsers;
	private User[] users;
	private int userIndex;

	{
		users = new User[DEFAULT_USERS_CAPACITY];
	}

	private DefaultUserManagementService() {

	}

	public String regirsterUser(User user) {
		if (user == null) {
			return NO_ERROR;
		}

		String errorMessage = checkEmail(user.getEmail());
		if (errorMessage != null && !errorMessage.isEmpty()) {
			return errorMessage;
		}
		if (users.length <= userIndex) {
			users = Arrays.copyOf(users, users.length << 1);
		}

		users[userIndex++] = user;
		return NO_ERROR;

	}

	public String checkEmail(String email) {
		if (email == null || ((CharSequence) email).isEmpty()) {
			return EMPTY_EMAIL_UNSUCCESSFUL_VALIDATION;
		}
		for (User user : users) {
			if (user != null && user.getEmail() != null && user.getEmail().equalsIgnoreCase(email)) {
				return NOT_UNIQUE_EMAIL_ERROR_MESSAGE;
			}
		}
		return NO_ERROR;
	}

	private static DefaultUserManagementService instance;
	
	public static UserManagementService getInstance() {
		if (instance == null) {
			instance = new DefaultUserManagementService();
		}
		return instance;
	}
 

	public User[] getUsers() {
		int AmountofUsers = 0;
		for (User user : users) {
			if (user != null) {
				AmountofUsers++;
			}
		}

		TotalUsers = new User[AmountofUsers];

		int i = 0;
		for (User user : users) {
			if (user != null) {
				TotalUsers[i++] = user;
			}
		}
		return TotalUsers;

	}

	@Override
	public User getUserByEmail(String userEmail) {
		for (User user : users) {
			if (user != null && user.getEmail().equalsIgnoreCase(userEmail)) {
				return user;
			}
		}
		return null;
	}

	void clearServiceState() {
		userIndex = 0;
		users = new User[DEFAULT_USERS_CAPACITY];

	}
	

	
}

