package com.aleksandrovaveronika.task12.delivery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;
import io.qameta.allure.*;

@Epic("Расчет стоимости доставки.")
@Feature("Функциональность расчета доставки.")
@DisplayName("Тестирование расчета стоимости доставки.")
public class DeliveryCostCalculationTest {

    @Test
    @Tag("enum")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации перечисления CargoDimensions.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка всех элементов перечисления CargoDimensions.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Enum CargoDimensions. Позитивный тест для проверки элементов перечисления.")
    void enumCargoDimensionsPositiveTests() {
        CargoDimensions[] dimensions = CargoDimensions.values();

        assertAll(
                () -> assertEquals(2, dimensions.length,
                        "CargoDimensions должен содержать 2 значения."),
                () -> assertEquals("SMALL", CargoDimensions.SMALL.name(),
                        "name() должен возвращать строку SMALL."),
                () -> assertEquals("LARGE", CargoDimensions.LARGE.name(),
                        "name() должен возвращать строку LARGE."),
                () -> assertEquals(CargoDimensions.SMALL, CargoDimensions.valueOf("SMALL"),
                        "valueOf() должен преобразовать строку SMALL в enum."),
                () -> assertEquals(CargoDimensions.LARGE, CargoDimensions.valueOf("LARGE"),
                        "valueOf() должен преобразовать строку LARGE в enum."),
                () -> assertEquals("SMALL", CargoDimensions.SMALL.toString(),
                        "toString() должен возвращать строку SMALL."),
                () -> assertEquals("LARGE", CargoDimensions.LARGE.toString(),
                        "toString() должен возвращать строку LARGE."),
                () -> assertEquals(CargoDimensions.SMALL.name(), CargoDimensions.SMALL.toString(),
                        "name() и toString() должны возвращать строку SMALL."),
                () -> assertEquals(CargoDimensions.LARGE.name(), CargoDimensions.LARGE.toString(),
                        "name() и toString() должны возвращать строку LARGE."),
                () -> assertEquals(0, CargoDimensions.SMALL.ordinal(),
                        "SMALL должен иметь ordinal 0."),
                () -> assertEquals(1, CargoDimensions.LARGE.ordinal(),
                        "LARGE должен иметь ordinal 1."),
                () -> assertArrayEquals(new CargoDimensions[] {CargoDimensions.SMALL, CargoDimensions.LARGE}, dimensions,
                        "Массив всех значений должен иметь 2 элемента: SMALL и LARGE.")
        );
    }

    @ParameterizedTest
    @Tag("enum")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации перечисления CargoDimensions.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Параметризованная проверка всех элементов перечисления CargoDimensions.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Enum CargoDimensions. Позитивный тест для проверки параметров элементов перечисления.")
    @EnumSource(CargoDimensions.class)
    void enumCargoDimensionsPositiveParameterizedTests(CargoDimensions dimensions) {
        assertAll(
                () -> assertNotNull(dimensions, "Значение перечисления должно быть не null."),
                () -> assertTrue(dimensions == CargoDimensions.SMALL || dimensions == CargoDimensions.LARGE,
                        "Значение перечисления должно быть или SMALL, или LARGE"),
                () -> assertDoesNotThrow(() -> CargoDimensions.valueOf(dimensions.name()),
                        "Значения перечисления не должны выбрасывать исключение.")
        );
    }

    @Test
    @Tag("enum")
    @Tag("negative tests")
    @Story("Негативные тесты для валидации перечисления CargoDimensions.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка перечисления CargoDimensions на генерацию исключений.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Enum CargoDimensions. Негативный тест для проверки обработки неверных значений.")
    void enumCargoDimensionsNegativeTests() {
        assertThrows(IllegalArgumentException.class, () -> CargoDimensions.valueOf("MEDIUM"),
                "valueOf() должен выбрасывать исключение при некорректном значении.");
        assertThrows(IllegalArgumentException.class, () -> CargoDimensions.valueOf("small"),
                "valueOf() должен выбрасывать исключение при некорректном регистре.");
        assertThrows(IllegalArgumentException.class, () -> CargoDimensions.valueOf(""),
                "valueOf() должен выбрасывать исключение при пустой строке.");
    }

    @Test
    @Tag("enum")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации перечисления DeliveryWorkload.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка всех элементов перечисления DeliveryWorkload.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Enum DeliveryWorkload. Позитивный тест для проверки элементов перечисления.")
    void enumDeliveryWorkloadPositiveTests() {
        DeliveryWorkload[] workload = DeliveryWorkload.values();

        assertAll(
                () -> assertEquals(4, workload.length,
                        "DeliveryWorkload должен содержать 4 значения."),
                () -> assertEquals("NORMAL", DeliveryWorkload.NORMAL.name(),
                        "name() должен возвращать строку NORMAL."),
                () -> assertEquals("BUSY", DeliveryWorkload.BUSY.name(),
                        "name() должен возвращать строку BUSY."),
                () -> assertEquals("HIGH", DeliveryWorkload.HIGH.name(),
                        "name() должен возвращать строку HIGH."),
                () -> assertEquals("EXTRA_HIGH", DeliveryWorkload.EXTRA_HIGH.name(),
                        "name() должен возвращать строку EXTRA_HIGH"),
                () -> assertEquals(DeliveryWorkload.NORMAL, DeliveryWorkload.valueOf("NORMAL"),
                        "valueOf() должен преобразовать строку NORMAL в enum."),
                () -> assertEquals(DeliveryWorkload.BUSY, DeliveryWorkload.valueOf("BUSY"),
                        "valueOf() должен преобразовать строку BUSY в enum."),
                () -> assertEquals(DeliveryWorkload.HIGH, DeliveryWorkload.valueOf("HIGH"),
                        "valueOf() должен преобразовать строку HIGH в enum."),
                () -> assertEquals(DeliveryWorkload.EXTRA_HIGH, DeliveryWorkload.valueOf("EXTRA_HIGH"),
                        "valueOf() должен преобразовать строку EXTRA_HIGH в enum."),
                () -> assertEquals("NORMAL", DeliveryWorkload.NORMAL.toString(),
                        "toString() должен возвращать строку NORMAL."),
                () -> assertEquals("BUSY", DeliveryWorkload.BUSY.toString(),
                        "toString() должен возвращать строку BUSY."),
                () -> assertEquals("HIGH", DeliveryWorkload.HIGH.toString(),
                        "toString() должен возвращать строку HIGH."),
                () -> assertEquals("EXTRA_HIGH", DeliveryWorkload.EXTRA_HIGH.toString(),
                        "toString() должен возвращать строку EXTRA_HIGH."),
                () -> assertEquals(DeliveryWorkload.NORMAL.name(), DeliveryWorkload.NORMAL.toString(),
                        "name() и toString() должны возвращать строку NORMAL."),
                () -> assertEquals(DeliveryWorkload.BUSY.name(), DeliveryWorkload.BUSY.toString(),
                        "name() и toString() должны возвращать строку BUSY."),
                () -> assertEquals(DeliveryWorkload.HIGH.name(), DeliveryWorkload.HIGH.toString(),
                        "name() и toString() должны возвращать строку HIGH."),
                () -> assertEquals(DeliveryWorkload.EXTRA_HIGH.name(), DeliveryWorkload.EXTRA_HIGH.toString(),
                        "name() и toString() должны возвращать строку EXTRA_HIGH."),
                () -> assertEquals(0, DeliveryWorkload.NORMAL.ordinal(),
                        "NORMAL должен иметь ordinal 0."),
                () -> assertEquals(1, DeliveryWorkload.BUSY.ordinal(),
                        "BUSY должен иметь ordinal 1."),
                () -> assertEquals(2, DeliveryWorkload.HIGH.ordinal(),
                        "HIGH должен иметь ordinal 2."),
                () -> assertEquals(3, DeliveryWorkload.EXTRA_HIGH.ordinal(),
                        "EXTRA_HIGH должен иметь ordinal 3."),
                () -> assertArrayEquals(new DeliveryWorkload[] {
                        DeliveryWorkload.NORMAL,
                        DeliveryWorkload.BUSY,
                        DeliveryWorkload.HIGH,
                        DeliveryWorkload.EXTRA_HIGH},
                        workload,
                        "Массив всех значений должен иметь 4 элемента: NORMAL, BUSY, HIGH и EXTRA_HIGH.")
        );
    }

    @ParameterizedTest
    @Tag("enum")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации перечисления DeliveryWorkload.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Параметризованная проверка всех элементов перечисления DeliveryWorkload.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Enum DeliveryWorkload. Позитивный тест для проверки параметров элементов перечисления.")
    @EnumSource(DeliveryWorkload.class)
    void enumDeliveryWorkloadPositiveParametrizedTests(DeliveryWorkload workload) {
        assertAll(
                () -> assertNotNull(workload, "Значение перечисления должно быть не null."),
                () -> assertTrue(workload == DeliveryWorkload.NORMAL ||
                        workload == DeliveryWorkload.BUSY ||
                        workload == DeliveryWorkload.HIGH ||
                        workload == DeliveryWorkload.EXTRA_HIGH,
                        "Значение перечисления должно быть или NORMAL, или BUSY, или HIGH, или EXTRA_HIGH"),
                () -> assertDoesNotThrow(() -> DeliveryWorkload.valueOf(workload.name()),
                        "Значения перечисления не должны выбрасывать исключение.")
        );
    }

    @Test
    @Tag("enum")
    @Tag("negative tests")
    @Story("Негативные тесты для валидации перечисления DeliveryWorkload.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка перечисления DeliveryWorkload на генерацию исключений.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Enum DeliveryWorkload. Негативный тест для проверки обработки неверных значений.")
    void enumDeliveryWorkloadNegativeTests() {
        assertThrows(IllegalArgumentException.class, () -> DeliveryWorkload.valueOf("LOW"),
                "valueOf() должен выбрасывать исключение при некорректном значении.");
        assertThrows(IllegalArgumentException.class, () -> DeliveryWorkload.valueOf("normal"),
                "valueOf() должен выбрасывать исключение при некорректном регистре.");
        assertThrows(IllegalArgumentException.class, () -> DeliveryWorkload.valueOf(""),
                "valueOf() должен выбрасывать исключение при пустой строке.");
    }

    @Test
    @Tag("validation")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации недопустимых значений параметров расчета стоимости доставки.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректности ввода расстояния доставки с учетом генерации исключения.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Позитивный тест для проверки валидации. Расстояние доставки должно быть больше 0 км.")
    void validationDeliveryDistancePositiveTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DeliveryCostCalculation(0, CargoDimensions.SMALL, true, DeliveryWorkload.NORMAL));
        assertEquals("Delivery distance should be more than 0 km.", exception.getMessage(),
                "Вывод ожидаемого сообщения о некорректном вводе расстояния доставки в 0 км.");
    }

    @Test
    @Tag("validation")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации недопустимых значений параметров расчета стоимости доставки.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректности ввода габаритов груза с учетом генерации исключения.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Позитивный тест для проверки валидации. Габариты груза должны быть определены.")
    void validationCargoDimensionsPositiveTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DeliveryCostCalculation(10, null, false, DeliveryWorkload.BUSY));
        assertEquals("Cargo dimensions should be specified.", exception.getMessage(),
                "Вывод ожидаемого сообщения о необходимости определения габаритов груза.");
    }

    @Test
    @Tag("validation")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации недопустимых значений параметров расчета стоимости доставки.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректности комбинации хрупкости груза и расстояния доставки с учетом генерации исключения.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Позитивный тест для проверки валидации. Хрупкий груз не может доставляться на расстояние более 30 км.")
    void validationDeliveryDistanceCargoFragilityPositiveTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DeliveryCostCalculation(31, CargoDimensions.SMALL, true, DeliveryWorkload.NORMAL));
        assertEquals("Fragile cargo cannot be transported over distance greater than 30 km.", exception.getMessage(),
                "Вывод ожидаемого сообщения о невозможности доставки хрупкого груза на расстояние более 30 км.");
    }

    @Test
    @Tag("validation")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации недопустимых значений параметров расчета стоимости доставки.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректности ввода загруженности сервиса доставки с учетом генерации исключения.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Позитивный тест для проверки валидации. Загруженность сервиса доставки должна быть определена.")
    void validationDeliveryWorkloadPositiveTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new DeliveryCostCalculation(20, CargoDimensions.LARGE, false, null));
        assertEquals("Delivery service workload should be specified.", exception.getMessage(),
                "Вывод ожидаемого сообщения о необходимости определения загруженности сервиса доставки.");
    }

    @ParameterizedTest
    @Tag("calculation")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации расчета стоимости доставки.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректности расчета стоимости доставки с учетом расстояния доставки.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Проверка расчета стоимости доставки в зависимости от расстояния доставки.")
    @CsvSource({
            "1, 400.0",  // (50 (расстояние) + 200 (габариты) + 0 (хрупкость)) * 1 (загруженность) = 250, используется MIN_COST = 400
            "2, 400.0",  // (50 + 200 + 0) * 1 = 250, используется MIN_COST = 400
            "3, 400.0",  // (100 + 200 + 0) * 1 = 300, используется MIN_COST = 400
            "9, 400.0",  // (100 + 200 + 0) * 1 = 300, используется MIN_COST = 400
            "10, 400.0", // (100 + 200 + 0) * 1 = 300, используется MIN_COST = 400
            "11, 400.0", // (200 + 200 + 0) * 1 = 400
            "29, 400.0", // (200 + 200 + 0) * 1 = 400
            "30, 400.0", // (200 + 200 + 0) * 1 = 400
            "31, 500.0", // (300 + 200 + 0) * 1 = 500
    })
    void calculationDeliveryCostViaDistancePositiveTests(int distance, double expectedCost) {
        DeliveryCostCalculation delivery = new DeliveryCostCalculation(
                distance,
                CargoDimensions.LARGE,
                false,
                DeliveryWorkload.NORMAL
        );

        double actualCost = delivery.getTotalDeliveryCost();

        assertAll(
                () -> assertEquals(expectedCost, actualCost, 0.01,
                        () -> String.format("Ожидаемая стоимость доставки равна %.2f для расстояния %d км.", expectedCost, distance)),
                () -> assertTrue(actualCost >= 400.0,
                        "Стоимость не может быть меньше минимальной - 400.")
        );
    }

    @ParameterizedTest
    @Tag("calculation")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации расчета стоимости доставки.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректности расчета стоимости доставки с учетом габаритов груза.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Проверка расчета стоимости доставки в зависимости от габаритов груза.")
    @CsvSource({
            "SMALL, 400", // (300 (расстояние) + 100 (габариты) + 0 (хрупкость)) * 1 (загруженность) = 400
            "LARGE, 500"  // (300 (расстояние) + 200 (габариты) + 0 (хрупкость)) * 1 (загруженность) = 500
    })
    void calculationDeliveryCostViaDimensions(CargoDimensions dimensions, double expectedCost) {
        DeliveryCostCalculation delivery = new DeliveryCostCalculation(
                35,
                dimensions,
                false,
                DeliveryWorkload.NORMAL
        );

        double actualCost = delivery.getTotalDeliveryCost();

        assertAll(
                () -> assertEquals(expectedCost, actualCost, 0.01,
                        () -> String.format("Ожидаемая стоимость доставки равна %.2f для габаритов %s.", expectedCost, dimensions)),
                () -> assertTrue(actualCost >= 400.0,
                        "Стоимость не может быть меньше минимальной - 400.")
        );
    }

    @ParameterizedTest
    @Tag("calculation")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации расчета стоимости доставки.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректности расчета стоимости доставки с учетом хрупкости груза.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Проверка расчета стоимости доставки в зависимости от хрупкости груза.")
    @CsvSource({
            "false, 400",  // (200 (расстояние) + 100 (габариты) + 0 (хрупкость)) * 1 (загруженность) = 300, используется MIN_COST = 400
            "true, 600"  // (200 (расстояние) + 100 (габариты) + 300 (хрупкость)) * 1 (загруженность) = 600
    })
    void calculationDeliveryCostViaFragility(boolean fragility, double expectedCost) {
        DeliveryCostCalculation delivery = new DeliveryCostCalculation(
                30,
                CargoDimensions.SMALL,
                fragility,
                DeliveryWorkload.NORMAL
        );

        double actualCost = delivery.getTotalDeliveryCost();

        assertAll(
                () -> assertEquals(expectedCost, actualCost, 0.01,
                        () -> String.format("Ожидаемая стоимость доставки равна %.2f для хрупкости %s.", expectedCost, fragility)),
                () -> assertTrue(actualCost >= 400.0,
                        "Стоимость не может быть меньше минимальной - 400.")
        );
    }

    @ParameterizedTest
    @Tag("calculation")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации расчета стоимости доставки.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректности расчета стоимости доставки с учетом загруженности сервиса доставки.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Проверка расчета стоимости доставки в зависимости от загруженности сервиса доставки.")
    @CsvSource({
            "NORMAL, 400",     // (200 (расстояние) + 100 (габариты) + 0 (хрупкость)) * 1.0 (загруженность) = 300, используется MIN_COST = 400
            "BUSY, 400",       // (200 (расстояние) + 100 (габариты) + 0 (хрупкость)) * 1.2 (загруженность) = 360, используется MIN_COST = 400
            "HIGH, 420",       // (200 (расстояние) + 100 (габариты) + 0 (хрупкость)) * 1.4 (загруженность) = 420
            "EXTRA_HIGH, 480", // (200 (расстояние) + 100 (габариты) + 0 (хрупкость)) * 1.6 (загруженность) = 480
    })
    void calculationDeliveryCostViaWorkload(DeliveryWorkload workload, double expectedCost) {
        DeliveryCostCalculation delivery = new DeliveryCostCalculation(
                20,
                CargoDimensions.SMALL,
                false,
                workload
        );

        double actualCost = delivery.getTotalDeliveryCost();

        assertAll(
                () -> assertEquals(expectedCost, actualCost, 0.01,
                        () -> String.format("Ожидаемая стоимость доставки равна %.2f для загруженности %s.", expectedCost, workload)),
                () -> assertTrue(actualCost >= 400.0,
                        "Стоимость не может быть меньше минимальной - 400.")
        );
    }

    @Test
    @Tag("getters")
    @Tag("positive tests")
    @Story("Позитивные тесты для валидации геттеров.")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка корректности работы геттеров.")
    @Owner("Veronika Aleksandrova")
    @DisplayName("Проверка геттеров.")
    void gettersSettersPositiveTests() {
        // Создание объекта с начальными значениями: 10, LARGE, false, BUSY.
        // (100 (расстояние) + 200 (габариты) + 0 (хрупкость)) * 1.2 (загруженность) = 360, используется MIN_COST = 400
        DeliveryCostCalculation delivery = new DeliveryCostCalculation(
                10,
                CargoDimensions.LARGE,
                false,
                DeliveryWorkload.BUSY);

        assertAll(
                () -> assertEquals(10, delivery.getDeliveryDistance(),
                        "Ожидаемое расстояние доставки - 10."),
                () -> assertEquals(CargoDimensions.LARGE, delivery.getCargoDimensions(),
                        "Ожидаемые габариты груза - LARGE"),
                () -> assertEquals(false, delivery.isCargoFragility(),
                        "Ожидаемая хрупкость груза - false"),
                () -> assertEquals(DeliveryWorkload.BUSY, delivery.getDeliveryWorkload(),
                        "Ожидаемая загруженность сервиса доставки - BUSY"),
                () -> assertEquals(400, delivery.getTotalDeliveryCost(), 0.01,
                        "Ожидаемая стоимость доставки - 400"),
                () -> assertTrue(delivery.getTotalDeliveryCost() >= 400.0,
                        "Стоимость не может быть меньше минимальной - 400.")
        );
    }
}
