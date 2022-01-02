package com.kayson.trackr.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Constraint(validatedBy = {})
@Retention(RUNTIME)
@Pattern(regexp = "^[a-z]+")
@NotEmpty
@ReportAsSingleViolation
public @interface CategoryName {
    String message() default "only lower case alphabets allowed";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
