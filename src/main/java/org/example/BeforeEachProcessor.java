package org.example;

public class BeforeEachProcessor {

    public static void runBefore(Class<?> testClass) {
        AnnotationProcessor.runAnnotation(testClass, BeforeEach.class);
    }
}
