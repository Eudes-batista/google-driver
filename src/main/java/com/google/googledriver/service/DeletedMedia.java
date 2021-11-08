/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.google.googledriver.service;

import com.google.api.client.http.HttpResponse;
import com.google.api.services.drive.Drive;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 *
 * @author eudes
 */
public class DeletedMedia {

    private final String credentials;
    private final String id;

    public DeletedMedia(String credentials, String id) {
        this.credentials = credentials;
        this.id = id;
    }

    public HttpResponse deleteMediaById() throws GeneralSecurityException, IOException {
        DriverService driverService = new DriverService(this.credentials);
        Drive driveService = driverService.driveService();
        HttpResponse executeUnparsed = driveService.files().delete(this.id).executeUnparsed();
        return executeUnparsed;
    }

}
