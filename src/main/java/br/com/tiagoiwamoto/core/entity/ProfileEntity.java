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
@Table(name = "tbl_profiles")
@RequiredArgsConstructor
@Getter
@Setter
public class ProfileEntity implements Serializable {
    private static final long serialVersionUID = -9017681530802336858L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private String name;
    private String title;
    @Column(name = "sub_title")
    private String subTitle;
    private String email;
    private String phone;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(length = 9999)
    private String description;
}
