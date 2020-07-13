package com.google.googledriver.service;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.googledriver.exception.UploadException;
import java.io.IOException;
import java.util.List;

public class UploadFile {

    private final Drive service;
    private final String originFile;
    private final String fileType;
    private List<String> folders;
    private String targetFile;
    private String fileId;
    private String fileShared;

    public UploadFile(Drive service, List<String> folders, String targetFile, String originFile, String fileType) throws UploadException {
        this.service = service;
        this.folders = folders;
        this.targetFile = targetFile;
        this.originFile = originFile;
        this.fileType = fileType;
        this.validation();
    }

    public UploadFile(Drive service, String originFile, String fileType) throws UploadException {
        this.service = service;
        this.originFile = originFile;
        this.fileType = fileType;
        this.validation();
    }

    public void send() throws IOException {
        java.io.File filePath = new java.io.File(this.originFile);
        File fileMetadata = new File();
        fileMetadata.setName(this.targetFile == null ? filePath.getName() : this.targetFile);
        if (this.folders != null) {
            fileMetadata.setParents(this.folders);
        }
        FileContent mediaContent = new FileContent(this.fileType, filePath);
        File file = service.files().create(fileMetadata, mediaContent)
                .setSupportsTeamDrives(true)
                .setFields("id,webViewLink")
                .execute();
        this.fileId = file.getId();
        this.fileShared = file.getWebViewLink();
    }

    public String getFileId() {
        return fileId;
    }

    public String getFileShared() {
        return fileShared;
    }

    private void validation() throws UploadException {
        if (this.service == null) {
            throw new UploadException("Not found instance Driver service");
        }
        if (this.originFile == null || this.originFile.trim().isEmpty()) {
            throw new UploadException("is required the origin File");
        }
        if (this.fileType == null || this.fileType.trim().isEmpty()) {
            throw new UploadException("is required the file Type");
        }
    }
}
