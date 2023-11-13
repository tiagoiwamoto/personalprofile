package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.ProjectDTO;
import br.com.tiagoiwamoto.core.entity.ProjectEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ProjectMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(CourseCategoryMapper.class);

    ProjectDTO toDto(ProjectEntity entity);
    ProjectEntity toEntity(ProjectDTO dto);

}
