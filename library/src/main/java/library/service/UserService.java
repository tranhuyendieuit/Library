package library.service;

import library.model.User;
import library.repository.AttackedSQLInjectionUserRepository;
import library.repository.UserRepository;

public class UserService {

	private UserRepository userRepository;
	
	private AttackedSQLInjectionUserRepository attackedSQLInjectionUserRepository;

	public UserService(UserRepository userRepository, 
			AttackedSQLInjectionUserRepository attackedSQLInjectionUserRepository) {
		this.userRepository = userRepository;
		this.attackedSQLInjectionUserRepository = attackedSQLInjectionUserRepository;
	}

	public User findUser(String username, String password) {
		// Validate input
//		if (username.contains("'") && username.contains("-"))
//			return null;
		
 		return attackedSQLInjectionUserRepository.findUser(username, password);
//		return userRepository.findUser(username, password); 
	}

}
