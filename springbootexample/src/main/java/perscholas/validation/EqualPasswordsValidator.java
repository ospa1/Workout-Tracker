package perscholas.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import perscholas.form.CreateUserForm;

public class EqualPasswordsValidator implements ConstraintValidator<EqualPasswords, CreateUserForm> {

	@Override
	public void initialize(EqualPasswords constraint) {
	}

	@Override
	public boolean isValid(CreateUserForm form, ConstraintValidatorContext context) {

		boolean valid = form.getPassword().equals(form.getConfirmPassword());
		if (valid) {
			System.out.println("password is equal");
		} else {
			System.out.println("password is not equal");
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("passwords not equal").addConstraintViolation();
			;
			// context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()).addConstraintViolation();
		}
		return valid;
	}
}
