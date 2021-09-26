package perscholas.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import perscholas.database.entity.Exercise;

public interface ExerciseDAO extends JpaRepository<Exercise, Long> {

	Exercise findById(@Param("id") Integer id);

	Exercise findByName(@Param("name") String name);

	List<Exercise> findByUserId(@Param("user_id") Integer user_id);
}
