package com.hutchison.runeshare.model.entity;

import com.hutchison.runeshare.model.dto.KeywordDto;
import com.hutchison.runeshare.model.entity.card.Card;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor

@Entity
@Table(name = "KEYWORD")
public class Keyword implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    Long id;
    @Column(name = "description", nullable = false)
    String description;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "name_ref", unique = true, nullable = false)
    String nameRef;
    @ManyToMany(mappedBy = "keywords")
    List<Card> associatedCards;

    public Keyword(String description, String name, String nameRef) {
        this.description = description;
        this.name = name;
        this.nameRef = nameRef;
    }

    public Keyword(Long id) {
        this.id = id;
    }

    public static Keyword fromDto(KeywordDto dto) {
        return new Keyword(
                dto.getDescription(),
                dto.getName(),
                dto.getNameRef()
        );
    }
}
