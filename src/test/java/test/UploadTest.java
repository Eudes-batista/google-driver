package test;

import com.google.googledriver.model.Credentials;
import com.google.googledriver.service.UploadFile;
import com.google.googledriver.exception.UploadException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class UploadTest {

    public static void main(String[] args) {
        Credentials.Installed installed = Credentials.newInstalled();
        installed.setClient_id("1018692177833-uodkvdj2vqvq0h37c9ohorm7eta2t37l.apps.googleusercontent.com");
        installed.setProject_id("quickstart-1563120354667");
        installed.setAuth_uri("https://accounts.google.com/o/oauth2/auth");
        installed.setToken_uri("https://oauth2.googleapis.com/token");
        installed.setAuth_provider_x509_cert_url("https://www.googleapis.com/oauth2/v1/certs");
        installed.setClient_secret("DnLmMt1QWiYvPoz-5_3TQhY2");
        installed.setRedirect_uris(new String[]{"urn:ietf:wg:oauth:2.0:oob", "http://localhost"});

        try {
            uploadFile(installed.toString());
        } catch (GeneralSecurityException | IOException | UploadException ex) {
            System.out.println("ex = " + ex.getMessage());
        }
    }

    private static void uploadFile(String credentials) throws IOException, UploadException, GeneralSecurityException {
        UploadFile uploadFile = new UploadFile(
                credentials,
                Arrays.asList("1kLRVGdAlpNdrBfPSXmdpgfUs6xhhOxF_"),
                "Schemas.rar",
                "/home/administrador/NetBeansProjects/googleDriver/Schemas.rar",
                "application/x-zip-compressed"
        );
        uploadFile.send();
    }

    private static void uploadFileNotTarget(String credentials) throws IOException, UploadException, GeneralSecurityException {
        UploadFile uploadFile = new UploadFile(
                credentials,
                "/home/administrador/NetBeansProjects/googleDriver/Schemas.rar",
                "application/x-zip-compressed"
        );
        uploadFile.send();
    }

}
