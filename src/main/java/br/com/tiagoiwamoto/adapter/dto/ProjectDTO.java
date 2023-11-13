package br.com.tiagoiwamoto.adapter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProjectDTO implements Serializable {

    private static final long serialVersionUID = -6731134143689559604L;

    private Long id;
    private UUID uuid;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private String description;
    private String url;
}
