package com.google.googledriver.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Credentials {
    
    public static Installed newInstalled() {
        Credentials credentials = new Credentials();
        Credentials.Installed installed = credentials.new Installed();
        return installed;
    }
    

    public class Installed {

        private String client_id;
        private String project_id;
        private String auth_uri;
        private String token_uri;
        private String auth_provider_x509_cert_url;
        private String client_secret;
        private String redirect_uris;

        public String getClient_id() {
            return client_id;
        }

        public void setClient_id(String client_id) {
            this.client_id = "\""+client_id+"\"";
        }

        public String getProject_id() {
            return project_id;
        }

        public void setProject_id(String project_id) {
            this.project_id = "\""+project_id+"\"";
        }

        public String getAuth_uri() {
            return auth_uri;
        }

        public void setAuth_uri(String auth_uri) {
            this.auth_uri = "\""+auth_uri+"\"";
        }

        public String getToken_uri() {
            return token_uri;
        }

        public void setToken_uri(String token_uri) {
            this.token_uri = "\""+token_uri+"\"";
        }

        public String getAuth_provider_x509_cert_url() {
            return auth_provider_x509_cert_url;
        }

        public void setAuth_provider_x509_cert_url(String auth_provider_x509_cert_url) {
            this.auth_provider_x509_cert_url = "\""+auth_provider_x509_cert_url+"\"";
        }

        public String getClient_secret() {
            return client_secret;
        }

        public void setClient_secret(String client_secret) {
            this.client_secret = "\""+client_secret+"\"";
        }

        public String getRedirect_uris() {
            return redirect_uris;
        }

        public void setRedirect_uris(String[] redirect_uris) {
            this.redirect_uris = Arrays.asList(redirect_uris).stream().map(elemento -> "\""+elemento+"\"").collect(Collectors.joining(","));
        }

        @Override
        public String toString() {
            return "{ \"installed\" :{\"client_id\":" + client_id + ", \"project_id\":" + project_id + ", \"auth_uri\":" + auth_uri + ", \"token_uri\":" + token_uri + ", \"auth_provider_x509_cert_url\":" + auth_provider_x509_cert_url + ", \"client_secret\":" + client_secret + ", \"redirect_uris\":[" + redirect_uris + "]} }";
        }        
    }
    
    
}
