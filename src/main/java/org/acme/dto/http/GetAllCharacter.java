package org.acme.dto.http;

import org.acme.dto.model.LootDTO;

import java.util.List;

public class GetAllCharacter {
    public static final String MSG_NAME = "getAllCharacter";

    public static class Input {
    }

    public static class Output {
        public List<LootDTO> characters;
        public Boolean isSuccess;
        public String message;
    }
}
