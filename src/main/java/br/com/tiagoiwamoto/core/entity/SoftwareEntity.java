package br.com.tiagoiwamoto.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_softwares")
@RequiredArgsConstructor
@Getter
@Setter
public class SoftwareEntity implements Serializable {

    private static final long serialVersionUID = -3322829350606967975L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private String name;
    @Column(length = 9999)
    private String description;
    private String url;
    @Column(name = "url_mirror")
    private String urlMirror;

}
