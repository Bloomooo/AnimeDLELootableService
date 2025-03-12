package org.acme.dto.http.banner;

import org.acme.dto.model.BannerDTO;

public class GetBanner {
    public static final String MSG_NAME = "getBanner";

    public static class Input {
    }

    public static class Output {
        public BannerDTO banner;
        public Boolean isSuccess;
        public String message;
    }
}
