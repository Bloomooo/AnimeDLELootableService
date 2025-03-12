package org.acme.dto;

import java.util.HashMap;
import java.util.Map;

import org.acme.dto.generic.CMessageAPI;
import org.acme.dto.generic.IGenericDTO;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.http.banner.GetBanner;
import org.acme.dto.http.loot.character.*;
import org.acme.dto.http.media.CreateMedia;
import org.acme.dto.http.media.DeleteMedia;
import org.acme.dto.http.media.EditMedia;
import org.acme.dto.http.media.GetAllMedia;

@ApplicationScoped
public class CDTOGestionLootable implements IGenericDTO {

    private final Map<String, CMessageAPI> messageMap = new HashMap<>();

    public CDTOGestionLootable() {
        messageMap.put(GetAllCharacter.MSG_NAME, new CMessageAPI(GetAllCharacter.MSG_NAME, GetAllCharacter.Input.class));
        messageMap.put(DeleteCharacter.MSG_NAME, new CMessageAPI(DeleteCharacter.MSG_NAME, DeleteCharacter.Input.class));
        messageMap.put(EditCharacter.MSG_NAME, new CMessageAPI(EditCharacter.MSG_NAME, EditCharacter.Input.class));
        messageMap.put(CreateCharacter.MSG_NAME, new CMessageAPI(CreateCharacter.MSG_NAME, CreateCharacter.Input.class));
        messageMap.put(RandomLoadingCharacters.MSG_NAME, new CMessageAPI(RandomLoadingCharacters.MSG_NAME, RandomLoadingCharacters.Input.class));
        messageMap.put(GetAllMedia.MSG_NAME, new CMessageAPI(GetAllMedia.MSG_NAME, GetAllMedia.Input.class));
        messageMap.put(CreateMedia.MSG_NAME, new CMessageAPI(CreateMedia.MSG_NAME, CreateMedia.Input.class));
        messageMap.put(DeleteMedia.MSG_NAME, new CMessageAPI(DeleteMedia.MSG_NAME, DeleteMedia.Input.class));
        messageMap.put(EditMedia.MSG_NAME, new CMessageAPI(EditMedia.MSG_NAME, EditMedia.Input.class));
        messageMap.put(GetBanner.MSG_NAME, new CMessageAPI(GetBanner.MSG_NAME, GetBanner.Input.class));
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
        Uni<GetAllMedia.Output> getAllMedia(GetAllMedia.Input input);
        Uni<CreateMedia.Output> createMedia(CreateMedia.Input input);
        Uni<DeleteMedia.Output> deleteMedia(DeleteMedia.Input input);
        Uni<EditMedia.Output> editMedia(EditMedia.Input input);
        Uni<GetBanner.Output> getBanner(GetBanner.Input input);
    }
}
