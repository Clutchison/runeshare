package com.hutchison.runeshare.util.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hutchison.runeshare.model.dto.*;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CORE_PATH = "datadragon/datadragon-core-en_us/en_us/data/globals-en_us.json";
    private static final String SET1_PATH = "datadragon/datadragon-set1-en_us/en_us/data/set1-en_us.json";

    public static CoreInput readCoreInput() {
        CoreInput coreInput;
        File f;
        try {
            f = new ClassPathResource(CORE_PATH).getFile();
            coreInput = objectMapper.readValue(f, CoreInput.class);
            List<KeywordDto> keywords = objectMapper.convertValue(coreInput.getKeywords(), new TypeReference<List<KeywordDto>>() {
            });
            List<RarityDto> rarities = objectMapper.convertValue(coreInput.getRarities(), new TypeReference<List<RarityDto>>() {
            });
            List<RegionDto> regions = objectMapper.convertValue(coreInput.getRegions(), new TypeReference<List<RegionDto>>() {
            });
            List<SpellSpeedDto> spellSpeeds = objectMapper.convertValue(coreInput.getSpellSpeeds(), new TypeReference<List<SpellSpeedDto>>() {
            });
            coreInput = new CoreInput(keywords, rarities, regions, spellSpeeds);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read core input.");
        }
        return coreInput;
    }

    public static SetInput readSetInput() {
        SetInput setInput;
        File f;
        try {
            f = new ClassPathResource(SET1_PATH).getFile();
            setInput = objectMapper.readValue(f, SetInput.class);
            List<CardDto> cards = objectMapper.convertValue(setInput.getCards(), new TypeReference<List<CardDto>>() {
            });
            setInput = new SetInput(cards);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read set input.");
        }
        return setInput;
    }
}
