package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.core.entity.ResumeEntity;

import java.util.List;
import java.util.UUID;

public interface ResumePort {

    List<ResumeEntity> all();
    ResumeEntity recoveryByUuid(UUID uuid);
    ResumeEntity save(ResumeEntity data);
    ResumeEntity update(ResumeEntity data);
    void delete(ResumeEntity data);

}
