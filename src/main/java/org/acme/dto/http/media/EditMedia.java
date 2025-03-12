package org.acme.dto.http.media;

import org.acme.dto.model.MediaDTO;

public class EditMedia {
    public static final String MSG_NAME = "editMedia";

    public static class Input {
        public MediaDTO media;
        public String logo;
    }

    public static class Output {
        public Boolean isSuccess;
        public String message;
    }
}
