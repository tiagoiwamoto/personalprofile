package br.com.tiagoiwamoto.adapter.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Data
public class CertificationDTO {

    private Long id;
    @FormParam("uuid")
    private UUID uuid;
    @FormParam("name")
    private String name;
    @FormParam("earnDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @NotBlank(message = "Informe a data que recebeu este certificado")
    private LocalDate earnDate;
    @FormParam("validateUrl")
//    @NotBlank
    private String validateUrl;
    private String pathOfImage;
    private String pathOfImageThumb;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @FormParam("file")
    @PartType(MediaType.MULTIPART_FORM_DATA)
    private FileUpload file;

}
