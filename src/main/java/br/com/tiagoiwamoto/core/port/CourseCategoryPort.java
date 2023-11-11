package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.core.entity.CourseCategoryEntity;

import java.util.List;
import java.util.UUID;

public interface CourseCategoryPort {

    List<CourseCategoryEntity> all();
    CourseCategoryEntity recoveryByUuid(UUID uuid);
    CourseCategoryEntity save(CourseCategoryEntity data);
    CourseCategoryEntity update(CourseCategoryEntity data);
    void delete(CourseCategoryEntity data);

}
