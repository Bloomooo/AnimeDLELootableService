package org.acme.dto.http.loot.character;

import org.acme.dto.model.LootDTO;

import java.util.List;

public class RandomLoadingCharacters {
    public static final String MSG_NAME = "randomLoadingCharacters";

    public static class Input {
    }

    public static class Output {
        public List<LootDTO> characters;
        public Boolean isSuccess;
        public String message;
    }
}
