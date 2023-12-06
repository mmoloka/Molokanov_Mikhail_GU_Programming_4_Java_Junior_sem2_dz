package org.example;

public class AfterEachProcessor {
    public static void runAfter(Class<?> testClass) {
        AnnotationProcessor.runAnnotation(testClass, AfterEach.class);
    }
}
