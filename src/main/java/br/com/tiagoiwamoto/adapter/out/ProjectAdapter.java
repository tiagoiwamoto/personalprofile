package br.com.tiagoiwamoto.adapter.out;

import br.com.tiagoiwamoto.core.entity.ProjectEntity;
import br.com.tiagoiwamoto.core.port.ProjectPort;
import br.com.tiagoiwamoto.core.repository.ProjectRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class ProjectAdapter implements ProjectPort, Serializable {

    @Inject
    private ProjectRepository repository;
    private final String ADAPTER_NAME = "project";

    private static final long serialVersionUID = -3888769632747048682L;

    @Override
    public List<ProjectEntity> all() {
        log.info("recuperando dados para o metodo all() domínio {}", ADAPTER_NAME);
        var records = this.repository.findAll().list();
        log.info("Dados recuperados: {}", records.size());
        return records;
    }

    @Override
    public ProjectEntity recoveryByUuid(UUID uuid) {
        log.info("recuperando dados para o metodo byUuid() domínio {}", ADAPTER_NAME);
        var record = this.repository.find("uuid", uuid).firstResult();
        log.info("Dado recuperado: {}", record);
        return record;
    }

    @Override
    @Transactional
    public ProjectEntity save(ProjectEntity data) {
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
    @Transactional
    public ProjectEntity update(ProjectEntity data) {
        log.info("inciando atualização para o metodo update() domínio {}", ADAPTER_NAME);
        try{
            var registro = this.recoveryByUuid(data.getUuid());
            registro.setName(data.getName());
            registro.setUrl(data.getUrl());
            registro.setDescription(data.getDescription());
            registro.setUpdatedAt(data.getUpdatedAt());
            this.repository.persist(registro);
            log.info("Dado atualizado: {}", registro);
            return registro;
        }catch (Exception e){
            log.error("não foi possível atualizar o dado para o metodo save() domínio {}", ADAPTER_NAME);
            throw new RuntimeException(); //TODO: Criar exception de falha ao atualizar
        }
    }

    @Override
    @Transactional
    public void delete(ProjectEntity data) {
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
