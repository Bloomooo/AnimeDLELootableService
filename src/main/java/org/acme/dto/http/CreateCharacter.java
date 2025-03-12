package org.acme.dto.http;

import org.acme.dto.model.LootDTO;

public class CreateCharacter {
    public static final String MSG_NAME = "createCharacter";

    public static class Input {
        public LootDTO character;
    }

    public static class Output {
        public Boolean isSuccess;
        public String message;
    }
}
