package org.acme.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Character;
import java.util.List;

@ApplicationScoped
public class CCharacterRepository implements PanacheRepository<Character> {
    public Uni<List<Character>> findCharacters(int page, int pageSize) {
        return findAll().page(page, pageSize).list();
    }

    public Uni<List<Character>> randomCharacter() {
        return find("ORDER BY RANDOM()").page(0, 25).list();
    }
}
