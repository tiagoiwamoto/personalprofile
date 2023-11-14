package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.core.entity.SkillEntity;

import java.util.List;
import java.util.UUID;

public interface SkillPort {

    List<SkillEntity> all();
    SkillEntity recoveryByUuid(UUID uuid);
    SkillEntity save(SkillEntity data);
    SkillEntity update(SkillEntity data);
    void delete(SkillEntity data);

}
