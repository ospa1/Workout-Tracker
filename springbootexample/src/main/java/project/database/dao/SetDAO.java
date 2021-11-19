package project.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import project.database.entity.Set;

public interface SetDAO extends JpaRepository<Set, Long> {

	Set findById(@Param("id") Integer id);

	List<Set> findByExerciseId(@Param("fk_exercise_id") Integer exerciseId);

	List<Set> findByUserId(@Param("fk_user_id") Integer userId);

	List<Set> findByUserIdOrderByIdDesc(@Param("fk_user_id") Integer userId);
}
