package com.hutchison.runeshare.persistence.entity;

import com.hutchison.runeshare.model.dto.SpellSpeedDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter

@Entity
@Table(name = "SPELL_SPEED")
public class SpellSpeed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    Long id;
    @Column(name = "name", nullable = false)
    final String name;
    @Column(name = "name_ref", nullable = false, unique = true)
    final String nameRef;

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
