package org.acme.handler;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.CDTOGestionLootable;
import org.acme.dto.http.GetAllCharacter;
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
        return this.lootService.getAllCharacter().onItem().transform(out -> out);
    }
}
