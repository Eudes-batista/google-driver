package test;

import com.google.googledriver.exception.ListFileDriverException;
import com.google.googledriver.model.Credentials;
import com.google.googledriver.model.FileDrive;
import com.google.googledriver.service.DownloadFileDrive;
import com.google.googledriver.service.ListFileDrive;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class DownloadTest {

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
            findPageSize(json).stream()
                    .filter(file -> verifyFileName(file))
                    .forEach(file -> {
                        try {
                            download(json, file);
                        } catch (GeneralSecurityException ex) {
                            ex.printStackTrace();
                        }
                    });
        } catch (IOException | ListFileDriverException | GeneralSecurityException ex) {
            System.out.println("ex = " + ex);
        }
    }

    private static void download(String credentials, FileDrive filesDriver) throws GeneralSecurityException {
        try {
            DownloadFileDrive downloadFileDrive = new DownloadFileDrive(credentials, filesDriver.getId());
            downloadFileDrive.setOriginPathFile("saiph.sql");
            downloadFileDrive.setSaveFile(true);
            downloadFileDrive.download();
        } catch (IOException ex) {
            System.out.println("ex = " + ex.getMessage());
        }
    }

    private static boolean verifyFileName(FileDrive filesDriver) {
        return filesDriver.getName().equals("saiph.sql");
    }

    private static List<FileDrive> findPageSize(String credentials) throws IOException, ListFileDriverException, GeneralSecurityException {
        ListFileDrive listFileDrive = new ListFileDrive(credentials, 50);
        listFileDrive.files();
        List<FileDrive> filesDrivers = listFileDrive.getFilesDrivers();
        return filesDrivers;
    }

}
