package com.hutchison.runeshare.model.entity;

import com.hutchison.runeshare.model.dto.RegionDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor

@Entity
@Table(name = "REGION")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    Long id;

    @Column(name = "abbreviation", nullable = false)
    String abbreviation;
    @Column(name = "icon_absolute_path", nullable = false)
    String iconAbsolutePath;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "name_ref", nullable = false, unique = true)
    String nameRef;

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
