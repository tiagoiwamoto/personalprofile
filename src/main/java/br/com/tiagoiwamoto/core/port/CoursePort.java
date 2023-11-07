package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.core.entity.CourseEntity;

import java.util.List;
import java.util.UUID;

public interface CoursePort {

    List<CourseEntity> all();
    CourseEntity recoveryByUuid(UUID uuid);
    CourseEntity save(CourseEntity data);
    CourseEntity update(CourseEntity data);
    void delete(CourseEntity data);

}
