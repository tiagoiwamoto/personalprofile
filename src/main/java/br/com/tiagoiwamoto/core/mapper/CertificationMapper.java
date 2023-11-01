package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.CertificationDTO;
import br.com.tiagoiwamoto.adapter.dto.ProfileDTO;
import br.com.tiagoiwamoto.core.entity.CertificationEntity;
import br.com.tiagoiwamoto.core.entity.ProfileEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface CertificationMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    CertificationDTO toDto(CertificationEntity entity);
    @InheritInverseConfiguration(name = "toDto")
    CertificationEntity toEntity(CertificationDTO dto);

    void updateEntityFromDto(CertificationDTO dto, @MappingTarget CertificationEntity entity);

    void updateDtoFromEntity(CertificationEntity entity, @MappingTarget CertificationDTO domain);

    /*
    @Mapper(componentModel = "cdi")
public interface CustomerMapper {

    List<Customer> toDomainList(List<CustomerEntity> entities);

    Customer toDomain(CustomerEntity entity);

    @InheritInverseConfiguration(name = "toDomain")
    CustomerEntity toEntity(Customer domain);

    void updateEntityFromDomain(Customer domain, @MappingTarget CustomerEntity entity);

    void updateDomainFromEntity(CustomerEntity entity, @MappingTarget Customer domain);

}
     */

}
