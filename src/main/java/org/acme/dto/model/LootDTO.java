package org.acme.dto.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LootDTO {
    private String name;
    private int rarity;
    private Boolean limited;
    private MediaDTO media;
    private String description;
}
