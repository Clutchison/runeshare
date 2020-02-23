package com.hutchison.runeshare.model.entity.card;

import com.hutchison.runeshare.model.entity.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "CARD")
@Builder
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    Long id;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "ASSOCIATED_CARD_ASSOCIATED_WITH_CARDS",
            joinColumns = @JoinColumn(name = "card1_id"),
            inverseJoinColumns = @JoinColumn(name = "card2_id")
    )
    Set<Card> associatedCards;
    @ManyToMany(mappedBy = "associatedCards", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Card> associatedWithCards;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assets_id", referencedColumnName = "id")
    Assets assets;
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    Region region;
    @Column(name = "attack")
    Integer attack;
    @Column(name = "cost")
    Integer cost;
    @Column(name = "health")
    Integer health;
    @Column(name = "description", nullable = false)
    String description;
    @Column(name = "description_raw", nullable = false)
    String descriptionRaw;
    @Column(name = "level_up_description")
    String levelUpDescription;
    @Column(name = "level_up_description_raw")
    String levelUpDescriptionRaw;
    @Column(name = "flavor_text", nullable = false)
    String flavorText;
    @Column(name = "artist_name", nullable = false)
    String artistName;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "cardCode", unique = true, nullable = false)
    String cardCode;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "CARD_KEYWORD",
            joinColumns = @JoinColumn(name = "CARD_id"),
            inverseJoinColumns = @JoinColumn(name = "KEYWORD_id")
    )
    Set<Keyword> keywords;
    @ManyToOne
    @JoinColumn(name = "spell_speed_id")
    SpellSpeed spellSpeed;
    @ManyToOne
    @JoinColumn(name = "rarity_id", nullable = false)
    Rarity rarity;
    @Column(name = "subtype")
    String subtype;
    @Column(name = "supertype")
    String supertype;
    @Column(name = "type", nullable = false)
    String type;
    @Column(name = "collectible", nullable = false)
    Boolean collectible;

    Card(Set<Card> associatedCards,
         Set<Card> associatedWithCards,
         Assets assets,
         Region region,
         Integer attack,
         Integer cost,
         Integer health,
         String description,
         String descriptionRaw,
         String levelUpDescription,
         String levelUpDescriptionRaw,
         String flavorText,
         String artistName,
         String name,
         String cardCode,
         Set<Keyword> keywords,
         SpellSpeed spellSpeed,
         Rarity rarity,
         String subtype,
         String supertype,
         String type,
         Boolean collectible) {
        this.associatedCards = associatedCards;
        this.associatedWithCards = associatedWithCards;
        this.assets = assets;
        this.region = region;
        this.attack = attack;
        this.cost = cost;
        this.health = health;
        this.description = description;
        this.descriptionRaw = descriptionRaw;
        this.levelUpDescription = levelUpDescription;
        this.levelUpDescriptionRaw = levelUpDescriptionRaw;
        this.flavorText = flavorText;
        this.artistName = artistName;
        this.name = name;
        this.cardCode = cardCode;
        this.keywords = keywords;
        this.spellSpeed = spellSpeed;
        this.rarity = rarity;
        this.subtype = subtype;
        this.supertype = supertype;
        this.type = type;
        this.collectible = collectible;
    }
}
