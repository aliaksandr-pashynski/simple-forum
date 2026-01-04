package com.pashynski.forum.feature.file;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FileService {

    private final MinioClient minioClient;
    private final MinioObjectNameProvider avatarNameProvider;
    private final String bucket;

    public FileService(
            MinioClient minioClient,
            MinioObjectNameProvider nameProvider,
            @Value("${minio.bucket}") String bucket
    ) {
        this.minioClient = minioClient;
        this.avatarNameProvider = nameProvider;
        this.bucket = bucket;
    }

    public FileUploadResponse uploadAvatar(InputStream inputStream, String contentType, long size) {
        String filename = avatarNameProvider.getName();
        try {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(filename)
                            .stream(inputStream, size, -1)
                            .contentType(contentType)
                            .build()
            );
            return FileUploadResponse.success(
                    FileInfo.builder()
                            .bucket(bucket)
                            .contentType(contentType)
                            .sizeBytes(size)
                            .objectName(filename)
                            .build()
            );
        } catch (Exception e) {
            return FileUploadResponse.error(e.getMessage());
        }
    }
}