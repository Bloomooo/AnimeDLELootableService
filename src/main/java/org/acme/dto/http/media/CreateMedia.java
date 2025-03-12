package org.acme.dto.http.media;

import org.acme.dto.model.MediaDTO;

public class CreateMedia {
    public static final String MSG_NAME = "createMedia";

    public static class Input {
        public MediaDTO media;
    }

    public static class Output {
        public Boolean isSuccess;
        public String message;
    }
}
