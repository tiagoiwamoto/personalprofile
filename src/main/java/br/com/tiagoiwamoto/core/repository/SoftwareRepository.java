package br.com.tiagoiwamoto.core.repository;

import br.com.tiagoiwamoto.core.entity.SoftwareEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SoftwareRepository implements PanacheRepository<SoftwareEntity> {
}
