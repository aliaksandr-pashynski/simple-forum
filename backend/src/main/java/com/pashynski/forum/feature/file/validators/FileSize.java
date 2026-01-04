package com.pashynski.forum.feature.file.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Constraint(validatedBy = FileSizeValidator.class)
public @interface FileSize {

    String message() default "File size exceeded";

    long max();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}