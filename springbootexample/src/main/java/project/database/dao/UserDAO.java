package project.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import project.database.entity.User;

public interface UserDAO extends JpaRepository<User, Long> {

	User findByEmail(@Param("email") String email);

	User findById(@Param("id") Integer id);

	// need to map user roles to user entity
	// @Query("select ur from UserRole ur where ur.fk_user_id = :userId")
	// List<UserRole> getUserRoles(Integer userId);
}
