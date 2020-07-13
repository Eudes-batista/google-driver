package com.google.googledriver.service;

import com.google.api.services.drive.Drive;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class DownloadFileDrive {

    private final Drive service;
    private final String fileId;
    private final String originPathFile;

    public DownloadFileDrive(Drive service, String fileId, String originPathFile) {
        this.service = service;
        this.fileId = fileId;
        this.originPathFile = originPathFile;
    }

    public void download() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        this.service.files().get(this.fileId)
                .executeMediaAndDownloadTo(outputStream);
        byte[] byteArray = outputStream.toByteArray();
        try (PrintStream printStream = new PrintStream(new File(this.originPathFile))) {
            printStream.write(byteArray);
            printStream.flush();
        }
    }

}
