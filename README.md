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
task12-delivery-cost-calculation/
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

# Task 14. Page Title Verification with Selenium Web Driver
https://bonigarcia.dev/selenium-webdriver-java/index.html

## Технологии
| Технология | Версия | Назначение             |
|------------|--------|------------------------|
| Java       | 17.0.1 | Основной язык          |
| JUnit 5    | 5.10.0 | Тестирование           |
| Gradle     | 8.5    | Сборка                 |
| Selenium   | 4.38.0 | Автоматизация браузера |
| Allure     | 2.24.0 | Отчеты                 |

## Описание
Базовый тест для проверки заголовка веб-страницы с использованием Selenium WebDriver:
- Настройку Selenium WebDriver для работы с Chrome
- Открытие веб-страницы и проверку заголовка
- Использование JUnit 5 аннотаций для организации тестов
- Правильное управление жизненным циклом WebDriver

## Структура проекта
```
task14-selenium-basics/
├── src/test/java/com/aleksandrovaveronika/task14/selenium/
│   └── SeleniumTitleTest.java         # Тест проверки заголовка страницы
├── build.gradle                       # Конфигурация сборки с зависимостями Selenium
└── README.md                          # Эта документация
```

## Тестирование
Тест демонстрирует:
- Заголовок страницы: проверка корректности title веб-страницы
- Открытие страницы: успешная загрузка целевой страницы
- Управление браузером: правильное открытие и закрытие браузера
- Настройка WebDriver: использование Setup/Teardown

# Task 15. Web Form Verification with Selenium Web Driver
https://bonigarcia.dev/selenium-webdriver-java/web-form.html

## Технологии
| Технология | Версия | Назначение             |
|------------|--------|------------------------|
| Java       | 17.0.1 | Основной язык          |
| JUnit 5    | 5.10.0 | Тестирование           |
| Gradle     | 8.5    | Сборка                 |
| Selenium   | 4.38.0 | Автоматизация браузера |
| Allure     | 2.24.0 | Отчеты                 |

## Описание
Проект включает:
- Поиск элементов с использованием CSS и XPath локаторов
- Проверка всех типов полей формы:
  1. Icon-link - Проверка перехода по ссылке
  2. Headers - Заголовки страницы (h1, h5)
  3. Text input - Текстовое поле ввода
  4. Password - Поле для пароля
  5. Textarea - Многострочное текстовое поле
  6. Disabled input - Неактивное поле ввода
  7. Readonly input - Поле только для чтения
  8. Dropdown (select) - Выпадающий список
  9. Dropdown (datalist) - Список с автозаполнением
  10. File input - Загрузка файлов
  11. Checkbox - Флажки
  12. Radio - Радио-кнопки
  13. Color picker - Выбор цвета
  14. Date picker - Выбор даты
  15. Example range - Ползунок
  16. Submit - Кнопка отправки формы
  17. Return to index - Ссылка возврата

## Структура проекта
```
task15-selenium-web-form/
├── src/test/java/com/aleksandrovaveronika/task15/locators/
│   └── WebFormLocatorsTests.java      # Основной класс тестов веб-формы
├── build.gradle                       # Конфигурация сборки с зависимостями Selenium
└── README.md                          # Эта документация
```

## Тестирование
Комплексные UI-тесты для веб-формы с использованием Selenium WebDriver:
- Параметризованное тестирование для различных сценариев
- Проверка функциональности отправки формы
- Навигация между страницами