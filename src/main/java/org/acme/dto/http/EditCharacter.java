package org.acme.dto.http;

import org.acme.dto.model.LootDTO;

public class EditCharacter {
    public static final String MSG_NAME = "editCharacter";

    public static class Input {
        public LootDTO character;
        public String splashartCard;
        public String splashartBanner;
    }

    public static class Output {
        public Boolean isSuccess;
        public String message;
    }
}
