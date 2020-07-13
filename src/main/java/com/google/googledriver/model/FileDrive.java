package com.google.googledriver.model;

import java.util.Objects;

public class FileDrive {
    private String id;
    private String name;
    private String mimeType;
    private boolean shared;
    private String webViewLink;

    public FileDrive() {
    }

    public FileDrive(String id, String name, String mimeType, boolean shared, String webViewLink) {
        this.id = id;
        this.name = name;
        this.mimeType = mimeType;
        this.shared = shared;
        this.webViewLink = webViewLink;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public String getWebViewLink() {
        return webViewLink;
    }

    public void setWebViewLink(String webViewLink) {
        this.webViewLink = webViewLink;
    }

    @Override
    public String toString() {
        return "FileDrive{" + "id=" + id + ", name=" + name + ", mimeType=" + mimeType + ", shared=" + shared + ", webViewLink=" + webViewLink + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FileDrive other = (FileDrive) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
