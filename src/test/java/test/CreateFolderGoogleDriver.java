/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.api.services.drive.model.File;
import com.google.googledriver.service.CreateFolder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

/**
 *
 * @author eudes
 */
public class CreateFolderGoogleDriver {

    private static final String FOLDER_MIME_TYPE = "application/vnd.google-apps.folder";

    public static void main(String[] args) throws IOException, GeneralSecurityException {

        String credentials = Files.readAllLines(Paths.get("zion.json")).get(0);

        CreateFolder createFolder = new CreateFolder(credentials, "11593321000163");

        File createdFolder = createFolder.createFolder();

        System.out.println("createdFolder = " + createdFolder);

    }

}
