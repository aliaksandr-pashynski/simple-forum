package com.pashynski.forum.feature.file.validators;


import com.pashynski.forum.feature.file.validators.ImageFileTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Constraint(validatedBy = ImageFileTypeValidator.class)
public @interface ImageFile {

    String message() default "Unsupported file type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}