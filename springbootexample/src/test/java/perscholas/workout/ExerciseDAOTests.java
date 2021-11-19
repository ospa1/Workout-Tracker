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
import project.database.dao.UserDAO;
import project.database.entity.Exercise;
import project.database.entity.User;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class ExerciseDAOTests {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private ExerciseDAO exerciseDao;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveUserTest() {
		
		User user = new User();
		user.setEmail("TestEmail@Email.com");
		user.setPassword("TestPassword");
		
		userDao.save(user);
		
		Assertions.assertThat(userDao.findById(1).getId()).isEqualTo(1);
	}
	
	@Test
	@Order(2)
	@Rollback(value = false)
	public void saveExerciseTest() {
		
		Exercise exercise = new Exercise();
		exercise.setName("Test Exercise");
		exercise.setUser(userDao.findById(1));
		
		exerciseDao.save(exercise);
		
		Assertions.assertThat(exerciseDao.findById(1).getId()).isEqualTo(1);
	}
	
	@Test
	@Order(3)
	@Rollback(value = false)
	public void getExerciseFromUserTest() {
		
		User user = userDao.findById(1);
		List<Exercise> exercises = user.getExercises();
		
		Assertions.assertThat(exercises.size()).isEqualTo(1);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateExerciseTest() {
		
		Exercise exercise = exerciseDao.findById(1);
		exercise.setName("new name");
		
		Assertions.assertThat( exerciseDao.findById(1).getName()).isEqualTo(exercise.getName());
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteExerciseTest() {
		
		Exercise exercise = exerciseDao.findById(1);
		exerciseDao.delete(exercise);
		
		Assertions.assertThat( exerciseDao.findById(1)).isNull();;
	}
	
	@Test
	@Order(6)
	@Rollback(value = false)
	public void listAfterDeleteExerciseTest() {
		
		User user = userDao.findById(1);
		List<Exercise> exercises =  user.getExercises();
		Assertions.assertThat( exercises.size()).isEqualTo(0);
		userDao.delete(user);
	}
	

}
