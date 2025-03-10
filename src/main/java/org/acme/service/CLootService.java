package org.acme.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.http.GetAllCharacter;
import org.acme.mapper.ILootMapper;
import org.acme.repository.CCharacterRepository;
import org.acme.repository.CLootRepository;

@ApplicationScoped
public class CLootService {
    private final CLootRepository lootRepository;
    private final CCharacterRepository characterRepository;

    public CLootService(CLootRepository lootRepository, CCharacterRepository characterRepository) {
        this.lootRepository = lootRepository;
        this.characterRepository = characterRepository;
    }

    @WithSession
    public Uni<GetAllCharacter.Output> getAllCharacter() {
        return this.characterRepository.findCharacters()
                .onItem().transform(character -> {
                    GetAllCharacter.Output output = new GetAllCharacter.Output();
                    output.isSuccess = true;
                    output.message = "Success";
                    output.characters = character.stream().map(ILootMapper.INSTANCE::toDto).toList();
                    return output;
                })
                .onFailure().recoverWithItem( e -> {
                    GetAllCharacter.Output output = new GetAllCharacter.Output();
                    output.isSuccess = false;
                    output.message = e.getMessage();
                    return output;
                });
    }
}
