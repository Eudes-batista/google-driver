package test;

import com.google.api.services.drive.model.File;
import com.google.googledriver.exception.ListFileDriverException;
import com.google.googledriver.service.ListFileDrive;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

public class ListFiles {

    public static void main(String[] args) throws IOException {
//        Credentials.Installed installed = Credentials.newInstalled();
//        installed.setClient_id("1018692177833-uodkvdj2vqvq0h37c9ohorm7eta2t37l.apps.googleusercontent.com");
//        installed.setProject_id("quickstart-1563120354667");
//        installed.setAuth_uri("https://accounts.google.com/o/oauth2/auth");
//        installed.setToken_uri("https://oauth2.googleapis.com/token");
//        installed.setAuth_provider_x509_cert_url("https://www.googleapis.com/oauth2/v1/certs");
//        installed.setClient_secret("DnLmMt1QWiYvPoz-5_3TQhY2");
//        installed.setRedirect_uris(new String[]{"urn:ietf:wg:oauth:2.0:oob", "http://localhost"});

        String json = Files.readAllLines(Paths.get("zion.json")).get(0);
        try {
            //files(json);
            findFile(json,"1TlTPemvGFlkuGoAz6GjRruR_EAIMl_ZN");
        } catch (GeneralSecurityException | IOException  ex) {
            System.out.println("ex = " + ex);
        }
    }

    private static void files(String credentials) throws IOException, ListFileDriverException, GeneralSecurityException {
        ListFileDrive listFileDrive = new ListFileDrive(credentials, 50);
        listFileDrive.files();
        listFileDrive.getFilesDrivers().forEach(System.out::println);
    }
    
    private static void findFile(String credentials,String id) throws GeneralSecurityException, IOException {
        ListFileDrive listFileDrive = new ListFileDrive(credentials, 1);
        File findFile = listFileDrive.findFile(id);
    }
    

}
