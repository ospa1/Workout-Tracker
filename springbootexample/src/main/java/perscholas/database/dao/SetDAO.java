package perscholas.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import perscholas.database.entity.Set;

public interface SetDAO extends JpaRepository<Set, Long> {

	Set findById(@Param("id") Integer id);

	// TODO test
	List<Set> findByExerciseId(@Param("fk_exercise_id") Integer exerciseId);

	// TODO test
	List<Set> findByUserId(@Param("fk_user_id") Integer userId);

	// maybe get by date
}
