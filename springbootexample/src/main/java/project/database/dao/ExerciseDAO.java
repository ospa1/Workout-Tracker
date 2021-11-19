package project.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.database.entity.Exercise;

public interface ExerciseDAO extends JpaRepository<Exercise, Long> {

	Exercise findById(@Param("id") Integer id);

	Exercise findByName(@Param("name") String name);

	List<Exercise> findByUserId(@Param("user_id") Integer user_id);

	List<Exercise> findByUserIdOrderByIdDesc(@Param("user_id") Integer user_id);

	@Query("select ex from Exercise as ex where ex.id = :id")
	Exercise getExerciseById(@Param("id") Integer id);
}
