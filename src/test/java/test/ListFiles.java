package test;

import com.google.api.services.drive.Drive;
import com.google.googledriver.exception.ListFileDriverException;
import com.google.googledriver.model.Credentials;
import com.google.googledriver.service.DriverService;
import com.google.googledriver.service.ListFileDrive;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class ListFiles {

    public static void main(String[] args) {
        Credentials.Installed installed = Credentials.newInstalled();
        installed.setClient_id("1018692177833-uodkvdj2vqvq0h37c9ohorm7eta2t37l.apps.googleusercontent.com");
        installed.setProject_id("quickstart-1563120354667");
        installed.setAuth_uri("https://accounts.google.com/o/oauth2/auth");
        installed.setToken_uri("https://oauth2.googleapis.com/token");
        installed.setAuth_provider_x509_cert_url("https://www.googleapis.com/oauth2/v1/certs");
        installed.setClient_secret("DnLmMt1QWiYvPoz-5_3TQhY2");
        installed.setRedirect_uris(new String[]{"urn:ietf:wg:oauth:2.0:oob", "http://localhost"});

        String json = installed.toString();
        try {
            Drive service = new DriverService(json).driveService();
            files(service);
        } catch (GeneralSecurityException | IOException | ListFileDriverException ex) {
            System.out.println("ex = " + ex);
        }
    }

    private static void files(Drive service) throws IOException, ListFileDriverException {
        ListFileDrive listFileDrive = new ListFileDrive(service, 50);
        listFileDrive.files();
        listFileDrive.getFilesDrivers()
                .forEach(System.out::println);
    }

}
