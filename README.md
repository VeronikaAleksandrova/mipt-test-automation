# Task 12. Delivery Cost Calculation System

## Технологии
| Технология | Версия | Назначение    |
|------------|--------|---------------|
| Java       | 17.0.1 | Основной язык |
| JUnit 5    | 5.10.0 | Тестирование  |
| Gradle     | 7.6.4  | Сборка        |
| Allure     | 2.24.0 | Отчеты        |

## Описание
Система расчета стоимости доставки с учетом:
- Расстояния доставки
- Габаритов груза
- Хрупкости груза
- Загруженности сервиса

## Структура проекта
```
delivery-cost-calculation/
├── src/main/java/com/aleksandrovaveronika/task12/delivery/
│   ├── DeliveryCostCalculation.java    # Основная логика расчета
│   ├── DeliveryDataEntry.java          # Консольный ввод данных
│   ├── CargoDimensions.java            # Enum габаритов
│   └── DeliveryWorkload.java           # Enum загруженности
├── src/test/java/com/aleksandrovaveronika/task12/delivery/
│   └── DeliveryCostCalculationTest.java # Юнит-тесты
├── build.gradle                        # Конфигурация сборки
└── README.md                           # Эта документация
```

## Быстрый старт
```bash
# Клонировать репозиторий
git clone https://github.com/VeronikaAleksandrova/mipt-test-automation.git
# Запустить тесты
./gradlew test
# Сгенерировать Allure отчет
./gradlew allureReport
# Открыть отчет в браузере
./gradlew allureServe
```

## Примеры использования
```java
// Пример создания объекта расчета
DeliveryCostCalculation delivery = new DeliveryCostCalculation(
    15,                     // расстояние 15 км
    CargoDimensions.LARGE,  // крупный груз
    true,                   // хрупкий
    DeliveryWorkload.HIGH   // высокая загруженность
);

double cost = delivery.getTotalDeliveryCost();
System.out.println("Стоимость доставки: " + cost);
```

## Тестирование
Проект включает:
- 100% покрытие логики расчета
- Параметризованные тесты
- Тесты валидации
- Allure отчет с аннотациями

# Task 13. Course Management System with Mockito Testing

## Технологии
| Технология | Версия | Назначение    |
|------------|--------|---------------|
| Java       | 17.0.1 | Основной язык |
| JUnit 5    | 5.10.0 | Тестирование  |
| Gradle     | 7.6.4  | Сборка        |
| Allure     | 2.24.0 | Отчеты        |
|Mockito     | 5.3.1  | Мокирование   |

## Описание
Система управления курсом студентов с функционалом:
- Получение списка студентов
- Расчет средней оценки студентов
- Поиск студентов по имени
- Мокирование зависимостей с использованием Mockito
- Тестирование с использованием Spy объектов

## Структура проекта
```
task13-course-management/
├── src/main/java/com/aleksandrovaveronika/task13/course/
│   ├── Student.java                    # Класс студента (сущность)
│   ├── CourseRepository.java           # Репозиторий для работы с данными
│   ├── CourseService.java              # Сервис с бизнес-логикой
│   └── StudentNotFoundException.java   # Пользовательское исключение
├── src/test/java/com/aleksandrovaveronika/task13/course/
│   └── CourseServiceTests.java         # Тесты с Mockito и Spy
├── build.gradle                        # Конфигурация сборки
└── README.md                           # Эта документация
```

## Быстрый старт
```bash
# Клонировать репозиторий
git clone https://github.com/VeronikaAleksandrova/mipt-test-automation.git
# Запустить тесты
./gradlew test
# Сгенерировать Allure отчет
./gradlew allureReport
# Открыть отчет в браузере
./gradlew allureServe
```

## Тестирование
Проект включает:
- Тесты без Mockito - тестирование реальных объектов
- Тесты с Mockito - мокирование зависимостей
- Тесты с исключениями - проверка обработки ошибок
- Тесты со Spy объектами - частичное мокирование
