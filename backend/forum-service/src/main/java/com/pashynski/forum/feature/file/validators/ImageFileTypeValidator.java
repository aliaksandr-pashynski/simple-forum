package com.pashynski.forum.feature.file.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Pattern;

public class ImageFileTypeValidator implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("image/jpeg|image/png");
        return file != null && pattern.matcher(file.getContentType()).matches();
    }
}