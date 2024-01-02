package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.dto.CourseCategoryDTO;
import br.com.tiagoiwamoto.adapter.out.CourseCategoryAdapter;
import br.com.tiagoiwamoto.core.mapper.CourseCategoryMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class CourseCategoryUsecase implements Serializable {

    private static final long serialVersionUID = 3535780205141440644L;
    @Inject
    private CourseCategoryAdapter adapter;
    @Inject
    private CourseCategoryMapper mapper;
    private final String DOMINIO = "courseCategory";

    public List<CourseCategoryDTO> listarRegistros(){
        var dados = this.adapter.all();

        log.info("iniciando conversão para DTO, metodo listarRegistros() domínio {}", DOMINIO);
        var listaDeDtos = dados.stream().map(registro -> this.mapper.toDto(registro)).toList();
        log.info("conversão para DTO realizada com sucesso {}", listaDeDtos);
        return listaDeDtos;
    }

    public CourseCategoryDTO listarRegistroPorUuid(UUID uuid){
        var dados = this.adapter.recoveryByUuid(uuid);
        log.info("iniciando conversão para DTO, metodo listarRegistros() domínio {}", DOMINIO);
        var registro = this.mapper.toDto(dados);
        log.info("conversão para DTO realizada com sucesso {}", registro);
        return registro;
    }

    public CourseCategoryDTO gravarRegistro(CourseCategoryDTO dados){

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

    public CourseCategoryDTO atualizarRegistro(CourseCategoryDTO dados){

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

    public void deletarRegistro(UUID uuid){

        var optionalRegistro = Optional.of(this.adapter.recoveryByUuid(uuid));

        if(optionalRegistro.isPresent()){
            var registroExistente = optionalRegistro.get();

            log.info("iniciando chamada ao adapter, domínio {}", DOMINIO);
            this.adapter.delete(registroExistente);
            log.info("registro removido com sucesso {}", registroExistente);
        }else{
            throw new RuntimeException();
        }
    }

}
