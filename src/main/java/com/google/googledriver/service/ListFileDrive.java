package com.google.googledriver.service;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.googledriver.exception.ListFileDriverException;
import com.google.googledriver.model.FileDrive;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class ListFileDrive {

    private final List<FileDrive> filesDrivers;
    private final int pageSize;
    private Drive service;

    public ListFileDrive(String credentials, int pageSize) throws GeneralSecurityException, IOException{
        this.fillDrive(credentials);
        this.filesDrivers = new ArrayList<>();
        this.pageSize = pageSize;
    }

    public void files() throws IOException, ListFileDriverException {
        this.filesDrivers.clear();
        FileList result = service.files().list()
                .setPageSize(this.pageSize)
                .setFields("nextPageToken, files(id, name,mimeType,shared,webViewLink)")
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            throw new ListFileDriverException("No files found.");
        }
        files.forEach(this::addFileDrive);
    }

    public List<FileDrive> getFilesDrivers() {
        return filesDrivers;
    }

    private void addFileDrive(File file) {
        this.filesDrivers.add(new FileDrive(file.getId(), file.getName(), file.getMimeType(), file.getShared(), file.getWebViewLink()));
    }
    
    private void fillDrive(String credentials) throws GeneralSecurityException, IOException{
        this.service = new DriverService(credentials).driveService();
    }
    
}
