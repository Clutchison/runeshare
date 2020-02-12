package com.hutchison.runeshare.persistence.entity;

import com.hutchison.runeshare.model.dto.KeywordDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter

@Entity
@Table(name = "KEYWORD")
public class Keyword implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false) //, columnDefinition = "id BIGINT NOT NULL AUTOINCREMENT")
    Long id;
    @Column(name = "description", nullable = false)
    final String description;
    @Column(name = "name", nullable = false)
    final String name;
    @Column(name = "name_ref", unique = true, nullable = false)
    final String nameRef;

    public Keyword(String description, String name, String nameRef) {
        this.description = description;
        this.name = name;
        this.nameRef = nameRef;
    }

    public static Keyword fromDto(KeywordDto dto) {
        return new Keyword(
                dto.getDescription(),
                dto.getName(),
                dto.getNameRef()
        );
    }
}
