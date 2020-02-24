package com.hutchison.runeshare.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.List;

public class ListDeserializer<T> extends JsonDeserializer<List<T>> {

    @Override
    public List<T> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        return jp.readValueAs(List.class);
    }
}
