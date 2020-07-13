package com.google.googledriver;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.client.http.FileContent;
import com.google.api.services.drive.model.FileList;
import com.google.googledriver.model.Credentials;
import com.google.googledriver.service.DriverService;

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
            listarArquivos(service);
        } catch (GeneralSecurityException | IOException ex) {
            System.out.println("ex = " + ex.getMessage());
        }
    }

    private static void listarArquivos(Drive service) throws IOException {
        // Print the names and IDs for up to 10 files.
        FileList result = service.files().list()
                .setPageSize(50)
                .setFields("nextPageToken, files(id, name,mimeType,shared,webViewLink)")
                .execute();
        List<File> files = result.getFiles();
        if (files == null || files.isEmpty()) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s) %s %s\n", file.getName(), file.getMimeType(), file.getShared(), file.getWebViewLink());
            }
        }
    }

    private static void upload(Drive service) throws IOException {
        File fileMetadata = new File();
        fileMetadata.setName("Schemas.rar");
        fileMetadata.setParents(List.of("1kLRVGdAlpNdrBfPSXmdpgfUs6xhhOxF_"));
        java.io.File filePath = new java.io.File("/home/administrador/NetBeansProjects/googleDriver/Schemas.rar");
        FileContent mediaContent = new FileContent("application/x-zip-compressed", filePath);
        File file = service.files().create(fileMetadata, mediaContent)
                .setSupportsTeamDrives(true)
                .setFields("id,webViewLink")
                .execute();
        System.out.println("File ID: " + file.getWebViewLink());
    }
}
