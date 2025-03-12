package org.acme.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.dto.http.media.CreateMedia;
import org.acme.dto.http.media.DeleteMedia;
import org.acme.dto.http.media.EditMedia;
import org.acme.dto.http.media.GetAllMedia;
import org.acme.mapper.IMediaMapper;
import org.acme.repository.CMediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CMediaService {
    private final CMediaRepository mediaRepository;
    private final Logger logger = LoggerFactory.getLogger(CMediaService.class);

    public CMediaService(CMediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @WithSession
    public Uni<GetAllMedia.Output> getAllMedia(GetAllMedia.Input input) {
        return this.mediaRepository.findAll().page(input.pageNum, input.pageSize).list()
                .onItem().transform(medias ->{
                    GetAllMedia.Output output = new GetAllMedia.Output();
                    output.isSuccess = true;
                    output.message = "Success";
                    output.media = medias.stream().map(IMediaMapper.INSTANCE::toDto).toList();
                    return output;
                })
                .onFailure().recoverWithItem(e ->{
                    this.logger.error(e.getMessage());
                    GetAllMedia.Output output = new GetAllMedia.Output();
                    output.isSuccess = false;
                    output.message = e.getMessage();
                    return output;
                });
    }

    @WithTransaction
    public Uni<CreateMedia.Output> createMedia(CreateMedia.Input input) {
        return this.mediaRepository.persistAndFlush(IMediaMapper.INSTANCE.toEntity(input.media))
                .onItem().transform(media ->{
                    CreateMedia.Output output = new CreateMedia.Output();
                    if(media != null){
                        output.isSuccess = true;
                        output.message = "Success";
                    }else{
                        output.isSuccess = false;
                        output.message = "Failed";
                    }
                    return output;
                })
                .onFailure().recoverWithItem(e ->{
                    this.logger.error(e.getMessage());
                    CreateMedia.Output output = new CreateMedia.Output();
                    output.isSuccess = false;
                    output.message = e.getMessage();
                    return output;
                });
    }

    @WithTransaction
    public Uni<DeleteMedia.Output> deleteMedia(DeleteMedia.Input input) {
        return this.mediaRepository.deleteById(input.id)
                .onItem().transform(success ->{
                    DeleteMedia.Output output = new DeleteMedia.Output();
                    output.isSuccess = success;
                    output.message = success ? "Success" : "Failed";
                    return output;
                })
                .onFailure().recoverWithItem(e ->{
                    this.logger.error(e.getMessage());
                    DeleteMedia.Output output = new DeleteMedia.Output();
                    output.isSuccess = false;
                    output.message = e.getMessage();
                    return output;
                });
    }

    @WithTransaction
    public Uni<EditMedia.Output> editMedia(EditMedia.Input input) {
        return this.mediaRepository.findById(input.media.getId())
                .onItem().transformToUni(media ->{
                    IMediaMapper.INSTANCE.updateEntity(media, input.media);

                    if(input.logo != null && !input.logo.isEmpty()){
                        media.setImg(input.logo);
                    }

                    return this.mediaRepository.persistAndFlush(media)
                            .onItem().transform(m -> {
                                EditMedia.Output output = new EditMedia.Output();
                                if(m != null){
                                    output.isSuccess = true;
                                    output.message = "Success";
                                }else{
                                    output.isSuccess = false;
                                    output.message = "Failed";
                                }
                                return output;
                            });
                })
                .onFailure().recoverWithItem(e -> {
                    this.logger.error(e.getMessage());
                    EditMedia.Output output = new EditMedia.Output();
                    output.isSuccess = false;
                    output.message = e.getMessage();
                    return output;
                });
    }
}
