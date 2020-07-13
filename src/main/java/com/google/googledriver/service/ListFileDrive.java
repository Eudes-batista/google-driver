package com.google.googledriver.service;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.googledriver.exception.ListFileDriverException;
import com.google.googledriver.model.FileDrive;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListFileDrive {

    private final Drive service;
    private final List<FileDrive> filesDrivers;
    private final int pageSize;

    public ListFileDrive(Drive service, int pageSize) {
        this.service = service;
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

    private void addFileDrive(File file) {
        this.filesDrivers.add(new FileDrive(file.getId(), file.getName(), file.getMimeType(), file.getShared(), file.getWebViewLink()));
    }

    public List<FileDrive> getFilesDrivers() {
        return filesDrivers;
    }
}
