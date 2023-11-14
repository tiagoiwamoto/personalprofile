package br.com.tiagoiwamoto.core.repository;

import br.com.tiagoiwamoto.core.entity.ResumeEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResumeRepository implements PanacheRepository<ResumeEntity> {
}
