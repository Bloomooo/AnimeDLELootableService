package org.acme.dto.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "loot")
public class Loot extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rarity")
    private int rarity;

    @Column(name = "limited")
    private boolean limited;

    @Column(name = "splashart_card")
    @Lob
    private Blob splashartCard;

    @Column(name = "splashart_banner")
    @Lob
    private Blob splashartBanner;

    @ManyToOne
    private Media media;

    @Column(name = "description")
    private String description;
}
