package br.com.tiagoiwamoto.adapter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ExperienceDTO implements Serializable {

    private static final long serialVersionUID = 426825892622467560L;

    private Long id;
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String job;
    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
