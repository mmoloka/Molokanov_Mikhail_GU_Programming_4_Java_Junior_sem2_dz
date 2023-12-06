package org.example;

/**
 * Расширить пример с запуском тестов следующими фичами:
 * 1. Добавить аннотации BeforeEach, AfterEach,
 * которые ставятся над методами void без аругментов и запускаются ДО и ПОСЛЕ всех тестов соответственно.
 * 2. В аннотацию Test добавить параметр order() со значением 0 по умолчанию.
 * Необходимо при запуске тестов сортировать тесты по этому параметру (от меньшего к большему).
 * Т.е. если есть методы @Test(order = -2) void first, @Test void second, Test(order = 5) void third,
 * то порядок вызовов first -> second -> third
 * 3.* Добавить аннотацию @Skip, которую можно ставить над тест-методами. Если она стоит - то тест не запускается.
 * 4.* При наличии идей, реализовать их и написать об этом в комментарии при сдаче.
 */
public class Task {
    public static void main(String[] args) {
        TestProcessor.runTest(MyTest.class);
    }

    static class MyTest {

        @BeforeEach
        void firstBefore() {
            System.out.println("____________firstBefore запущен");
        }

        @BeforeEach
        void secondBefore() {
            System.out.println("____________secondBefore запущен");
        }

        @Test(-2)
        void firstTest() {
            System.out.println("firstTest запущен");
        }

        @Skip
        @Test
        void secondTest() {
            System.out.println("secondTest запущен");
        }

        @Test(5)
        void thirdTest() {
            System.out.println("thirdTest запущен");
        }

        @Skip
        @Test(255)
        void fourthTest() {
            System.out.println("fourthTest запущен");
        }

        @AfterEach
        void firstAfter() {
            System.out.println("____________firstAfter запущен");
        }

        @AfterEach
        void secondAfter() {
            System.out.println("____________secondAfter запущен");
        }
    }
}
