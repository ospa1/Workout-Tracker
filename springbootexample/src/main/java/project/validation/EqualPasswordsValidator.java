package project.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import project.form.CreateUserForm;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, CreateUserForm> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void initialize(EqualPasswords constraint) {
	}

	@Override
	public boolean isValid(CreateUserForm form, ConstraintValidatorContext context) {

		boolean valid = form.getPassword().equals(form.getConfirmPassword());
		if (valid) {
			logger.info("password is equal");
		} else {
			logger.info("password is not equal");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("passwords not equal").addConstraintViolation();
			;
			// context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addConstraintViolation();
		}
		return valid;
	}
}
