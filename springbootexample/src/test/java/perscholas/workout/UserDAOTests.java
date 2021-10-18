package perscholas.workout;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import perscholas.database.dao.UserDAO;
import perscholas.database.entity.User;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class UserDAOTests {

	@Autowired
	private UserDAO userDao;

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
	public void getUserTest() {
		User user = userDao.findById(1);
		Assertions.assertThat(user.getId()).isEqualTo(1);
	}
	
	
	@Test
	@Order(3)
	@Rollback(value = false)
	public void updateUserTest() {
		User user = userDao.findById(1);
		user.setEmail("updatedEmail@email.com");
		Assertions.assertThat(userDao.findById(1).getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void deleteUserTest() {
		User user = userDao.findById(1);
		userDao.delete(user);
		Optional<User> optionalUser = Optional.ofNullable(userDao.findByEmail(user.getEmail()));

		User tempUser = null;
		if (optionalUser.isPresent()) {
			tempUser = optionalUser.get();
		}

		Assertions.assertThat(tempUser).isNull();
	}
	
	@Test
	@Order(5)
	public void getListOfUsersAfterDeletion() {
		List<User> users = userDao.findAll();
		Assertions.assertThat(users.size()).isEqualTo(0);
	}
}