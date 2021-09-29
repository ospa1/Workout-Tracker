package perscholas.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class ExerciseForm {

	@NotEmpty(message = "name cannot be empty")
	private String name;
}
