package org.acme.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Media;

@ApplicationScoped
public class CMediaRepository implements PanacheRepository<Media> {
}
