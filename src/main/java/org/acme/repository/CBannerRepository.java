package org.acme.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Banner;

@ApplicationScoped
public class CBannerRepository implements PanacheRepository<Banner> {
    public Uni<Banner> findLastestBanner(){
        return Banner.find("select b from Banner b left join fetch b.loots order by b.dateCreated desc")
                .firstResult();
    }
}
