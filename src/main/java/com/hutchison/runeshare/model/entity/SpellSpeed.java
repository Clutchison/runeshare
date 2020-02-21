package com.hutchison.runeshare.model.entity;

import com.hutchison.runeshare.model.dto.SpellSpeedDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor

@Entity
@Table(name = "SPELL_SPEED")
public class SpellSpeed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    Long id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "name_ref", nullable = false, unique = true)
    String nameRef;

    public SpellSpeed(String name, String nameRef) {
        this.name = name;
        this.nameRef = nameRef;
    }

    public static SpellSpeed fromDto(SpellSpeedDto dto) {
        return new SpellSpeed(
                dto.getName(),
                dto.getNameRef()
        );
    }
}
