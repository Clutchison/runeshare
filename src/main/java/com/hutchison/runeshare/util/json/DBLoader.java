package com.hutchison.runeshare.util.json;

import com.hutchison.runeshare.persistence.entity.Keyword;
import com.hutchison.runeshare.persistence.entity.Rarity;
import com.hutchison.runeshare.persistence.entity.Region;
import com.hutchison.runeshare.persistence.entity.SpellSpeed;
import com.hutchison.runeshare.persistence.repository.KeywordRepository;
import com.hutchison.runeshare.persistence.repository.RarityRepository;
import com.hutchison.runeshare.persistence.repository.RegionRepository;
import com.hutchison.runeshare.persistence.repository.SpellSpeedRepository;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Component
public class DBLoader {

    List<JpaRepository> repositories;
    KeywordRepository keywordRepository;
    RarityRepository rarityRepository;
    RegionRepository regionRepository;
    SpellSpeedRepository spellSpeedRepository;

    @Autowired
    public DBLoader(
            KeywordRepository keywordRepository,
            RarityRepository rarityRepository,
            RegionRepository regionRepository,
            SpellSpeedRepository spellSpeedRepository
    ) {
        this.keywordRepository = keywordRepository;
        this.rarityRepository = rarityRepository;
        this.regionRepository = regionRepository;
        this.spellSpeedRepository = spellSpeedRepository;
        repositories = Arrays.asList(
                keywordRepository,
                rarityRepository,
                regionRepository,
                spellSpeedRepository
        );
    }

    public void load() {
        repositories.forEach(JpaRepository::deleteAllInBatch);
        CoreInput coreInput = JsonReader.readCoreInput();
        //        KeywordDto dto = new ObjectMapper().convertValue(keywords.get(0), KeywordDto.class);
//        Keyword keyword = Keyword.fromDto(dto);
//        Keyword entity = new Keyword(keyword.getDescription(), keyword.getName(), keyword.getNameRef());
        //        collect.forEach(keywordRepository::save);
        keywordRepository.saveAll(coreInput.getKeywords().stream()
                .map(Keyword::fromDto)
                .collect(Collectors.toList()));
        rarityRepository.saveAll(coreInput.getRarities().stream()
                .map(Rarity::fromDto)
                .collect(Collectors.toList()));
        regionRepository.saveAll(coreInput.getRegions().stream()
                .map(Region::fromDto)
                .collect(Collectors.toList()));
        spellSpeedRepository.saveAll(coreInput.getSpellSpeeds().stream()
                .map(SpellSpeed::fromDto)
                .collect(Collectors.toList()));
    }
}
