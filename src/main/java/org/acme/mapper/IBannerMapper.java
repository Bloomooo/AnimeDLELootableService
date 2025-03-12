package org.acme.mapper;

import org.acme.dto.model.BannerDTO;
import org.acme.model.Banner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ILootMapper.class)
public interface IBannerMapper {
    public IBannerMapper INSTANCE = Mappers.getMapper(IBannerMapper.class);

    @Mapping(target = "limited", qualifiedByName = "toEntity")
    @Mapping(target = "loots", qualifiedByName = "toEntity")
    Banner toEntity(BannerDTO bannerDTO);

    @Mapping(target = "limited", qualifiedByName = "toDto")
    @Mapping(target = "loots", qualifiedByName = "toDto")
    BannerDTO toDTO(Banner banner);
}
