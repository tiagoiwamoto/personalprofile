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
public class CourseDTO implements Serializable {

    private Long id;
    @FormParam("uuid")
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @FormParam("name")
    private String name;
    @FormParam("title")
    private String title;
    @FormParam("school")
    private String school;
    @FormParam("duration")
    private Double duration;
    @FormParam("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @FormParam("endDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @FormParam("courseCategory")
    private UUID courseCategoryUuid;
    @FormParam("file")
    @PartType(MediaType.MULTIPART_FORM_DATA)
    private FileUpload file;

    private String pathOfImage;
    private String pathOfImageThumb;
}
