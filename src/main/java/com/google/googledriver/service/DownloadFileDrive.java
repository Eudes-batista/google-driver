package com.google.googledriver.service;

import com.google.api.services.drive.Drive;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class DownloadFileDrive {

    private final Drive service;
    private final String fileId;
    private String originPathFile;
    private boolean saveFile;
    private byte[] fileContent;

    public DownloadFileDrive(Drive service, String fileId) {
        this.service = service;
        this.fileId = fileId;
    }

    public void download() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        this.service.files().get(this.fileId)
                .executeMediaAndDownloadTo(outputStream);
        byte[] byteArray = outputStream.toByteArray();
        this.fileContent = byteArray;
        if (!this.saveFile) {
            return;
        }
        try (PrintStream printStream = new PrintStream(new File(this.originPathFile))) {
            printStream.write(byteArray);
            printStream.flush();
        }
    }

    public void setSaveFile(boolean saveFile) {
        this.saveFile = saveFile;
    }

    public void setOriginPathFile(String originPathFile) {
        this.originPathFile = originPathFile;
    }

    public byte[] getFileContent() {
        return fileContent;
    }
}
