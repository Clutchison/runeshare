package com.hutchison.runeshare;

import com.hutchison.runeshare.annotation.Route;
import com.hutchison.runeshare.routing.Listener;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.security.auth.login.LoginException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RuneshareController {

    private final Listener listener;

    @Autowired
    public RuneshareController(Listener listener) throws LoginException {
        this.listener = listener;
        Set<Method> methodsAnnotatedWith = new Reflections("com.hutchison.runeshare.routing", new MethodAnnotationsScanner()).getMethodsAnnotatedWith(Route.class);
        Map<Method, Annotation> methodMap = methodsAnnotatedWith.stream()
                .collect(Collectors.toMap(
                        method -> method,
                        method -> Arrays.stream(method.getDeclaredAnnotations())
                                .filter(annotation -> annotation instanceof Route)
                                .findFirst()
                                .orElseThrow(() -> new RuntimeException("Failed to find route annotation"))
                ));
        System.out.println("Welp");

        listener.listen();
    }
}
