package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.dto.ScholarityDTO;
import br.com.tiagoiwamoto.adapter.out.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.adapter.out.ScholarityAdapter;
import br.com.tiagoiwamoto.adapter.out.dto.ImageDTO;
import br.com.tiagoiwamoto.core.mapper.ScholarityMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class ScholarityUsecase {

    @Inject
    private ScholarityAdapter adapter;
    @Inject
    private ImageAndThumbAdapter image;
    @Inject
    private ScholarityMapper mapper;
    private final String DOMINIO = "scholarity";
    private final String PATH = "files/scholarities/";

    public List<ScholarityDTO> listarRegistros(){
        var dados = this.adapter.all();
        log.info("iniciando conversão para DTO, metodo listarRegistros() domínio {}", DOMINIO);
        var listaDeDtos = dados.stream().map(registro -> this.mapper.toDto(registro)).toList();
        log.info("conversão para DTO realizada com sucesso {}", listaDeDtos);
        return listaDeDtos;
    }

    public ScholarityDTO gravarRegistro(ScholarityDTO dados) {
        log.info("iniciando usecase de certification createOrUpdate");
        var timestamp = LocalDateTime.now();
        log.info("certification createOrUpdate -> será criado um novo registro");
        UUID certificationUuid = UUID.randomUUID();
        var path = Paths.get(PATH.concat(certificationUuid.toString()));
        var imageDto = this.image.storeImage(dados.getFile(), path);
        dados.setUuid(certificationUuid);


        dados.setPathOfImage(imageDto.getPathOfImage());
        dados.setPathOfImageThumb(imageDto.getPathOfThumb());
        log.info("Convertendo certificationDto para certificationDomain");
        var domain = this.mapper.toEntity(dados);
        domain.setCreatedAt(timestamp);
        domain.setUpdatedAt(timestamp);
        var response = this.adapter.save(domain);
        var certificationDtoConverted = this.mapper.toDto(response);
        log.info("Convertendo certificationDomain para certificationDto");
        return certificationDtoConverted;

    }

    public ScholarityDTO atualizarRegistro(ScholarityDTO dados){

        var timestamp = LocalDateTime.now();

        var optionalRegistro = Optional.of(this.adapter.recoveryByUuid(dados.getUuid()));

        if(optionalRegistro.isPresent()){
            log.info("certification createOrUpdate -> será atualizado o registro");
            var domain = optionalRegistro.get();
            var path = Paths.get(PATH.concat(domain.getUuid().toString()));
            dados.setCreatedAt(domain.getCreatedAt());
            dados.setUpdatedAt(timestamp);
            var oldImageDto = new ImageDTO(domain.getPathOfImage(), domain.getPathOfImageThumb());
            var imageDto = this.image.validUpdateOfImage(path, dados.getFile(), oldImageDto);

            dados.setPathOfImage(imageDto.getPathOfImage());
            dados.setPathOfImageThumb(imageDto.getPathOfThumb());
            log.info("Convertendo certificationDto para certificationDomain");
            domain = this.mapper.toEntity(dados);
            var response = this.adapter.update(domain);
            var certificationDtoConverted = this.mapper.toDto(response);
            log.info("Convertendo certificationDomain para certificationDto");
            return certificationDtoConverted;
        }
        throw new RuntimeException();
    }

    public void deletarRegistro(UUID uuid){

        var optionalRegistro = Optional.of(this.adapter.recoveryByUuid(uuid));

        if(optionalRegistro.isPresent()){
            var registroExistente = optionalRegistro.get();
            log.info("registro localizado com sucesso {}", registroExistente);

            log.info("iniciando chamada ao adapter, domínio {}", DOMINIO);
            this.adapter.delete(registroExistente);
            log.info("registro removido com sucesso {}", registroExistente);
            var path = Paths.get(PATH.concat(registroExistente.getUuid().toString()));
            this.image.removeFiles(path);
            log.info("imagens para o registro removido com sucesso {}", registroExistente);
        }else{
            throw new RuntimeException();
        }


    }

}
