package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.User;
import org.springframework.data.repository.CrudRepository;

// 19.3.3
public interface UserRepository extends CrudRepository<User, Integer> {

	/**
	 * Find a {@link User} provided a username
	 * 
	 * @param username the username
	 * 
	 * @return the {@link User} if found
	 */
	User findByUsername(String username);

}