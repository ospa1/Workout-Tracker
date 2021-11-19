package project.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginForm {

	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Email is invalid")
	private String username;

	@NotEmpty(message = "Password cannot be empty")
	@Size(min = 10, max = 25, message = "Password must be between 10 and 25 characters")
	private String password;
}
