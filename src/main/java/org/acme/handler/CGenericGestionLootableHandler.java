package org.acme.handler;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.CDTOGestionLootable;
import org.acme.dto.http.loot.character.*;
import org.acme.dto.http.media.CreateMedia;
import org.acme.dto.http.media.DeleteMedia;
import org.acme.dto.http.media.EditMedia;
import org.acme.dto.http.media.GetAllMedia;
import org.acme.service.CBannerService;
import org.acme.service.CLootService;
import org.acme.service.CMediaService;

@ApplicationScoped
public class CGenericGestionLootableHandler implements CDTOGestionLootable.IHandlerDTOGestionUser {
    private final CLootService lootService;
    private final CMediaService mediaService;
    private final CBannerService bannerService;

    public CGenericGestionLootableHandler(CLootService lootService, CMediaService mediaService, CBannerService bannerService) {
        this.lootService = lootService;
        this.mediaService = mediaService;
        this.bannerService = bannerService;
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

    /**
     * @param input 
     * @return
     */
    @Override
    public Uni<GetAllMedia.Output> getAllMedia(GetAllMedia.Input input) {
        return this.mediaService.getAllMedia(input).onItem().transform(out -> out);
    }

    /**
     * @param input 
     * @return
     */
    @Override
    public Uni<CreateMedia.Output> createMedia(CreateMedia.Input input) {
        return this.mediaService.createMedia(input).onItem().transform(out -> out);
    }

    /**
     * @param input 
     * @return
     */
    @Override
    public Uni<DeleteMedia.Output> deleteMedia(DeleteMedia.Input input) {
        return this.mediaService.deleteMedia(input).onItem().transform(out -> out);
    }

    /**
     * @param input 
     * @return
     */
    @Override
    public Uni<EditMedia.Output> editMedia(EditMedia.Input input) {
        return this.mediaService.editMedia(input).onItem().transform(out -> out);
    }

    public Uni<byte[]> getSplashartCard(String name){
        return this.lootService.getSplashartCard(name).onItem().transform(out -> out);
    }

    public Uni<byte[]> getSplashartBanner(String name){
        return this.lootService.getSplashartBanner(name).onItem().transform(out -> out);
    }
}
