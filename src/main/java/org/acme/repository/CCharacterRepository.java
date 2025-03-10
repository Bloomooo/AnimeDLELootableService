package org.acme.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Character;
import java.util.List;

@ApplicationScoped
public class CCharacterRepository implements PanacheRepository<Character> {
    public Uni<List<Character>> findCharacters() {
        return findAll().page(0, 10).list();
    }
}
