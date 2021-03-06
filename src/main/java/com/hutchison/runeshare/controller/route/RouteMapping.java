package com.hutchison.runeshare.controller.route;

import lombok.Value;

import java.lang.reflect.Method;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;

@Value
public class RouteMapping {

    Method method;
    BiPredicate<String, String> biPredicate;
    String routeValue;

    public RouteMapping(Method method) {
        Route route = method.getAnnotation(Route.class);
        this.method = method;
        if (!route.startsWith().equals("")) {
            this.routeValue = route.startsWith();
            this.biPredicate = String::startsWith;
        } else if (!route.contains().equals("")) {
            this.routeValue = route.contains();
            this.biPredicate = String::contains;
        } else if (!route.matches().equals("")) {
            this.routeValue = route.matches();
            this.biPredicate = (s1, s2) -> Pattern.matches(s2, s1);
        } else {
            throw new RuntimeException("Failed to create routeMapping");
        }
    }

    public boolean check(String s) {
        return biPredicate.test(s, routeValue);
    }
}
