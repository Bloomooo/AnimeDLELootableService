package org.acme.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BannerDTO {
    private Long id;
    private LootDTO limited;
    private List<LootDTO> loots;
}