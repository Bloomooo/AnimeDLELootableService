package org.acme.mapper;

import org.acme.dto.model.LootDTO;
import org.acme.model.Loot;
import org.acme.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = IMediaMapper.class)
public interface ILootMapper {
    ILootMapper INSTANCE = Mappers.getMapper(ILootMapper.class);

    @Mapping(target = "media", qualifiedByName = "toEntity")
    Loot toEntity(LootDTO dto);

    @Mapping(target = "media", qualifiedByName = "toDto")
    LootDTO toDto(Loot entity);

    @Mapping(target = "media", qualifiedByName = "toEntity")
    Character toCharacterEntity(LootDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateCharacterEntity(@MappingTarget Character existingCharacter, LootDTO character);
}
