package org.acme.mapper;

import org.acme.dto.model.MediaDTO;
import org.acme.model.Media;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IMediaMapper {
    IMediaMapper INSTANCE = Mappers.getMapper(IMediaMapper.class);

    @Named("toDto")
    MediaDTO toDto(Media entity);

    @Named("toEntity")
    Media toEntity(MediaDTO dto);
}