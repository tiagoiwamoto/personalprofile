package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.core.entity.SoftwareEntity;

import java.util.List;
import java.util.UUID;

public interface SoftwarePort {

    List<SoftwareEntity> all();
    SoftwareEntity recoveryByUuid(UUID uuid);
    SoftwareEntity save(SoftwareEntity data);
    SoftwareEntity update(SoftwareEntity data);
    void delete(SoftwareEntity data);

}
