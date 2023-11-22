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

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ScholarityDTO implements Serializable {

    private static final long serialVersionUID = 7112740584947910252L;

    private Long id;
    @FormParam("uuid")
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @FormParam("schoolName")
    private String schoolName;
    @FormParam("courseName")
    private String courseName;
    @FormParam("titleReceivedCourse")
    private String titleReceivedCourse;
    @FormParam("duration")
    private Integer duration;
    @FormParam("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @FormParam("dateOfConclusion")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfConclusion;
    @FormParam("file")
    @PartType(MediaType.MULTIPART_FORM_DATA)
    private FileUpload file;
    private String pathOfImage;
    private String pathOfImageThumb;

}
