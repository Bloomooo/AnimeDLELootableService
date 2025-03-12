package org.acme.service;

import io.quarkus.hibernate.reactive.panache.common.WithSession;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Banner;
import org.acme.model.Loot;
import org.acme.repository.CBannerRepository;
import org.acme.repository.CCharacterRepository;
import org.acme.repository.CLootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public class CBannerService {
    private final CCharacterRepository characterRepository;
    private final CLootRepository lootRepository;
    private final CBannerRepository bannerRepository;
    private final Logger logger;

    public CBannerService(CCharacterRepository characterRepository, CLootRepository lootRepository, CBannerRepository bannerRepository) {
        this.characterRepository = characterRepository;
        this.lootRepository = lootRepository;
        this.bannerRepository = bannerRepository;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @WithTransaction
    public Uni<Boolean> loadBanner() {
        return this.characterRepository.findRandomCharacterLimitedAndRarity(Boolean.TRUE, 5)
                .onItem().transformToUni(limited -> {
                    return this.characterRepository.findRandomCharacterLimitedAndRarityList(Boolean.FALSE, 4, 3)
                            .onItem().transformToUni(characters -> {
                                Banner banner = new Banner();
                                banner.setLimited(limited);
                                banner.setLoots(characters.stream().map(Loot.class::cast).collect(Collectors.toList()));
                                banner.setDateCreated(LocalDateTime.now());
                                banner.setName(limited.getDescription());
                                return this.bannerRepository.persistAndFlush(banner)
                                        .onItem().transform(Objects::nonNull)
                                        .onFailure().recoverWithItem(e -> {
                                           this.logger.error(e.getMessage());
                                           return Boolean.FALSE;
                                        });
                            })
                            .onFailure().recoverWithItem(e -> {
                                this.logger.error(e.getMessage());
                                return false;
                            });
                })
                .onFailure().recoverWithItem(e -> {;
                    return false;
                });
    }
}
