package org.example;

public class TestProcessor {
    public static void runTest(Class<?> testClass) {
        BeforeEachProcessor.runBefore(testClass);
        AnnotationProcessor.runAnnotation(testClass, Test.class);
        AfterEachProcessor.runAfter(testClass);
    }
}
