package br.com.tiagoiwamoto.core.port;

import br.com.tiagoiwamoto.adapter.dto.ProfileDTO;
import br.com.tiagoiwamoto.core.entity.ProfileEntity;

import java.util.List;

public interface ProfilePort {

    List<ProfileEntity> all();
    ProfileEntity recoveryByUuid(ProfileEntity data);
    ProfileEntity save(ProfileEntity data);
    ProfileEntity update(ProfileEntity data);
    void delete(ProfileEntity data);

}
