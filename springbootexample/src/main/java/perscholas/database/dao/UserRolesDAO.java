package perscholas.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import perscholas.database.entity.UserRole;

public interface UserRolesDAO extends JpaRepository<UserRole, Long> {

	UserRole findById(@Param("id") Integer id);

	UserRole findByUserId(@Param("fk_user_id") Integer user_id);
}
