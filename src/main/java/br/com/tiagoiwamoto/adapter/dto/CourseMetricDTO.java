package br.com.tiagoiwamoto.adapter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CourseMetricDTO {

    private String school;
    private Double total;
}
