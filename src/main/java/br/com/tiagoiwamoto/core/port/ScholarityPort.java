package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.core.entity.ScholarityEntity;

import java.util.List;
import java.util.UUID;

public interface ScholarityPort {

    List<ScholarityEntity> all();
    ScholarityEntity recoveryByUuid(UUID uuid);
    ScholarityEntity save(ScholarityEntity data);
    ScholarityEntity update(ScholarityEntity data);
    void delete(ScholarityEntity data);

}
