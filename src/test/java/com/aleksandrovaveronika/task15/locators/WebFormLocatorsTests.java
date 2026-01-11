package com.aleksandrovaveronika.task15.locators;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebFormLocatorsTests {
    WebDriver webDriver = new ChromeDriver();
    public static final String URL_WEB_FORM = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @BeforeEach
    void setup() {
        webDriver.get(URL_WEB_FORM);
        webDriver.manage().window().maximize();
    }

    @Test
    @Tag("locators")
    @Tag("icon-link")
    @DisplayName("Базовые тесты для локаторов элемента Icon-link страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsIconLinkTests() {
        // CSS локатор
        WebElement iconLinkCss = webDriver.findElement(By.cssSelector("a[href = 'https://github.com/bonigarcia/selenium-webdriver-java']"));
        // XPath локатор
        WebElement iconLinkXpath = webDriver.findElement(By.xpath("//a[@href = 'https://github.com/bonigarcia/selenium-webdriver-java']"));

        String expectedURL = "https://github.com/bonigarcia/selenium-webdriver-java";

        assertAll(
                () -> assertNotNull(iconLinkCss, "Icon-link should not be null"),
                () -> assertNotNull(iconLinkXpath, "Icon-link should not be null"),
                () -> assertTrue(iconLinkCss.isDisplayed(), "Icon-link should not be hidden"),
                () -> assertTrue(iconLinkXpath.isDisplayed(), "Icon-link should not be hidden"),
                () -> assertEquals(iconLinkCss, iconLinkXpath,
                        "The links of both locators should be equal")
        );

        // Сохранение исходного окна
        String initialWindow = webDriver.getWindowHandle();
        // Кликание на иконку-ссулку
        iconLinkCss.click();
        // Переключение на новое окно
        for (String windowHandle : webDriver.getWindowHandles()) {
            if (!windowHandle.equals(initialWindow)) {
                webDriver.switchTo().window(windowHandle);
                break;
            }
        }

        // Получение актуального URL после перехода по ссылке
        String actualURLCss = webDriver.getCurrentUrl();

        assertTrue(actualURLCss.contains("https://github.com/bonigarcia/selenium-webdriver-java"),
                        "Should be transition to the site https://github.com/bonigarcia/selenium-webdriver-java");

        // Возврат на исходную страницу
        webDriver.switchTo().window(initialWindow);
    }

    @Test
    @Tag("locators")
    @Tag("headers")
    @DisplayName("Базовые тесты для локаторов элементов Header страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsHeaderTests() {
        // CSS локатор
        List<WebElement> headers1Css = webDriver.findElements(By.cssSelector("h1"));
        List<String> headers1CssText = headers1Css.stream().map(WebElement::getText).toList();

        // XPath локатор
        List<WebElement> headers5Xpath = webDriver.findElements(By.xpath("//h5"));
        List<String> headers5XpathText = headers5Xpath.stream().map(WebElement::getText).toList();

        assertAll(
                () -> assertTrue(headers1CssText.contains("Hands-On Selenium WebDriver with Java") &&
                        headers1CssText.contains("Web form"),
                        "The headers1 should be 'Hands-On Selenium WebDriver with Java' and 'Web form'"),
                () -> assertTrue(!headers1Css.isEmpty(),
                        "The list of headers1 should not be empty"),
                () -> assertTrue(headers5XpathText.contains("Practice site"),
                        "The header5 should be 'Practice site'"),
                () -> assertTrue(!headers5Xpath.isEmpty(),
                        "The list of headers5 should not be empty")
        );
    }

    @Test
    @Tag("locators")
    @Tag("text-input")
    @DisplayName("Базовые тесты для локаторов элемента Text input страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsTextInputTests() throws InterruptedException {
        // CSS локатор
        WebElement textInputCss = webDriver.findElement(By.cssSelector("input[name = 'my-text']"));
        // XPath локатор
        WebElement textInputXpath = webDriver.findElement(By.xpath("//label[contains(text(), 'Text input')]/input"));

        assertAll(
                () -> assertTrue(textInputCss.isEnabled(),
                        "Text input should not be disabled"),
                () -> assertTrue(textInputXpath.isEnabled(),
                        "Text input should not be disabled"),
                () -> assertEquals(textInputCss, textInputXpath,
                        "Both locators should have the same element"),
                () -> assertEquals(textInputCss.getAttribute("myprop"), textInputXpath.getAttribute("myprop"),
                        "Attribute 'myprop' of both locators should have the value 'myvalue'")
        );

        String initialValue = textInputCss.getAttribute("value");
        assertEquals("", initialValue,
                "Initial value of Text Input should be empty");

        String testData = "bonigarcia-selenium-webdriver-java";
        textInputCss.sendKeys(testData);

        Thread.sleep(2000);

        String actualValue = textInputCss.getAttribute("value");
        assertEquals(testData, actualValue,
                "The test value of Text Input should be " + testData);
    }

    @Test
    @Tag("locators")
    @Tag("password")
    @DisplayName("Базовые тесты для локаторов элемента Password страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsPasswordTests() throws InterruptedException {
        // CSS локатор
        WebElement passwordCss = webDriver.findElement(By.cssSelector("input[name = 'my-password']"));
        // XPath локатор
        WebElement passwordXpath = webDriver.findElement(By.xpath("//label[contains(text(), 'Password')]/input"));

        assertAll(
                () -> assertTrue(passwordCss.isEnabled(),
                        "Password should not be disabled"),
                () -> assertTrue(passwordXpath.isEnabled(),
                        "Password should not be disabled"),
                () -> assertEquals(passwordCss, passwordXpath,
                        "Both locators should have the same element"),
                () -> assertEquals(passwordCss.getAttribute("type"), passwordXpath.getAttribute("type"),
                        "Attribute 'type' of both locators should have the value 'password'")
        );

        String initialPassword = passwordXpath.getAttribute("value");
        assertEquals("", initialPassword,
                "Initial value of Password should be empty");

        String testData = "BoniGarcia.Selenium-Java:4.38.0";
        passwordXpath.sendKeys(testData);

        Thread.sleep(2000);

        String actualPassword = passwordXpath.getAttribute("value");
        assertEquals(testData, actualPassword,
                "The test value of Password should be " + testData);
    }

    @Test
    @Tag("locators")
    @Tag("textarea")
    @DisplayName("Базовые тесты для локаторов элемента Textarea страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsTextareaTests() throws InterruptedException {
        // CSS локатор
        WebElement textareaCss = webDriver.findElement(By.cssSelector("textarea[name = 'my-textarea']"));
        // XPath локатор
        WebElement textareaXpath = webDriver.findElement(By.xpath("//label[contains(text(), 'Textarea')]/textarea"));

        assertAll(
                () -> assertNotNull(textareaCss,
                        "Textarea should not be null"),
                () -> assertNotNull(textareaXpath,
                        "Textarea should not be null"),
                () -> assertEquals(textareaCss, textareaXpath,
                        "Both locators should have the same element"),
                () -> assertEquals(textareaCss.getAttribute("name"), textareaXpath.getAttribute("name"),
                        "The 'name' of both locators should be 'my-textarea'")
        );

        String initialTextarea = textareaCss.getAttribute("value");
        assertEquals("", initialTextarea,
                "The initial value of Textarea should be empty");

        String testData = "'Boni Garcia is an open source and test automation enthusiast, tech lead at Selenium and creator of WebDriverManager and Selenium Manager.'";
        textareaCss.sendKeys(testData);

        Thread.sleep(2000);
        String actualTextarea = textareaCss.getAttribute("value");
        assertEquals(testData, actualTextarea,
                "The test value of Textarea should be " + testData);
    }

    @Test
    @Tag("locators")
    @Tag("disabled-input")
    @DisplayName("Базовые тесты для локаторов элемента Disabled input страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsDisabledInputTests() {
        // CSS локатор
        WebElement disabledInputCss = webDriver.findElement(By.cssSelector("input[name = 'my-disabled']"));
        // XPath локатор
        WebElement disabledInputXpath = webDriver.findElement(By.xpath("//label[contains(text(), 'Disabled input')]/input"));

        assertAll(
                () -> assertTrue(disabledInputCss.isDisplayed(),
                        "Disabled input should not be hidden"),
                () -> assertTrue(disabledInputXpath.isDisplayed(),
                        "Disabled input should not be hidden"),
                () -> assertFalse(disabledInputCss.isEnabled(),
                        "Disabled input should be blocked"),
                () -> assertFalse(disabledInputXpath.isEnabled(),
                        "Disabled input should be blocked"),
                () -> assertEquals(disabledInputCss, disabledInputXpath,
                        "Both locators should have the same element"),
                () -> assertEquals(disabledInputCss.getAttribute("placeholder"), disabledInputXpath.getAttribute("placeholder"),
                        "The 'placeholder' of both locators should be 'Disabled Input'")
        );
    }

    @Test
    @Tag("locators")
    @Tag("readonly-input")
    @DisplayName("Базовые тесты для локаторов элемента Readonly input страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsReadonlyInputTests() {
        // CSS локатор
        WebElement readonlyInputCss = webDriver.findElement(By.cssSelector("input[name = 'my-readonly']"));
        // XPath локатор
        WebElement readonlyInputXpath = webDriver.findElement(By.xpath("//label[contains(text(), 'Readonly input')]/input"));

        assertAll(
                () -> assertTrue(readonlyInputCss.isDisplayed(),
                        "Readonly input should not be hidden"),
                () -> assertTrue(readonlyInputXpath.isDisplayed(),
                        "Readonly input should not be hidden"),
                () -> assertTrue(readonlyInputCss.isEnabled(),
                        "Readonly input should be disabled"),
                () -> assertTrue(readonlyInputXpath.isEnabled(),
                        "Readonly input should be disabled"),
                () -> assertEquals(readonlyInputCss, readonlyInputXpath,
                        "Both locators should have the same element"),
                () -> assertEquals(readonlyInputCss.getAttribute("value"), readonlyInputXpath.getAttribute("value"),
                        "The 'value' of both locators should be 'Readonly input'")
        );
    }

    @Test
    @Tag("locators")
    @Tag("dropdown-select")
    @DisplayName("Базовые тесты для локаторов элемента Dropdown (select) страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsDropdownSelectTests() {
        // CSS локатор
        WebElement dropdownSelectCss = webDriver.findElement(By.cssSelector("select[name = 'my-select']"));
        // XPath локатор
        WebElement dropdownSelectXpath = webDriver.findElement(By.xpath(
                "//label[contains(text(), 'Dropdown (select)')]" +
                        "/select[@name = 'my-select']"));

        assertAll(
                () -> assertTrue(dropdownSelectCss.isDisplayed(),
                        "Dropdown (select) should not be hidden"),
                () -> assertTrue(dropdownSelectXpath.isDisplayed(),
                        "Dropdown (select) should not be hidden"),
                () -> assertTrue(dropdownSelectCss.isEnabled(),
                        "Dropdown (select) should not be disabled"),
                () -> assertTrue(dropdownSelectXpath.isEnabled(),
                        "Dropdown (select) should not be disabled"),
                () -> assertEquals(dropdownSelectCss, dropdownSelectXpath,
                        "Both locators should have the same element")
        );

        Select selectCss = new Select(dropdownSelectCss);
        Select selectXpath = new Select(dropdownSelectCss);

        assertAll(
                () -> assertEquals(4, selectCss.getOptions().size(),
                        "Dropdown (select) should have 4 options"),
                () -> assertEquals(4, selectXpath.getOptions().size(),
                        "Dropdown (select) should have 4 options"),
                () -> assertEquals(selectCss.getFirstSelectedOption().getText(), selectXpath.getFirstSelectedOption().getText(),
                        "The first option of both Dropdown (select) should be 'Open this select menu'")
        );

        // Выбор опции, содержащей видимый текст "One"
        selectCss.selectByContainsVisibleText("One");

        assertAll(
                () -> assertEquals("1", selectCss.getFirstSelectedOption().getAttribute("value"),
                        "The selected option 'One' should have the value '1'"),
                () -> assertEquals("One", selectCss.getFirstSelectedOption().getText(),
                        "The selected option 'One' should have the text 'One'"),
                () -> assertTrue(selectCss.getFirstSelectedOption().isSelected(),
                        "The selected option 'One' should be selected")
        );

        // Выбор опции, содержащей значение "2"
        selectXpath.selectByValue("2");

        assertAll(
                () -> assertEquals("2", selectXpath.getFirstSelectedOption().getAttribute("value"),
                        "The selected option '2' should have the value '2'"),
                () -> assertEquals("Two", selectXpath.getFirstSelectedOption().getText(),
                        "The selected option '2' should have the text 'Two'"),
                () -> assertTrue(selectXpath.getFirstSelectedOption().isSelected(),
                        "The selected option '2' should be selected")
        );

        // Выбор опции с индексом "3" ("Open this select menu" имеет индекс 0)
        selectCss.selectByIndex(3);

        assertAll(
                () -> assertEquals("3", selectCss.getFirstSelectedOption().getAttribute("value"),
                        "The selected option with index '3' should have the value '3'"),
                () -> assertEquals("Three", selectCss.getFirstSelectedOption().getText(),
                        "The selected option with index '3' should have the text 'Three'"),
                () -> assertTrue(selectCss.getFirstSelectedOption().isSelected(),
                        "The selected option with index '3' should be selected")
        );
    }

    @ParameterizedTest
    @Tag("locators")
    @Tag("dropdown-datalist")
    @DisplayName("Параметризованные тесты для локаторов элемента Dropdown (datalist) страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    @CsvSource({
            "San Francisco, San Fr",
            "New York, New Y",
            "Seattle, Seat",
            "Los Angeles, Los A",
            "Chicago, Chic"
    })
    void locatorsDropdownDataListTests(String optionText, String optionPartOfText) {
        // CSS локатор
        WebElement dropdownDataListCss = webDriver.findElement(By.cssSelector("input[name = 'my-datalist']"));
        // XPath локатор
        WebElement dropdownDataListXpath = webDriver.findElement(By.xpath(
                "//label[contains(text(), 'Dropdown (datalist)')]/input"));

        assertAll(
                () -> assertTrue(dropdownDataListCss.isDisplayed(),
                        "Dropdown (datalist) should not be hidden"),
                () -> assertTrue(dropdownDataListXpath.isDisplayed(),
                        "Dropdown (datalist) should not be hidden"),
                () -> assertTrue(dropdownDataListCss.isEnabled(),
                        "Dropdown (datalist) should not be disabled"),
                () -> assertTrue(dropdownDataListXpath.isEnabled(),
                        "Dropdown (datalist) should not be disabled"),
                () -> assertEquals(dropdownDataListCss, dropdownDataListXpath,
                        "Both locators should have the same element"),
                () -> assertEquals(dropdownDataListCss.getAttribute("placeholder"),dropdownDataListXpath.getAttribute("placeholder"),
                        "The placeholders of both locators should be equal")
        );

        // Сброс данных и ввод части текста
        dropdownDataListXpath.clear();
        dropdownDataListXpath.sendKeys(optionPartOfText);

        assertEquals(optionPartOfText, dropdownDataListXpath.getAttribute("value"),
                        () -> String.format("'%s' should be accepted", optionPartOfText));

        // Сброс данных и ввод полного текста
        dropdownDataListXpath.clear();
        dropdownDataListXpath.sendKeys(optionText);

        assertEquals(optionText, dropdownDataListXpath.getAttribute("value"),
                        () -> String.format("'%s' should be accepted", optionText));

        // Проверка наличия наименования городов в datalist
        List<WebElement> optionsXpath = webDriver.findElements(By.xpath("//datalist[@id = 'my-options']/option"));

        assertTrue(!optionsXpath.isEmpty(), "Datalist should contain options");

        boolean optionExists = optionsXpath.stream().
                anyMatch(option -> {
                    String value = option.getAttribute("value");
                    return value != null && value.equals(optionText);
                });

        assertTrue(optionExists, "Option should exist in datalist: " + optionText);
    }

    @Test
    @Tag("locators")
    @Tag("file-input")
    @DisplayName("Базовые тесты для локаторов элемента File input страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsFileInputTests() {
        // CSS локатор
        WebElement fileInputCss = webDriver.findElement(By.cssSelector("input[name = 'my-file']"));
        // XPath локатор
        WebElement fileInputXpath = webDriver.findElement(By.xpath("//label[contains(text(), 'File input')]/input"));

        assertAll(
                () -> assertTrue(fileInputCss.isDisplayed(),
                        "File input should not be hidden"),
                () -> assertTrue(fileInputXpath.isDisplayed(),
                        "File input should not be hidden"),
                () -> assertTrue(fileInputCss.isEnabled(),
                        "File input should not be disabled"),
                () -> assertTrue(fileInputXpath.isEnabled(),
                        "File input should not be disabled"),
                () -> assertEquals(fileInputCss, fileInputXpath,
                        "Both locators should have the same element"),
                () -> assertEquals(fileInputCss.getAttribute("type"),fileInputXpath.getAttribute("type"),
                        "The 'type' of both locators should have 'file'")
        );

        try {
            // Создание тестового файла
            File testFile = File.createTempFile("selenium_test_", ".txt");

            try (PrintWriter printWriter = new PrintWriter(testFile)) {
                printWriter.println("Testing the Web Form of website https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
            }

            // Выбор тестового файла
            fileInputCss.sendKeys(testFile.getAbsolutePath());

            // Проверка выбора файла
            String selectedFile = fileInputCss.getAttribute("value");

            assertAll(
                    () -> assertNotNull(selectedFile,
                            "File name should not be null after selection"),
                    () -> assertTrue(!selectedFile.isEmpty(),
                            "File name should not be empty"),
                    () -> assertTrue(selectedFile.contains("selenium_test_") || selectedFile.contains(".txt"),
                            "File name should indicate a text file was selected")
            );

            // Сброс данных через перезагрузку страницы
            webDriver.navigate().refresh();

            // Удаление тестового файла
            testFile.delete();
        } catch (IOException exc) {
            exc.getMessage();
        }
    }

    @Test
    @Tag("locators")
    @Tag("checkbox")
    @DisplayName("Базовые тесты для локаторов элемента Checkbox страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsCheckboxTests() {
        // CSS локатор
        WebElement checkedCheckboxCss = webDriver.findElement(By.cssSelector("input[id = 'my-check-1']"));
        WebElement defaultCheckboxCss = webDriver.findElement(By.cssSelector("input[id = 'my-check-2']"));

        // XPath локатор
        WebElement checkedCheckboxXpath = webDriver.findElement(By.xpath("//input[@id = 'my-check-1']"));
        WebElement defaultCheckboxXpath = webDriver.findElement(By.xpath("//input[@id = 'my-check-2']"));

        assertAll(
                () -> assertTrue(checkedCheckboxCss.isDisplayed(),
                        "Checked Checkbox should not be hidden"),
                () -> assertTrue(checkedCheckboxXpath.isDisplayed(),
                        "Checked Checkbox should not be hidden"),
                () -> assertTrue(defaultCheckboxCss.isDisplayed(),
                        "Default Checkbox should not be hidden"),
                () -> assertTrue(defaultCheckboxXpath.isDisplayed(),
                        "Default Checkbox should not be hidden"),
                () -> assertTrue(checkedCheckboxCss.isEnabled(),
                        "Checked Checkbox should not be disabled"),
                () -> assertTrue(checkedCheckboxXpath.isEnabled(),
                        "Checked Checkbox should not be disabled"),
                () -> assertTrue(defaultCheckboxCss.isEnabled(),
                        "Default Checkbox should not be disabled"),
                () -> assertTrue(defaultCheckboxXpath.isEnabled(),
                        "Default Checkbox should not be disabled"),
                () -> assertEquals(checkedCheckboxCss, checkedCheckboxXpath,
                        "Both locators should have the same element 'Checked Checkbox'"),
                () -> assertEquals(defaultCheckboxCss, defaultCheckboxXpath,
                        "Both locators should have the same element 'Default Checkbox'")
        );

        // Проверка исходного состояния
        assertTrue(checkedCheckboxCss.isSelected() && !defaultCheckboxCss.isSelected(),
                        "Only Checked Checkbox should be selected");

        // Выбор дополнительно Default Checkbox
        defaultCheckboxCss.click();

        assertTrue(checkedCheckboxCss.isSelected() && defaultCheckboxCss.isSelected(),
                "Both Checked Checkbox and Default Checkbox should be selected");

        // Выбор только Default Checkbox с учетом сброса Сhecked Checkbox
        checkedCheckboxCss.click();

        assertTrue(!checkedCheckboxCss.isSelected() && defaultCheckboxCss.isSelected(),
                "Only Default Checkbox should be selected");

        // Восстановление исходного состояния
        defaultCheckboxCss.click();

        assertTrue(!checkedCheckboxCss.isSelected() && !defaultCheckboxCss.isSelected(),
                "Both Checked Checkbox and Default Checkbox should not be selected finally");
    }

    @Test
    @Tag("locators")
    @Tag("radio")
    @DisplayName("Базовые тесты для локаторов элемента Radio страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsRadioTests() {
        // CSS локатор
        WebElement checkedRadioCss = webDriver.findElement(By.cssSelector("input[id = 'my-radio-1']"));
        WebElement defaultRadioCss = webDriver.findElement(By.cssSelector("input[id = 'my-radio-2']"));

        // XPath локатор
        WebElement checkedRadioXpath = webDriver.findElement(By.xpath("//input[@id = 'my-radio-1']"));
        WebElement defaultRadioXpath = webDriver.findElement(By.xpath("//input[@id = 'my-radio-2']"));

        assertAll(
                () -> assertTrue(checkedRadioCss.isDisplayed(),
                        "Checked Radio should not be hidden"),
                () -> assertTrue(checkedRadioXpath.isDisplayed(),
                        "Checked Radio should not be hidden"),
                () -> assertTrue(defaultRadioCss.isDisplayed(),
                        "Default Radio should not be hidden"),
                () -> assertTrue(defaultRadioXpath.isDisplayed(),
                        "Default Radio should not be hidden"),
                () -> assertTrue(checkedRadioCss.isEnabled(),
                        "Checked Radio should not be disabled"),
                () -> assertTrue(checkedRadioXpath.isEnabled(),
                        "Checked Radio should not be disabled"),
                () -> assertTrue(defaultRadioCss.isEnabled(),
                        "Default Radio should not be disabled"),
                () -> assertTrue(defaultRadioXpath.isEnabled(),
                        "Default Radio should not be disabled"),
                () -> assertEquals(checkedRadioCss, checkedRadioXpath,
                        "Both locators should have the same element 'Checked Radio'"),
                () -> assertEquals(defaultRadioCss, defaultRadioXpath,
                        "Both locators should have the same element 'Default Radio'")
        );

        // Проверка исходного состояния
        assertTrue(checkedRadioCss.isSelected() && !defaultRadioCss.isSelected(),
                "Only Checked Radio should be selected initially");

        // Выбор дополнительно Default Checkbox
        defaultRadioCss.click();

        assertTrue(!checkedRadioCss.isSelected() && defaultRadioCss.isSelected(),
                "Only Default Radio should be selected");

        // Восстановление исходного состояния
        checkedRadioCss.click();

        assertTrue(checkedRadioCss.isSelected() && !defaultRadioCss.isSelected(),
                "Only Checked Radio should be selected finally");
    }

    @Test
    @Tag("locators")
    @Tag("color-picker")
    @DisplayName("Базовые тесты для локаторов элемента Color picker страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsColorPickerTests() {
        // CSS локатор
        WebElement colorPickerCss = webDriver.findElement(By.cssSelector("input[name = 'my-colors']"));
        // XPath локатор
        WebElement colorPickerXpath = webDriver.findElement(By.xpath("//label[contains(text(), 'Color picker')]/input"));

        assertAll(
                () -> assertTrue(colorPickerCss.isDisplayed(),
                        "Color picker should not be hidden"),
                () -> assertTrue(colorPickerXpath.isDisplayed(),
                        "Color picker should not be hidden"),
                () -> assertTrue(colorPickerCss.isEnabled(),
                        "Color picker should not be disabled"),
                () -> assertTrue(colorPickerXpath.isEnabled(),
                        "Color picker should not be disabled"),
                () -> assertEquals(colorPickerCss, colorPickerXpath,
                        "Both locators should have the same element 'Color picker'"),
                () -> assertEquals(colorPickerCss.getAttribute("type"), colorPickerXpath.getAttribute("type"),
                        "Both locators should have the type 'color'")
        );

        // Сохранение исходного цвета
        String initialColor = colorPickerCss.getAttribute("value");

        assertEquals("#563d7c", initialColor,
                "The initial color should be #563d7c");

        // Изменение и сохранение нового цвета
        colorPickerCss.sendKeys("#00FF00");
        String actualColor = colorPickerCss.getAttribute("value");

        assertTrue(actualColor.equalsIgnoreCase("#00FF00"),
                "The initial color should be #00FF00");

        // Возврат к исходному цвету
        colorPickerCss.clear();
        colorPickerCss.sendKeys("#563d7c");

        actualColor = colorPickerCss.getAttribute("value");

        assertTrue(actualColor.equalsIgnoreCase("#563d7c"),
                "The initial color should be #563d7c");
    }

    @Test
    @Tag("locators")
    @Tag("date-picker")
    @DisplayName("Базовые тесты для локаторов элемента Date picker страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsDatePickerTests() {
        // CSS локатор
        WebElement datePickerCss = webDriver.findElement(By.cssSelector("input[name = 'my-date']"));
        // XPath локатор
        WebElement datePickerXpath = webDriver.findElement(By.xpath("//label[contains(text(), 'Date picker')]/input"));

        assertAll(
                () -> assertTrue(datePickerCss.isDisplayed(),
                        "Date picker should not be hidden"),
                () -> assertTrue(datePickerXpath.isDisplayed(),
                        "Date picker should not be hidden"),
                () -> assertTrue(datePickerCss.isEnabled(),
                        "Date picker should not be disabled"),
                () -> assertTrue(datePickerXpath.isEnabled(),
                        "Date picker should not be disabled"),
                () -> assertEquals(datePickerCss, datePickerXpath,
                        "Both locators should have the same element 'Date picker'"),
                () -> assertEquals(datePickerCss.getAttribute("type"), datePickerXpath.getAttribute("type"),
                        "Both locators should have the type 'text'")
        );

        // Сохранение первоначального значения
        String initialDate = datePickerXpath.getAttribute("value");

        assertTrue(initialDate == null || initialDate.isEmpty(),
                "Date picker should be empty initially");

        // Ввод даты
        String testDate = "01/11/2026";
        datePickerXpath.clear();
        datePickerXpath.sendKeys(testDate);

        String actualDate = datePickerXpath.getAttribute("value");

        assertAll(
                () -> assertTrue(actualDate != null || !actualDate.isEmpty(),
                        "Date picker should not be empty after date input"),
                () -> assertTrue(actualDate.contains(testDate),
                        () -> String.format("Date picker should have the date: %s", testDate)),
                () -> assertTrue(!actualDate.contains("01-11-2026"),
                        () -> String.format("Date picker should have the date: %s", testDate)),
                () -> assertTrue(!actualDate.contains("01.11.2026"),
                        () -> String.format("Date picker should have the date: %s", testDate)),
                () -> assertTrue(!actualDate.contains("11/01/2026"),
                        () -> String.format("Date picker should have the date: %s", testDate))
        );

        // Сброс даты
        datePickerXpath.clear();

        assertTrue(datePickerXpath.getAttribute("value") == null ||
                        datePickerXpath.getAttribute("value").isEmpty(),
                "Date picker should be empty finally");
    }

    @Test
    @Tag("locators")
    @Tag("example-range")
    @DisplayName("Базовые тесты для локаторов элемента Example range страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsExampleRangeTests() {
        // CSS локатор
        WebElement exampleRangeCss = webDriver.findElement(By.cssSelector("input[name = 'my-range']"));
        // XPath локатор
        WebElement exampleRangeXpath = webDriver.findElement(By.xpath("//label[contains(text(), 'Example range')]/input"));

        assertAll(
                () -> assertTrue(exampleRangeCss.isDisplayed(),
                        "Example range should not be hidden"),
                () -> assertTrue(exampleRangeXpath.isDisplayed(),
                        "Example range should not be hidden"),
                () -> assertTrue(exampleRangeCss.isEnabled(),
                        "Example range should not be disabled"),
                () -> assertTrue(exampleRangeXpath.isEnabled(),
                        "Example range should not be disabled"),
                () -> assertEquals(exampleRangeCss, exampleRangeXpath,
                        "Both locators should have the same element 'Example range'"),
                () -> assertEquals(exampleRangeCss.getAttribute("type"), exampleRangeXpath.getAttribute("type"),
                        "Both locators should have the type 'range'"),
                () -> assertTrue(exampleRangeCss.getAttribute("min").equals("0") &&
                        exampleRangeCss.getAttribute("max").equals("10") &&
                        exampleRangeCss.getAttribute("step").equals("1") &&
                        exampleRangeCss.getAttribute("value").equals("5"),
                        "Example range should have attributes: min - 0, max - 10, step - 1, value - 5"),
                () -> assertTrue(exampleRangeXpath.getAttribute("min").equals("0") &&
                                exampleRangeXpath.getAttribute("max").equals("10") &&
                                exampleRangeXpath.getAttribute("step").equals("1") &&
                                exampleRangeXpath.getAttribute("value").equals("5"),
                        "Example range should have attributes: min - 0, max - 10, step - 1, value - 5"),
                () -> assertTrue(exampleRangeCss.getAttribute("value") != null ||
                                !exampleRangeCss.getAttribute("value").isEmpty(),
                        "The initial value of Example range should not be null or empty")
        );
    }

    @Test
    @Tag("locators")
    @Tag("submit")
    @DisplayName("Базовые тесты для локаторов элемента Submit страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsSubmitTests() {
        // CSS локатор
        WebElement submitCss = webDriver.findElement(By.cssSelector("button[type = 'submit']"));
        // XPath локатор
        WebElement submitXpath = webDriver.findElement(By.xpath("//button[contains(text(), 'Submit')]"));

        assertAll(
                () -> assertTrue(submitCss.isDisplayed(),
                        "Button Submit should not be hidden"),
                () -> assertTrue(submitXpath.isDisplayed(),
                        "Button Submit should not be hidden"),
                () -> assertTrue(submitCss.isEnabled(),
                        "Button Submit should not be disabled"),
                () -> assertTrue(submitXpath.isEnabled(),
                        "Button Submit should not be disabled"),
                () -> assertEquals(submitCss, submitXpath,
                        "Both locators should have the same element 'Button Submit'"),
                () -> assertEquals(submitCss.getAttribute("type"), submitXpath.getAttribute("type"),
                        "Both locators should have the same type 'submit'")
        );

        // Сохранение URL исходной страницы
        String initialUrl = webDriver.getCurrentUrl();

        // Нажатие на кнопку "Submit"
        submitXpath.click();

        // Сохранение URL текущей страницы
        String actualUrl = webDriver.getCurrentUrl();

        // Получение элементов текущей страницы
        WebElement submittedFormXpath = webDriver.findElement(By.xpath("//h1[contains(text(), 'Form submitted')]"));
        WebElement outcomeReceivedXpath = webDriver.findElement(By.xpath("//p[contains(text(), 'Received!')]"));

        assertAll(
                () -> assertTrue(actualUrl.contains("submitted-form.html"),
                        "Actual URL should have 'submitted-form.html'"),
                () -> assertEquals("Form submitted", submittedFormXpath.getText(),
                        "Header1 of the actual URL should be 'Form submitted'"),
                () -> assertEquals("Received!", outcomeReceivedXpath.getText(),
                        "The outcome of the actual URL should be 'Received!'")
        );

        // Вовзрат на исходную страницу Web Form
        webDriver.navigate().back();

        assertEquals(initialUrl, webDriver.getCurrentUrl(),
                () -> String.format("After return the actual URL should be %s", initialUrl));
    }

    @Test
    @Tag("locators")
    @Tag("return-to-index")
    @DisplayName("Базовые тесты для локаторов элемента Return to index страницы https://bonigarcia.dev/selenium-webdriver-java/web-form.html")
    void locatorsReturnToIndexTests() {
        // CSS локатор
        WebElement returnToIndexCss = webDriver.findElement(By.cssSelector("a[href = './index.html']"));
        // XPath локатор
        WebElement returnToIndexXpath = webDriver.findElement(By.xpath("//a[contains(text(), 'Return to index')]"));

        assertAll(
                () -> assertTrue(returnToIndexCss.isDisplayed(),
                        "Return to index should not be hidden"),
                () -> assertTrue(returnToIndexXpath.isDisplayed(),
                        "Return to index should not be hidden"),
                () -> assertTrue(returnToIndexCss.isEnabled(),
                        "Return to index should not be disabled"),
                () -> assertTrue(returnToIndexXpath.isEnabled(),
                        "Return to index should not be disabled"),
                () -> assertEquals(returnToIndexCss, returnToIndexXpath,
                        "Both locators should have the same element")
        );

        // Ссылка по локатору XPath
        String hrefLink = returnToIndexXpath.getAttribute("href");
        // Сохранение в виде текста для проверки после возврата
        String hrefLinkText = returnToIndexXpath.getText();
        // URL исходной страницы
        String initialUrl = webDriver.getCurrentUrl();

        // Клик на "Return to index"
        returnToIndexXpath.click();

        // Явное ожидание перехода на страницу, содержащую "index.html"
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("index.html"));

        // URL обновленной страницы
        String actualUrl = webDriver.getCurrentUrl();

        assertAll(
                () -> assertEquals(hrefLink, actualUrl,
                "Should be on index page"),
                () -> assertTrue(actualUrl.contains("index.html"),
                        "Should be transition to the site with /index.html"),
                () -> assertTrue(webDriver.getTitle().contains("Hands-On Selenium WebDriver"),
                        "Should have correct page title")
        );

        // Возврат на страницу Web Form
        webDriver.navigate().back();

        // Явное ожидание загрузки страницы Web Form
        wait.until(ExpectedConditions.urlToBe(initialUrl));

        // Поиск элементов повторно после возврата, поскольку предыдущие данные устарели
        WebElement returnToIndexXpathNew = webDriver.findElement(By.xpath("//a[@href = './index.html']"));

        assertEquals(hrefLinkText, returnToIndexXpathNew.getText(),
                "Return to index should have the same text");
    }

    @AfterEach
    void teardown () {
        webDriver.close();
    }
}
