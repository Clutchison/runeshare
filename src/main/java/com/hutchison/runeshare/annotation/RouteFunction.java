package com.hutchison.runeshare.annotation;

import com.google.common.base.CaseFormat;

import java.util.Arrays;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public enum RouteFunction {
    CONTAINS(String::contains),
    STARTS_WITH(String::startsWith),
    REGEX((s1, s2) -> Pattern.matches(s2, s1));

    BiPredicate<String, String> biPredicate;
    private static Map<String, RouteFunction> map;

    static {
        map = Arrays.stream(RouteFunction.values())
                .collect(Collectors.toMap(
                        Enum::name,
                        rf -> rf
                ));
    }

    RouteFunction(BiPredicate<String, String> biPredicate) {
        this.biPredicate = biPredicate;
    }

    public boolean check(String s1, String s2) {
        return biPredicate.test(s1, s2);
    }

    public static RouteFunction fromString(String s) {
        return map.get(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, s));
    }
}
