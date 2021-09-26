package perscholas.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import perscholas.database.entity.User;

public interface UserDAO extends JpaRepository<User, Long> {

	User findByEmail(@Param("email") String email);

	User findById(@Param("id") Integer id);

	User findByUsername(@Param("username") String username);

	// @Query("select u from user u where u.firstname= :firstName")
	// public List<User> findByName(@Param("firsName") String firstName);

}
