package project.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import project.database.entity.UserRole;

public interface UserRolesDAO extends JpaRepository<UserRole, Long> {

	UserRole findById(@Param("id") Integer id);

	List<UserRole> findByUserId(@Param("fk_user_id") Integer user_id);

	// @Query("select u from userrole u where fk_user_id = :user_id")
	// List<UserRole> findByUserId(Integer user_id);
}
