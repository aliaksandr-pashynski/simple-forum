package com.pashynski.forum.feature.file;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponse {
    private boolean success;
    private ErrorMessage error;
    private FileInfo fileInfo;

    public static FileUploadResponse success(FileInfo fileInfo) {
        return new FileUploadResponse(true, null, fileInfo);
    }

    public static FileUploadResponse error(String errorMessage) {
        return new FileUploadResponse(false, new ErrorMessage(errorMessage), null);
    }

    @Data
    @AllArgsConstructor
    public static class ErrorMessage {
        private String error;
    }
}