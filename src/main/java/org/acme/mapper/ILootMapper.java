package org.acme.mapper;

import org.acme.dto.model.LootDTO;
import org.acme.model.Loot;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ILootMapper {
    ILootMapper INSTANCE = Mappers.getMapper(ILootMapper.class);

    Loot toEntity(org.acme.dto.model.LootDTO dto);
    LootDTO toDto(Loot entity);
}
