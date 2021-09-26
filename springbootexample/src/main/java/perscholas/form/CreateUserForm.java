package perscholas.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import perscholas.validation.EmailUnique;
import perscholas.validation.EqualPasswords;

@EqualPasswords(message = "passwords must match")
@Data
public class CreateUserForm {

	@NotEmpty(message = "email cannot be empty")
	@Email(message = "Email is invalid")
	@EmailUnique(message = "Email already exists")
	private String email;

	@NotEmpty(message = "password cannot be empty")
	@Size(min = 10, max = 25, message = "password must be between 10 and 25 characters")
	private String password;

	@NotEmpty(message = "confirm password cannot be empty")
	private String confirmPassword;
}
