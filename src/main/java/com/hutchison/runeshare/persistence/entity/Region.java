package com.hutchison.runeshare.persistence.entity;

import com.hutchison.runeshare.model.dto.RegionDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "REGION")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    @Column(name = "abbreviation", nullable = false)
    final String abbreviation;
    @Column(name = "icon_absolute_path", nullable = false)
    final String iconAbsolutePath;
    @Column(name = "name", nullable = false)
    final String name;
    @Column(name = "name_ref", nullable = false, unique = true)
    final String nameRef;

    public Region(String abbreviation, String iconAbsolutePath, String name, String nameRef) {
        this.abbreviation = abbreviation;
        this.iconAbsolutePath = iconAbsolutePath;
        this.name = name;
        this.nameRef = nameRef;
    }

    public static Region fromDto(RegionDto dto) {
        return new Region(
                dto.getAbbreviation(),
                dto.getIconAbsolutePath(),
                dto.getName(),
                dto.getNameRef()
        );
    }
}
