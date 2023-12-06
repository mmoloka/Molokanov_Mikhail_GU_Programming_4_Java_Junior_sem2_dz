package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnnotationProcessor {
    /**
     * Данный метод находит все void методы без аргументов в классе, и запускеет их.
     * <p>
     * Для запуска создается тестовый объект с помощью конструткора без аргументов.
     */
    public static void runAnnotation(Class<?> testClass,
                                     Class<? extends java.lang.annotation.Annotation> annotationClass) {
        final Object testObj = getObject(testClass);

        List<Method> methods = new ArrayList<>();
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                if (annotationClass.isAssignableFrom(Test.class) && !method.isAnnotationPresent(Skip.class)
                        || !annotationClass.isAssignableFrom(Test.class)) {
                    checkMethod(method);
                    methods.add(method);
                }
            }
        }

        if (annotationClass.isAssignableFrom(Test.class)) {
            methods.sort(Comparator.comparingInt(it -> it.getAnnotation(Test.class).value()));
        }
        methods.forEach(it -> runMethod(it, testObj));
    }

    private static Object getObject(Class<?> testClass) {
        final Constructor<?> declaredConstructor;
        try {
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Для класса \"" + testClass.getName() + "\" не найден конструктор без аргументов");
        }

        final Object testObj;
        try {
            testObj = declaredConstructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
        }
        return testObj;
    }

    private static void checkMethod(Method method) {
        if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
            throw new IllegalArgumentException("Метод \"" + method.getName() + "\" должен быть void и не иметь аргументов");
        }
    }

    private static void runMethod(Method testMethod, Object testObj) {
        try {
            testMethod.invoke(testObj);
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось запустить метод \"" + testMethod.getName() + "\"");
        }
    }
}
