package perscholas.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class SetForm {

	@NotEmpty(message = "weight cannot be empty")
	private Integer weight;

	@NotEmpty(message = "reps cannot be empty")
	private Integer reps;
}
