package org.acme.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Banner;

@ApplicationScoped
public class CBannerRepository implements PanacheRepository<Banner> {
}
