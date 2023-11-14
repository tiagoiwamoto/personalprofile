package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.SoftwareDTO;
import br.com.tiagoiwamoto.core.entity.SoftwareEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface SoftwareMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(CourseCategoryMapper.class);

    SoftwareDTO toDto(SoftwareEntity entity);
    SoftwareEntity toEntity(SoftwareDTO dto);

}
