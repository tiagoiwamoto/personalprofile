package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.dto.ProfileDTO;
import br.com.tiagoiwamoto.adapter.out.ProfileAdapter;
import br.com.tiagoiwamoto.core.mapper.ProfileMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class ProfileUsecase {

    @Inject
    private ProfileAdapter adapter;
    @Inject
    private ProfileMapper mapper;
    private final String DOMINIO = "profile";

    public List<ProfileDTO> listarRegistros(){
        var dados = this.adapter.all();
        log.info("iniciando conversão para DTO, metodo listarRegistros() domínio {}", DOMINIO);
        var listaDeDtos = dados.stream().map(registro -> this.mapper.toDto(registro)).toList();
        log.info("conversão para DTO realizada com sucesso {}", listaDeDtos);
        return listaDeDtos;
    }

    public ProfileDTO gravarRegistro(ProfileDTO dados){

        var uuid = UUID.randomUUID();
        var timestamp = LocalDateTime.now();
        dados.setUuid(uuid);
        dados.setCreatedAt(timestamp);
        dados.setUpdatedAt(timestamp);

        log.info("iniciando conversão para ENTITY, metodo gravarRegistro() domínio {}", DOMINIO);
        var registro = this.mapper.toEntity(dados);
        log.info("conversão para ENTITY realizada com sucesso {}", registro);

        log.info("iniciando chamada ao adapter, domínio {}", DOMINIO);
        registro = this.adapter.save(registro);
        log.info("registro realizado com sucesso {}", registro);

        log.info("iniciando conversão para DTO, metodo gravarRegistro() domínio {}", DOMINIO);
        var dto = this.mapper.toDto(registro);
        log.info("conversão para DTO realizada com sucesso {}", dto);
        return dto;
    }

}
