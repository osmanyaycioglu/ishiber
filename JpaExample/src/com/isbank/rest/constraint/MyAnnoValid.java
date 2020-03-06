package com.isbank.rest.constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@Constraint(validatedBy = MyValidator.class)
public @interface MyAnnoValid {
		int length() default 20;
		
		String message() default "{javax.validation.constraints.MyAnno.message}";

		Class<?>[] groups() default { };

		Class<? extends Payload>[] payload() default { };

}
