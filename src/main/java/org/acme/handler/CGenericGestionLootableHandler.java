package org.acme.handler;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.CDTOGestionLootable;
import org.acme.dto.http.*;
import org.acme.service.CLootService;

@ApplicationScoped
public class CGenericGestionLootableHandler implements CDTOGestionLootable.IHandlerDTOGestionUser {
    private final CLootService lootService;

    public CGenericGestionLootableHandler(CLootService lootService) {
        this.lootService = lootService;
    }

    /**
     * @param input 
     * @return
     */
    @Override
    public Uni<GetAllCharacter.Output> getAllCharacter(GetAllCharacter.Input input) {
        return this.lootService.getAllCharacter(input).onItem().transform(out -> out);
    }

    /**
     * @param input 
     * @return
     */
    @Override
    public Uni<DeleteCharacter.Output> deleteCharacter(DeleteCharacter.Input input) {
        return this.lootService.deleteCharacter(input).onItem().transform(out -> out);
    }

    /**
     * @param input 
     * @return
     */
    @Override
    public Uni<EditCharacter.Output> editCharacter(EditCharacter.Input input) {
        return this.lootService.editCharacter(input).onItem().transform(out -> out);
    }

    /**
     * @param input 
     * @return
     */
    @Override
    public Uni<CreateCharacter.Output> createCharacter(CreateCharacter.Input input) {
        return this.lootService.createCharacter(input).onItem().transform(out -> out);
    }

    /**
     * @param input 
     * @return
     */
    @Override
    public Uni<RandomLoadingCharacters.Output> randomLoadingCharacters(RandomLoadingCharacters.Input input) {
        return this.lootService.randomLoadingCharacters(input).onItem().transform(out -> out);
    }

    public Uni<byte[]> getSplashartCard(String name){
        return this.lootService.getSplashartCard(name).onItem().transform(out -> out);
    }

    public Uni<byte[]> getSplashartBanner(String name){
        return this.lootService.getSplashartBanner(name).onItem().transform(out -> out);
    }
}
