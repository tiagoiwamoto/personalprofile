package br.com.tiagoiwamoto.adapter.out;

import br.com.tiagoiwamoto.core.entity.ProfileEntity;
import br.com.tiagoiwamoto.core.port.ProfilePort;
import br.com.tiagoiwamoto.core.repository.ProfileRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@ApplicationScoped
@Slf4j
public class ProfileAdapter implements ProfilePort, Serializable {

    @Inject
    private ProfileRepository repository;
    private final String ADAPTER_NAME = "profile";

    private static final long serialVersionUID = 2328956355430633139L;

    @Override
    public List<ProfileEntity> all() {
        log.info("recuperando dados para o metodo all() domínio {}", ADAPTER_NAME);
        var records = this.repository.findAll().list();
        log.info("Dados recuperados: {}", records.size());
        return records;
    }

    @Override
    public ProfileEntity recoveryByUuid(ProfileEntity data) {
        log.info("recuperando dados para o metodo byUuid() domínio {}", ADAPTER_NAME);
        var record = this.repository.find("uuid", data.getUuid()).firstResult();
        log.info("Dado recuperado: {}", record);
        return record;
    }

    @Override
    @Transactional
    public ProfileEntity save(ProfileEntity data) {
        log.info("inciando gravação para o metodo save() domínio {}", ADAPTER_NAME);
        try{
            this.repository.persist(data);
            log.info("Dado salvo: {}", data);
            return data;
        }catch (Exception e){
            log.error("não foi possível gravar o dado para o metodo save() domínio {}", ADAPTER_NAME);
            throw new RuntimeException(); //TODO: Criar exception de falha ao gravar
        }
    }

    @Override
    public ProfileEntity update(ProfileEntity data) {
        log.info("inciando atualização para o metodo update() domínio {}", ADAPTER_NAME);
        try{
            this.repository.persist(data);
            log.info("Dado atualizado: {}", data);
            return data;
        }catch (Exception e){
            log.error("não foi possível atualizar o dado para o metodo save() domínio {}", ADAPTER_NAME);
            throw new RuntimeException(); //TODO: Criar exception de falha ao atualizar
        }
    }

    @Override
    public void delete(ProfileEntity data) {
        log.info("inciando remoção para o metodo delete() domínio {}", ADAPTER_NAME);
        try{
            this.repository.delete(data);
            log.info("Dado removido: {}", data);
        }catch (Exception e){
            log.error("não foi possível remover o dado para o metodo delete() domínio {}", ADAPTER_NAME);
            throw new RuntimeException(); //TODO: Criar exception de falha ao deletar
        }
    }
}
