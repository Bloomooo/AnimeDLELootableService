package org.acme.model;

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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Loot extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "rarity")
    private int rarity;

    @Column(name = "limited")
    private Boolean limited;

    @Column(name = "splashart_card")
    private byte[] splashartCard;

    @Column(name = "splashart_banner")
    private byte[] splashartBanner;

    @ManyToOne
    private Media media;

    @Column(name = "description")
    private String description;
}
