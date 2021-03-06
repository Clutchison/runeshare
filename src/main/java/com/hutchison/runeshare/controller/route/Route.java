package com.hutchison.runeshare.controller.route;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Route {
    String contains() default "";

    String startsWith() default "";

    String matches() default "";
}
