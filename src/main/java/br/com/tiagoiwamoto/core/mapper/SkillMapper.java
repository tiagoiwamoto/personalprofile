package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.SkillDTO;
import br.com.tiagoiwamoto.core.entity.SkillEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface SkillMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(CourseCategoryMapper.class);

    SkillDTO toDto(SkillEntity entity);
    SkillEntity toEntity(SkillDTO dto);

}
