package com.hutchison.runeshare.persistence.entity;

import com.hutchison.runeshare.model.dto.RarityDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor

@Entity
@Table(name = "RARITY")
public class Rarity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    Long id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "name_ref", unique = true, nullable = false)
    String nameRef;

    public Rarity(String name, String nameRef) {
        this.name = name;
        this.nameRef = nameRef;
    }

    public static Rarity fromDto(RarityDto dto) {
        return new Rarity(
                dto.getName(),
                dto.getNameRef()
        );
    }
}
