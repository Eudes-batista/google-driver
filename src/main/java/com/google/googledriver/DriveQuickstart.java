package com.google.googledriver;

import com.google.api.services.drive.Drive;
import com.google.googledriver.exception.ListFileDriverException;
import com.google.googledriver.model.Credentials;
import com.google.googledriver.model.FileDrive;
import com.google.googledriver.service.DownloadFileDrive;
import com.google.googledriver.service.DriverService;
import com.google.googledriver.service.ListFileDrive;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class DriveQuickstart {

    public static void main(String... args) {
        try {
            Credentials.Installed installed = Credentials.newInstalled();
            installed.setClient_id("1018692177833-uodkvdj2vqvq0h37c9ohorm7eta2t37l.apps.googleusercontent.com");
            installed.setProject_id("quickstart-1563120354667");
            installed.setAuth_uri("https://accounts.google.com/o/oauth2/auth");
            installed.setToken_uri("https://oauth2.googleapis.com/token");
            installed.setAuth_provider_x509_cert_url("https://www.googleapis.com/oauth2/v1/certs");
            installed.setClient_secret("DnLmMt1QWiYvPoz-5_3TQhY2");
            installed.setRedirect_uris(new String[]{"urn:ietf:wg:oauth:2.0:oob", "http://localhost"});

            String json = installed.toString();
            Drive service = new DriverService(json).driveService();
            ListFileDrive listFileDrive = new ListFileDrive(service, 50);
            listFileDrive.files();
            List<FileDrive> filesDrivers = listFileDrive.getFilesDrivers();
            for (FileDrive filesDriver : filesDrivers) {
                if (filesDriver.getName().equals("saiph.sql")) {
                    DownloadFileDrive downloadFileDrive = new DownloadFileDrive(service, filesDriver.getId());
                    downloadFileDrive.setOriginPathFile("saiph.sql");
                    downloadFileDrive.setSaveFile(true);
                    downloadFileDrive.download();
                    break;
                }
            }
//            UploadFile uploadFile = new UploadFile(
//                    service,
//                    Arrays.asList("1kLRVGdAlpNdrBfPSXmdpgfUs6xhhOxF_"),
//                    "Schemas.rar",
//                    "/home/administrador/NetBeansProjects/googleDriver/Schemas.rar",
//                    "application/x-zip-compressed"
//            );
//            UploadFile uploadFile = new UploadFile(
//                    service,
//                    "/home/administrador/NetBeansProjects/googleDriver/Schemas.rar",
//                    "application/x-zip-compressed"
//            );
//            uploadFile.send();
        } catch (IOException | GeneralSecurityException | ListFileDriverException ex) {
            System.out.println("ex = " + ex);
        }
    }

}
