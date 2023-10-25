package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.CertificationDTO;
import br.com.tiagoiwamoto.adapter.dto.ProfileDTO;
import br.com.tiagoiwamoto.core.entity.CertificationEntity;
import br.com.tiagoiwamoto.core.entity.ProfileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CertificationMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    CertificationDTO toDto(CertificationEntity entity);
    CertificationEntity toEntity(CertificationDTO dto);

}
