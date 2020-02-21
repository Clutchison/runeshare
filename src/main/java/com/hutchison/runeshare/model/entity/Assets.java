package com.hutchison.runeshare.model.entity;

import com.hutchison.runeshare.model.dto.AssetsDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor

@Entity
@Table(name = "ASSETS")
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    Long id;
    @Column(name = "game_absolute_path", nullable = false)
    String gameAbsolutePath;
    @Column(name = "full_absolute_path", nullable = false)
    String fullAbsolutePath;

    public Assets(String gameAbsolutePath, String fullAbsolutePath) {
        this.gameAbsolutePath = gameAbsolutePath;
        this.fullAbsolutePath = fullAbsolutePath;
    }

    public static Assets fromDto(AssetsDto dto) {
        return new Assets(
                dto.getGameAbsolutePath(),
                dto.getFullAbsolutePath()
        );
    }
}
