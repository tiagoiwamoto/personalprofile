package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.core.entity.ExperienceEntity;

import java.util.List;
import java.util.UUID;

public interface ExperiencePort {

    List<ExperienceEntity> all();
    ExperienceEntity recoveryByUuid(UUID uuid);
    ExperienceEntity save(ExperienceEntity data);
    ExperienceEntity update(ExperienceEntity data);
    void delete(ExperienceEntity data);

}
