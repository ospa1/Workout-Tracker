package project.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class SetForm {

	@PositiveOrZero(message = "has to be posistive or zero")
	@NotNull(message = "reps cannot be empty")
	private Integer weight;

	@PositiveOrZero(message = "has to be posistive or zero")
	@NotNull(message = "reps cannot be empty")
	private Integer reps;
}
