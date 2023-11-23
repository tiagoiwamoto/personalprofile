package br.com.tiagoiwamoto.core.usecase;

import br.com.tiagoiwamoto.adapter.dto.CourseDTO;
import br.com.tiagoiwamoto.adapter.dto.CourseMetricDTO;
import br.com.tiagoiwamoto.adapter.out.CourseAdapter;
import br.com.tiagoiwamoto.adapter.out.CourseCategoryAdapter;
import br.com.tiagoiwamoto.adapter.out.ImageAndThumbAdapter;
import br.com.tiagoiwamoto.adapter.out.dto.ImageDTO;
import br.com.tiagoiwamoto.core.mapper.CourseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Slf4j
public class CourseUsecase {

    @Inject
    private CourseAdapter adapter;
    @Inject
    private CourseCategoryAdapter courseCategoryAdapter;
    @Inject
    private ImageAndThumbAdapter image;
    @Inject
    private CourseMapper mapper;
    private final String DOMINIO = "courses";
    private final String PATH = "files/courses/";

    public List<CourseDTO> listarRegistros(){
        var dados = this.adapter.all();
        log.info("iniciando conversão para DTO, metodo listarRegistros() domínio {}", DOMINIO);
        var listaDeDtos = dados.stream().map(registro -> this.mapper.toDto(registro)).toList();
        log.info("conversão para DTO realizada com sucesso {}", listaDeDtos);
        return listaDeDtos;
    }

    public List<CourseDTO> listarRegistrosPorCategoria(UUID uuid){
        var category = this.courseCategoryAdapter.recoveryByUuid(uuid);
        if(Optional.of(category).isPresent()){
            var dados = this.adapter.allByCategory(category);
            log.info("iniciando conversão para DTO, metodo listarRegistros() domínio {}", DOMINIO);
            var listaDeDtos = dados.stream().map(registro -> this.mapper.toDto(registro)).toList();
            log.info("conversão para DTO realizada com sucesso {}", listaDeDtos);
            return listaDeDtos;
        }
        throw new RuntimeException("Categoria não foi localizada");
    }

    public CourseDTO gravarRegistro(CourseDTO dados) {
        log.info("iniciando usecase de courses createOrUpdate, {}", DOMINIO);
        var timestamp = LocalDateTime.now();
        log.info("certification gravarRegistro -> será criado um novo registro, {}", DOMINIO);
        UUID courseUuid = UUID.randomUUID();
        var path = Paths.get(PATH
                .concat(dados.getCourseCategoryUuid().toString())
                .concat("/")
                .concat(courseUuid.toString()));
        var imageDto = this.image.storeImage(dados.getFile(), path);
        dados.setUuid(courseUuid);
        var category = this.courseCategoryAdapter.recoveryByUuid(dados.getCourseCategoryUuid());

        dados.setPathOfImage(imageDto.getPathOfImage());
        dados.setPathOfImageThumb(imageDto.getPathOfThumb());
        log.info("Convertendo dto para domain, {}", DOMINIO);
        var domain = this.mapper.toEntity(dados);
        domain.setCourseCategory(category);
        domain.setCreatedAt(timestamp);
        domain.setUpdatedAt(timestamp);
        var response = this.adapter.save(domain);
        var certificationDtoConverted = this.mapper.toDto(response);
        log.info("Convertendo domain para dto, {}", DOMINIO);
        return certificationDtoConverted;

    }

    public CourseDTO atualizarRegistro(CourseDTO dados){

        var timestamp = LocalDateTime.now();

        var optionalRegistro = Optional.of(this.adapter.recoveryByUuid(dados.getUuid()));

        if(optionalRegistro.isPresent()){
            log.info("course atualizarRegistro -> será atualizado o registro, {}", DOMINIO);
            var domain = optionalRegistro.get();
            var path = Paths.get(PATH
                    .concat(dados.getCourseCategoryUuid().toString())
                    .concat("/")
                    .concat(domain.getUuid().toString()));
            dados.setCreatedAt(domain.getCreatedAt());
            dados.setUpdatedAt(timestamp);
            var oldImageDto = new ImageDTO(domain.getPathOfImage(), domain.getPathOfImageThumb());
            var imageDto = this.image.validUpdateOfImage(path, dados.getFile(), oldImageDto);

            var category = this.courseCategoryAdapter.recoveryByUuid(dados.getCourseCategoryUuid());

            dados.setPathOfImage(imageDto.getPathOfImage());
            dados.setPathOfImageThumb(imageDto.getPathOfThumb());
            log.info("Convertendo dto para domain, {}", DOMINIO);
            domain = this.mapper.toEntity(dados);
            domain.setCourseCategory(category);
            var response = this.adapter.update(domain);
            var dto = this.mapper.toDto(response);
            log.info("Convertendo domain para dto, {}", DOMINIO);
            return dto;
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

    public List<CourseDTO> top10(){
        var dados = this.adapter.top10();
        log.info("iniciando conversão para DTO, metodo listarRegistros() domínio {}", DOMINIO);
        var listaDeDtos = dados.stream().map(registro -> this.mapper.toDto(registro)).toList();
        log.info("conversão para DTO realizada com sucesso {}", listaDeDtos);
        return listaDeDtos;
    }

    public List<CourseMetricDTO> getTotalOfCoursesByCategory(){
        List<CourseMetricDTO> metricas = new ArrayList<>();
        var dados = this.adapter.getTotalOfCoursesByCategory();
        log.info("iniciando conversão para DTO, metodo listarRegistros() domínio {}", DOMINIO);
        dados.forEach(dado -> {
            var dadoParaArray = (Object[]) dado;
            var arrayParaLista = Arrays.asList(dadoParaArray);
            var school = arrayParaLista.get(0);
            var tempo = String.valueOf(arrayParaLista.get(1));
            metricas.add(new CourseMetricDTO(String.valueOf(school), Double.valueOf(tempo)));
        });
        log.info("conversão para DTO realizada com sucesso {}", metricas);
        return metricas;
    }

}
