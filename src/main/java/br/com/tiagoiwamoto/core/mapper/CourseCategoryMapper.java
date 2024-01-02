package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.CourseCategoryDTO;
import br.com.tiagoiwamoto.core.entity.CourseCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface CourseCategoryMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(CourseCategoryMapper.class);

//    @Mapping(target = "courses", ignore = true)
    CourseCategoryDTO toDto(CourseCategoryEntity entity);
    CourseCategoryEntity toEntity(CourseCategoryDTO dto);

}
