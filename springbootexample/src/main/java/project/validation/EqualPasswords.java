package project.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Constraint(validatedBy = EqualPasswordsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EqualPasswords {

	String message() default "Passwords do not match";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
