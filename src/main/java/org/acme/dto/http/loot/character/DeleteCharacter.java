package org.acme.dto.http.loot.character;

public class DeleteCharacter {
    public static final String MSG_NAME = "deleteCharacter";

    public static class Input {
        public Long id;
    }

    public static class Output {
        public Boolean isSuccess;
        public String message;
    }
}
