package perscholas.database.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import perscholas.database.entity.Tutorial;

public interface TutorialDAO extends JpaRepository<Tutorial, Long> {

	Tutorial findById(@Param("id") Integer id);

	// TODO decide on url uniqueness and name uniqueness
	List<Tutorial> findByNameIgnoreCase(@Param("name") String name);

	List<Tutorial> findByName(@Param("name") String name);

	List<Tutorial> findByUrl(@Param("url") String url);
}
