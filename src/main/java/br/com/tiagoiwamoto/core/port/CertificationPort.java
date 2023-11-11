package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.core.entity.CertificationEntity;

import java.util.List;
import java.util.UUID;

public interface CertificationPort {

    List<CertificationEntity> all();
    CertificationEntity recoveryByUuid(UUID uuid);
    CertificationEntity save(CertificationEntity data);
    CertificationEntity update(CertificationEntity data);
    void delete(CertificationEntity data);

}
