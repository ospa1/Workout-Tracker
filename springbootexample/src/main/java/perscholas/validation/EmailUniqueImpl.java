package perscholas.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import perscholas.database.dao.UserDAO;
import perscholas.database.entity.User;

public class EmailUniqueImpl implements ConstraintValidator<EmailUnique, String> {

	@Autowired
	private UserDAO userDao;

	@Override
	public void initialize(EmailUnique unique) {
		// unique.message();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {

		if (StringUtils.isEmpty(email)) {
			return true;
		}

		User user = userDao.findByEmail(email);
		return (user == null);
	}
}
