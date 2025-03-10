package org.acme.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Loot;

import java.util.List;

@ApplicationScoped
public class CLootRepository implements PanacheRepository<Loot> {
    public Uni<List<Loot>> findByType(Class<?> type) {
        return find("dtype = ?1", type.getSimpleName()).page(0, 10).list();
    }

    public Uni<Loot> findByName(String name) {
        return find("name = ?1", name).firstResult();
    }
}
