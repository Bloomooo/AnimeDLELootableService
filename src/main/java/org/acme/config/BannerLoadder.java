package org.acme.config;

import io.quarkus.scheduler.Scheduled;
import io.vertx.core.Vertx;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.service.CBannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class BannerLoadder {
    private final CBannerService bannerService;
    private final Vertx vertx;
    private final Logger logger;

    public BannerLoadder(CBannerService bannerService, Vertx vertx) {
        this.bannerService = bannerService;
        this.vertx = vertx;
        this.logger = LoggerFactory.getLogger(BannerLoadder.class);
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void scheduledLoadBanner() {
        vertx.runOnContext(v -> {
            try {
                this.bannerService.loadBanner()
                        .onItem().transform(success -> {
                            if (success) {
                                this.logger.info("Banner loaded");
                            }
                            return success;
                        })
                        .onFailure().recoverWithItem(e -> {
                            this.logger.error("Banner loaded failed: " + e.getMessage());
                            return false;
                        })
                        .subscribe().with(success -> {});
            } catch (Exception e) {
                this.logger.error("Banner loaded failed: " + e.getMessage());
            }
        });
    }
}
