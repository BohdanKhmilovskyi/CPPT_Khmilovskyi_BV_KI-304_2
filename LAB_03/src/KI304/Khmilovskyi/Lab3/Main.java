package KI304.Khmilovskyi.Lab3;

import java.io.IOException;

/**
 * Клас {@code Main} є точкою входу програми до лабораторної роботи №3.
 * <p>
 * Програма демонструє роботу розширеного кондиціонера {@link AdvancedConditioner},
 * який підтримує:
 * <ul>
 *     <li>увімкнення та вимкнення пристрою;</li>
 *     <li>встановлення цільової температури;</li>
 *     <li>режим енергозбереження Eco;</li>
 *     <li>самодіагностику;</li>
 *     <li>планування автоматичного вимкнення.</li>
 * </ul>
 *
 * <p>Створення об’єкта виконується у блоці try-with-resources, оскільки
 * {@code AdvancedConditioner} реалізує інтерфейс {@code AutoCloseable}.
 * Це гарантує автоматичне вивільнення ресурсів та коректне завершення роботи.</p>
 *
 * <p>У випадку помилок створення об’єкта кондиціонера може бути згенеровано
 * виняток {@link IOException}, який обробляється у відповідному блоці {@code catch}.</p>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304 – Лабораторна робота №3
 * @version 1.0
 */
public class Main {

    /**
     * Точка входу в програму.
     * <p>
     * Метод послідовно демонструє можливості класу {@link AdvancedConditioner},
     * включаючи:
     * <ol>
     *     <li>Увімкнення кондиціонера;</li>
     *     <li>Встановлення бажаної температури;</li>
     *     <li>Активацію Eco режиму;</li>
     *     <li>Виконання самотестування;</li>
     *     <li>Планування автоматичного вимкнення.</li>
     * </ol>
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦІЯ КЛІМАТ КОНТРОЛЮ ===");

        try (AdvancedConditioner ac = new AdvancedConditioner()) {
            System.out.println("\n1. Увімкнення кондиціонера:");
            ac.turnOn();
            ac.showStatus();

            System.out.println("\n2. Встановлення цільової температури на 20°C:");
            ac.setTargetTemperature(20);
            ac.showStatus();

            System.out.println("\n3. Включення Eco режиму:");
            ac.enableEcoMode();
            ac.showStatus();

            System.out.println("\n4. Виконання самотестування кондиціонера:");
            ac.performSelfTest();

            System.out.println("\n5. Планування вимкнення через 1 хв:");
            ac.scheduleShutdown(1);

            System.out.println("\n=== ДЕМОНСТРАЦІЯ ЗАВЕРШЕНА ===");

        } catch (IOException e) {
            System.out.println("Помилка при створенні кондиціонера: " + e.getMessage());
        }
    }
}
