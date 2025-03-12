ALTER TABLE banner_loots
DROP
CONSTRAINT fk_banloo_on_banner;

ALTER TABLE banner_loots
DROP
CONSTRAINT fk_banloo_on_loot;

CREATE TABLE banner_loot
(
    banner_id BIGINT NOT NULL,
    loot_id   BIGINT NOT NULL
);

ALTER TABLE banner_loot
    ADD CONSTRAINT fk_banloo_on_banner FOREIGN KEY (banner_id) REFERENCES banner (id);

ALTER TABLE banner_loot
    ADD CONSTRAINT fk_banloo_on_loot FOREIGN KEY (loot_id) REFERENCES loot (id);

DROP TABLE banner_loots CASCADE;