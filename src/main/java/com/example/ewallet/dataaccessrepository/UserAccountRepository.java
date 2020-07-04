package com.example.ewallet.dataaccessrepository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.ewallet.models.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

	/**gets user by name
	 * @param name
	 * @return user account
	 */
	Optional<UserAccount> getByUserName(String name);
}
