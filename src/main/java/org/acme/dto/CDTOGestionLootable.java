package org.acme.dto;

import java.util.HashMap;
import java.util.Map;

import org.acme.dto.generic.CMessageAPI;
import org.acme.dto.generic.IGenericDTO;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CDTOGestionLootable implements IGenericDTO {

    private final Map<String, CMessageAPI> messageMap = new HashMap<>();

    public CDTOGestionLootable() {
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

    }
}
