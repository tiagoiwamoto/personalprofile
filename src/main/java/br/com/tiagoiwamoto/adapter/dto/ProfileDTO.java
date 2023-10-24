package br.com.tiagoiwamoto.adapter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Data
public class ProfileDTO implements Serializable {

    private static final long serialVersionUID = 2806800246322609997L;

    private UUID uuid;
    private String name;
    private String title;
    private String subTitle;
    private String email;
    private String phone;
    private Boolean isActive;
    private String description;
}
