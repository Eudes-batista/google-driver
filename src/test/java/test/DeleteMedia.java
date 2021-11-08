package test;

import com.google.api.client.http.HttpResponse;
import com.google.googledriver.service.DeletedMedia;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

/**
 *
 * @author eudes
 */
public class DeleteMedia {

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        String credentials = Files.readAllLines(Paths.get("zion.json")).get(0);
        HttpResponse response = new DeletedMedia(credentials, "1PcoyM59HcGTqaQc_wcpiR79XbVRFpPe1").deleteMediaById();
        if (response.isSuccessStatusCode()) {
            System.out.println("deleted with success!!");
        }
    }

}
