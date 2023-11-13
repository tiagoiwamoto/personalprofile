package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.core.entity.ProjectEntity;

import java.util.List;
import java.util.UUID;

public interface ProjectPort {

    List<ProjectEntity> all();
    ProjectEntity recoveryByUuid(UUID uuid);
    ProjectEntity save(ProjectEntity data);
    ProjectEntity update(ProjectEntity data);
    void delete(ProjectEntity data);

}
