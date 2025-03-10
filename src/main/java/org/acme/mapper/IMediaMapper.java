package org.acme.mapper;

import org.acme.dto.model.MediaDTO;
import org.acme.model.Media;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IMediaMapper {
    IMediaMapper INSTANCE = Mappers.getMapper(IMediaMapper.class);

    Media toEntity(MediaDTO dto);
    MediaDTO toDto(Media entity);
}
