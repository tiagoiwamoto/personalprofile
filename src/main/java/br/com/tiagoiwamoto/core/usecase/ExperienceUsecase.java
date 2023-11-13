package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.dto.ExperienceDTO;
import br.com.tiagoiwamoto.adapter.out.ExperienceAdapter;
import br.com.tiagoiwamoto.core.mapper.ExperienceMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class ExperienceUsecase {

    @Inject
    private ExperienceAdapter adapter;
    @Inject
    private ExperienceMapper mapper;
    private final String DOMINIO = "experience";

    public List<ExperienceDTO> listarRegistros(){
        var dados = this.adapter.all();
        log.info("iniciando conversão para DTO, metodo listarRegistros() domínio {}", DOMINIO);
        var listaDeDtos = dados.stream().map(registro -> this.mapper.toDto(registro)).toList();
        log.info("conversão para DTO realizada com sucesso {}", listaDeDtos);
        return listaDeDtos;
    }

    public ExperienceDTO gravarRegistro(ExperienceDTO dados){

        var uuid = UUID.randomUUID();
        var timestamp = LocalDateTime.now();
        dados.setUuid(uuid);


        log.info("iniciando conversão para ENTITY, metodo gravarRegistro() domínio {}", DOMINIO);
        var registro = this.mapper.toEntity(dados);
        registro.setCreatedAt(timestamp);
        registro.setUpdatedAt(timestamp);
        log.info("conversão para ENTITY realizada com sucesso {}", registro);

        log.info("iniciando chamada ao adapter, domínio {}", DOMINIO);
        registro = this.adapter.save(registro);
        log.info("registro realizado com sucesso {}", registro);

        log.info("iniciando conversão para DTO, metodo gravarRegistro() domínio {}", DOMINIO);
        var dto = this.mapper.toDto(registro);
        log.info("conversão para DTO realizada com sucesso {}", dto);
        return dto;
    }

    public ExperienceDTO atualizarRegistro(ExperienceDTO dados){

        var timestamp = LocalDateTime.now();

        var optionalRegistro = Optional.of(this.adapter.recoveryByUuid(dados.getUuid()));

        if(optionalRegistro.isPresent()){
            var registroExistente = optionalRegistro.get();
            //TODO: validar uuid e id entre dto e entity

            log.info("iniciando conversão para ENTITY, metodo atualizarRegistro() domínio {}", DOMINIO);
            var registro = this.mapper.toEntity(dados);
            registro.setUpdatedAt(timestamp);
            registro.setCreatedAt(registroExistente.getCreatedAt());
            log.info("conversão para ENTITY, metodo atualizarRegistro() realizada com sucesso {}", registro);

            log.info("iniciando chamada ao adapter, domínio {}", DOMINIO);
            registro = this.adapter.update(registro);
            log.info("registro atualizado com sucesso {}", registro);

            log.info("iniciando conversão para DTO, metodo atualizarRegistro() domínio {}", DOMINIO);
            var dto = this.mapper.toDto(registro);
            log.info("conversão para DTO realizada com sucesso {}", dto);
            return dto;
        }
        throw new RuntimeException();
    }

    public void deletarRegistro(ExperienceDTO dados){

        var optionalRegistro = Optional.of(this.adapter.recoveryByUuid(dados.getUuid()));

        if(optionalRegistro.isPresent()){
            var registroExistente = optionalRegistro.get();
            log.info("iniciando conversão para ENTITY, metodo deletarRegistro() domínio {}", DOMINIO);
            var registro = this.mapper.toEntity(dados);
            log.info("conversão para ENTITY realizada com sucesso {}", registro);

            log.info("iniciando chamada ao adapter, domínio {}", DOMINIO);
            this.adapter.delete(registroExistente);
            log.info("registro removido com sucesso {}", registro);
        }else{
            throw new RuntimeException();
        }
    }

}
