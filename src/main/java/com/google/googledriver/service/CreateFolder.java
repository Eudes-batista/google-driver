package com.google.googledriver.service;

import com.google.api.services.drive.Drive;
import java.io.IOException;
import java.security.GeneralSecurityException;
import com.google.api.services.drive.model.File;

/**
 *
 * @author eudes
 */
public class CreateFolder {

    private static final String FOLDER_MIME_TYPE = "application/vnd.google-apps.folder";
    private final String credentials;
    private final String nameFolder;

    public CreateFolder(String credentials, String nameFolder) {
        this.credentials = credentials;
        this.nameFolder = nameFolder;
    }

    public File createFolder() throws GeneralSecurityException, IOException {
        DriverService driverService = new DriverService(this.credentials);

        Drive driveService = driverService.driveService();

        File fileMetaData = new File();
        fileMetaData.setName(this.nameFolder);
        fileMetaData.setMimeType(FOLDER_MIME_TYPE);

        File createdFolder = driveService.files().create(fileMetaData).setFields("id,name,mimeType,shared,webViewLink").execute();
        return createdFolder;
    }

}
