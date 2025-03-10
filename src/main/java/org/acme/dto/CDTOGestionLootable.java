package org.acme.dto;

import java.util.HashMap;
import java.util.Map;

import org.acme.dto.generic.CMessageAPI;
import org.acme.dto.generic.IGenericDTO;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.http.GetAllCharacter;

@ApplicationScoped
public class CDTOGestionLootable implements IGenericDTO {

    private final Map<String, CMessageAPI> messageMap = new HashMap<>();

    public CDTOGestionLootable() {
        messageMap.put(GetAllCharacter.MSG_NAME, new CMessageAPI(GetAllCharacter.MSG_NAME, GetAllCharacter.Input.class));
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
    }
}
