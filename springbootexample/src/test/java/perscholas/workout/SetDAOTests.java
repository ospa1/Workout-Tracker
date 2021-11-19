package perscholas.workout;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import project.database.dao.ExerciseDAO;
import project.database.dao.SetDAO;
import project.database.dao.UserDAO;
import project.database.entity.Exercise;
import project.database.entity.Set;
import project.database.entity.User;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class SetDAOTests {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private ExerciseDAO exerciseDao;
	
	@Autowired
	private SetDAO setDao;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void setUpUserTest() {
		
		User user = new User();
		user.setEmail("TestEmail@Email.com");
		user.setPassword("TestPassword");
		
		userDao.save(user);
		
		Assertions.assertThat(userDao.findById(1).getId()).isEqualTo(1);
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)
	public void setUpExerciseTest() {
		
		Exercise exercise = new Exercise();
		exercise.setName("Test Exercise");
		exercise.setUser(userDao.findById(1));
		
		exerciseDao.save(exercise);
		
		Assertions.assertThat(exerciseDao.findById(1).getId()).isEqualTo(1);
	}
	
	@Test
	@Order(3)
	@Rollback(value = false)
	public void saveSetTest() {
		
		Set set = new Set();
		set.setReps(10);
		set.setWeight(100);
		set.setUserId(1);
		set.setExercise(exerciseDao.findById(1));
		
		setDao.save(set);
		
		Assertions.assertThat(setDao.findById(1));
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void userContainsSetTest() {
		
		User user = userDao.findById(1);
		Assertions.assertThat(user).isNotNull();
		
		Exercise exercise = user.getExercises().get(0);
		Assertions.assertThat(exercise).isNotNull();
		
		Set set = exercise.getSets().get(0);
		
		Assertions.assertThat(set).isNotNull();
		Assertions.assertThat(set.getId()).isEqualTo(1);
		Assertions.assertThat(set.getWeight()).isEqualTo(100);
		Assertions.assertThat(set.getReps()).isEqualTo(10);
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void updateSetTest() {
		
		Set set = setDao.findById(1);
		set.setReps(1);
		set.setWeight(1);
		Assertions.assertThat(setDao.findById(1).getReps()).isEqualTo(1);
		Assertions.assertThat(setDao.findById(1).getWeight()).isEqualTo(1);
	}
	
	@Test
	@Order(6)
	@Rollback(value = false)
	public void deleteSetTest() {
		
		Set set = setDao.findById(1);
		setDao.delete(set);
		
		Assertions.assertThat(setDao.findById(1)).isNull();
	}
	
	@Test
	@Order(7)
	@Rollback(value = false)
	public void findAfterDeleteSetTest() {
		
		Exercise exercise = exerciseDao.findById(1);
		List<Set> sets = exercise.getSets();
		
		Assertions.assertThat(sets.size()).isEqualTo(0);
	}
}
