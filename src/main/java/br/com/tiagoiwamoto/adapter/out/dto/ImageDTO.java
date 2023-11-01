package br.com.tiagoiwamoto.adapter.out.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO implements Serializable {

    private static final long serialVersionUID = -8040570410818453667L;
    private String pathOfImage;
    private String pathOfThumb;
}
