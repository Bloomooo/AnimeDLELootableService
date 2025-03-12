package org.acme.dto.http.media;

import org.acme.dto.model.MediaDTO;

import java.util.List;

public class GetAllMedia {
    public static final String MSG_NAME = "getAllMedia";

    public static class Input {
        public int pageNum;
        public int pageSize;
    }

    public static class Output {
        public List<MediaDTO> media;
        public Boolean isSuccess;
        public String message;
    }
}
