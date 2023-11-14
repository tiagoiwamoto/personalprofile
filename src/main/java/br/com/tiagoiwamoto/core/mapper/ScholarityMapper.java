package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.ScholarityDTO;
import br.com.tiagoiwamoto.core.entity.ScholarityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ScholarityMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(CourseCategoryMapper.class);

    ScholarityDTO toDto(ScholarityEntity entity);
    ScholarityEntity toEntity(ScholarityDTO dto);

}
