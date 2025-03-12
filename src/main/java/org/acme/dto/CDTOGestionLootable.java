package org.acme.dto;

import java.util.HashMap;
import java.util.Map;

import org.acme.dto.generic.CMessageAPI;
import org.acme.dto.generic.IGenericDTO;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.http.*;

@ApplicationScoped
public class CDTOGestionLootable implements IGenericDTO {

    private final Map<String, CMessageAPI> messageMap = new HashMap<>();

    public CDTOGestionLootable() {
        messageMap.put(GetAllCharacter.MSG_NAME, new CMessageAPI(GetAllCharacter.MSG_NAME, GetAllCharacter.Input.class));
        messageMap.put(DeleteCharacter.MSG_NAME, new CMessageAPI(DeleteCharacter.MSG_NAME, DeleteCharacter.Input.class));
        messageMap.put(EditCharacter.MSG_NAME, new CMessageAPI(EditCharacter.MSG_NAME, EditCharacter.Input.class));
        messageMap.put(CreateCharacter.MSG_NAME, new CMessageAPI(CreateCharacter.MSG_NAME, CreateCharacter.Input.class));
        messageMap.put(RandomLoadingCharacters.MSG_NAME, new CMessageAPI(RandomLoadingCharacters.MSG_NAME, RandomLoadingCharacters.Input.class));
    }

    @Override
    public String getInterfaceName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public CMessageAPI getMessageAPI(String msgName) {
        return messageMap.get(msgName);
    }

    public interface IHandlerDTOGestionUser{
        Uni<GetAllCharacter.Output> getAllCharacter(GetAllCharacter.Input input);
        Uni<DeleteCharacter.Output> deleteCharacter(DeleteCharacter.Input input);
        Uni<EditCharacter.Output> editCharacter(EditCharacter.Input input);
        Uni<CreateCharacter.Output> createCharacter(CreateCharacter.Input input);
        Uni<RandomLoadingCharacters.Output> randomLoadingCharacters(RandomLoadingCharacters.Input input);
    }
}
