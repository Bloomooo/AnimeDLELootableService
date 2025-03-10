package org.acme.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CHARACTER")
public class Character extends Loot{
}
