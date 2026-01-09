package com.aleksandrovaveronika.task14.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class SeleniumTitleTest {
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private WebDriver webDriver = new ChromeDriver();

    @BeforeEach
    void setup() {
        // Открытие тестируемой страницы
        webDriver.get(BASE_URL);
    }

    @Test
    @Tag("selenium")
    @DisplayName("Базовый тест на проверку заголовка после открытия страницы")
    void bonigarciaTitleTest() {
        String expectedTitle = "Hands-On Selenium WebDriver with Java";

        // Получение фактического заголовка страницы
        String actualTitle = webDriver.getTitle();

        // Проверка соответствия фактического заголовка ожидаемому варианту
        assertAll(
                () -> assertEquals(expectedTitle, actualTitle,
                        "The title of the opening page should be " + expectedTitle),
                () -> assertTrue(!actualTitle.isEmpty(),
                        "The title of the opening page should not be empty")
        );
    }

    @AfterEach
    void teardown() {
        // Закрытие тестируемой страницы
        webDriver.close();
    }
}
