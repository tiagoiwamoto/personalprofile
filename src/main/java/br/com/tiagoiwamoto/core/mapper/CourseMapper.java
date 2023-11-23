package br.com.tiagoiwamoto.core.mapper;

import br.com.tiagoiwamoto.adapter.dto.CourseDTO;
import br.com.tiagoiwamoto.core.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Objects;
import java.util.UUID;

@Mapper(componentModel = "cdi")
public interface CourseMapper {

//    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

//    @Mapping(source = "entity", target = "courseCategory", qualifiedByName = "categoryUuid")
    @Mapping( expression = "java(toCourseCategoryDto(entity))", target = "courseCategoryUuid")
    CourseDTO toDto(CourseEntity entity);
    @Mapping(target = "courseCategory", ignore = true)
    CourseEntity toEntity(CourseDTO dto);

//    @Named("categoryUuid")
    default UUID toCourseCategoryDto(CourseEntity entity){
        if(Objects.isNull(entity.getCourseCategory())){
            return null;
        }
        return entity.getCourseCategory().getUuid();
    }

//    void updateEntityFromDto(CertificationDTO dto, @MappingTarget CertificationEntity entity);
//
//    void updateDtoFromEntity(CertificationEntity entity, @MappingTarget CertificationDTO domain);

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
