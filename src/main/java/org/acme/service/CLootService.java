package org.acme.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.http.loot.character.*;
import org.acme.mapper.ILootMapper;
import org.acme.model.Loot;
import org.acme.repository.CCharacterRepository;
import org.acme.repository.CLootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CLootService {
    private final CLootRepository lootRepository;
    private final CCharacterRepository characterRepository;
    private final Logger logger;

    public CLootService(CLootRepository lootRepository, CCharacterRepository characterRepository) {
        this.lootRepository = lootRepository;
        this.characterRepository = characterRepository;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @WithSession
    public Uni<GetAllCharacter.Output> getAllCharacter(GetAllCharacter.Input input) {
        return this.characterRepository.findCharacters(input.pageNum, input.pageSize)
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

    @WithSession
    public Uni<byte[]> getSplashartCard(String name){
        return this.lootRepository.findByName(name)
                .onItem().transform(Loot::getSplashartCard)
                .onFailure().recoverWithItem(e ->{
                    return null;
                });
    }

    @WithSession
    public Uni<byte[]> getSplashartBanner(String name){
        return this.lootRepository.findByName(name)
                .onItem().transform(Loot::getSplashartBanner)
                .onFailure().recoverWithItem(e ->{
                    return null;
                });
    }

    @WithTransaction
    public Uni<DeleteCharacter.Output> deleteCharacter(DeleteCharacter.Input input) {
        return this.characterRepository.deleteById(input.id)
                .onItem().transform(success -> {
                    DeleteCharacter.Output output = new DeleteCharacter.Output();
                    output.isSuccess = success;
                    output.message = success ? "Success" : "Failed";
                    return output;
                })
                .onFailure().recoverWithItem(e -> {
                    this.logger.error(e.getMessage());
                    DeleteCharacter.Output output = new DeleteCharacter.Output();
                    output.isSuccess = false;
                    output.message = e.getMessage();
                    return output;
                });
    }

    @WithTransaction
    public Uni<EditCharacter.Output> editCharacter(EditCharacter.Input input) {
        return this.characterRepository.findById((long) input.character.getId())
                .onItem().transformToUni(loot -> {
                    ILootMapper.INSTANCE.updateCharacterEntity(loot, input.character);

                    if(input.splashartCard != null && !input.splashartCard.isEmpty()) {
                        loot.setSplashartCard(input.splashartCard.getBytes());
                    }
                    if(input.splashartBanner != null && !input.splashartBanner.isEmpty()) {
                        loot.setSplashartBanner(input.splashartBanner.getBytes());
                    }

                    return this.lootRepository.persistAndFlush(loot)
                            .onItem().transform(character -> {
                                EditCharacter.Output output = new EditCharacter.Output();
                                if (character != null) {
                                    output.isSuccess = true;
                                    output.message = "Success";
                                } else {
                                    output.isSuccess = false;
                                    output.message = "Failed";
                                }
                                return output;
                            });
                })
                .onFailure().recoverWithItem(e -> {
                    this.logger.error(e.getMessage());
                    EditCharacter.Output output = new EditCharacter.Output();
                    output.isSuccess = false;
                    output.message = e.getMessage();
                    return output;
                });
    }

    @WithSession
    public Uni<CreateCharacter.Output> createCharacter(CreateCharacter.Input input) {
        return this.characterRepository.persistAndFlush(ILootMapper.INSTANCE.toCharacterEntity(input.character))
                .onItem().transform(loot ->{
                    CreateCharacter.Output output = new CreateCharacter.Output();
                    if(loot != null){
                        output.isSuccess = true;
                        output.message = "Success";
                    }else{
                        output.isSuccess = false;
                        output.message = "Failed";
                    }
                    return output;
                })
                .onFailure().recoverWithItem(
                        e -> {
                            this.logger.error(e.getMessage());
                            CreateCharacter.Output output = new CreateCharacter.Output();
                            output.isSuccess = false;
                            output.message = e.getMessage();
                            return output;
                        }
                );
    }

    @WithSession
    public Uni<RandomLoadingCharacters.Output> randomLoadingCharacters(RandomLoadingCharacters.Input input) {
        return this.characterRepository.randomCharacter()
                .onItem().transform(loots -> {
                    RandomLoadingCharacters.Output output = new RandomLoadingCharacters.Output();
                    output.isSuccess = true;
                    output.message = "Success";
                    output.characters = loots.stream().map(ILootMapper.INSTANCE::toDto).toList();
                    return output;
                })
                .onFailure().recoverWithItem(e -> {
                    RandomLoadingCharacters.Output output = new RandomLoadingCharacters.Output();
                    output.isSuccess = false;
                    output.message = e.getMessage();
                    return output;
                });
    }
}
