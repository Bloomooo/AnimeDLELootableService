package org.acme;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.CDTOGestionLootable;
import org.acme.dto.generic.IMessageAPI;
import org.acme.handler.CGenericGestionLootableHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("")
public class CGenericGestionLootableRessource {

    private final ObjectMapper objectMapper;
    private final CDTOGestionLootable dtoGestionLootable;
    private final CGenericGestionLootableHandler genericGestionUserHandler;
    private final Logger logger;

    public CGenericGestionLootableRessource(CDTOGestionLootable dtoGestionLootable,
                                            CGenericGestionLootableHandler genericGestionUserHandler) {
        this.objectMapper = new ObjectMapper();
        this.dtoGestionLootable = dtoGestionLootable;
        this.genericGestionUserHandler = genericGestionUserHandler;
        this.logger = LoggerFactory.getLogger(CGenericGestionLootableRessource.class);
    }

    @POST
    public Uni<Object> handleMessage(Object body) {
        try {
            JsonNode jsonNode = this.objectMapper.valueToTree(body);
            String typeMsg = jsonNode.get("typeMsg").asText();
            IMessageAPI messageAPI = this.dtoGestionLootable.getMessageAPI(typeMsg);

            return processMessage(typeMsg, jsonNode, messageAPI);

        } catch (Exception e) {
            this.logger.error("Erreur interne : " + e.getMessage(), e);
            return Uni.createFrom().item("Erreur interne : " + e.getMessage());
        }
    }

    private Uni<Object> processMessage(String typeMsg, JsonNode jsonNode, IMessageAPI messageAPI) {
        try {
            Object input = this.objectMapper.treeToValue(jsonNode.get("input"), messageAPI.getTypeInput());
            Method method = this.genericGestionUserHandler.getClass().getMethod(typeMsg, messageAPI.getTypeInput());
            Uni<?> outputUni = (Uni<?>) method.invoke(this.genericGestionUserHandler, input);

            return outputUni.onItem().transform(output -> {
                messageAPI.setOutput(output);
                return messageAPI.getOutput();
            });
        } catch (JsonProcessingException e) {
            this.logger.error("Erreur de conversion JSON : " + e.getMessage(), e);
            return Uni.createFrom().item("Erreur de conversion JSON");
        } catch (NoSuchMethodException e) {
            this.logger.error("Méthode inconnue : " + e.getMessage(), e);
            return Uni.createFrom().item("Méthode inconnue : " + e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            this.logger.error("Erreur d'invocation de méthode : " + e.getMessage(), e);
            return Uni.createFrom().item("Erreur d'invocation de méthode");
        }
    }

    @GET
    @Path("/card/{name}")
    public Uni<Response> getSplashartCard(@PathParam("name") String name) {
        return this.genericGestionUserHandler.getSplashartCard(name)
                .onItem().transform(card -> {
                    return Response.ok(card)
                            .type("image/jpeg")
                            .build();
                });
    }

    @GET
    @Path("/banner/{name}")
    public Uni<Response> getSplashartBanner(@PathParam("name") String name){
        return this.genericGestionUserHandler.getSplashartBanner(name)
                .onItem().transform(card -> {
                    return Response.ok(card)
                            .type("image/jpeg")
                            .build();
                });
    }
}
