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
		if (form.getPassword().equals(form.getConfirmPassword())) {
			System.out.println("shit is not equal");
		}
		return form.getPassword().equals(form.getConfirmPassword());
	}
}
