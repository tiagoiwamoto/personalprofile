package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.ExperienceDTO;
import br.com.tiagoiwamoto.core.entity.ExperienceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ExperienceMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ExperienceDTO toDto(ExperienceEntity entity);
    ExperienceEntity toEntity(ExperienceDTO dto);

}
