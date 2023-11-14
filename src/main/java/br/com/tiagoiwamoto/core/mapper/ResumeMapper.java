package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.ResumeDTO;
import br.com.tiagoiwamoto.core.entity.ResumeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ResumeMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(CourseCategoryMapper.class);

    ResumeDTO toDto(ResumeEntity entity);
    ResumeEntity toEntity(ResumeDTO dto);

}
