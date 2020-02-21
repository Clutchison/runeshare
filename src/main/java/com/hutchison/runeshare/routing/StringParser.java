package com.hutchison.runeshare.routing;

import com.hutchison.runeshare.annotation.Route;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StringParser {

    Set<Method> startsWithMethods;
    Set<Method> containsMethods;
    Set<Method> regexMethods;

    public StringParser() {
    }

    public String callMethod() {
        return "";
    }

    @Route(startsWith = "!deck")
    public String parseDeckString() {
        return "";
    }

    @Route(regex = ".*\\[.*\\].*")
    public String getCardImage() {
        return "";
    }
}
