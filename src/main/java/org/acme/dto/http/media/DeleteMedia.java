package org.acme.dto.http.media;

public class DeleteMedia {
    public static final String MSG_NAME = "deleteMedia";

    public static class Input {
        public Long id;
    }

    public static class Output {
        public Boolean isSuccess;
        public String message;
    }
}
