package KI304.Khmilovskyi.Lab3;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Клас {@code AdvancedConditioner} є розширеною версією базового {@link Conditioner}.
 * <p>
 * Він реалізує інтерфейс {@link ClimateDevice} і додає новий функціонал:
 * <ul>
 *     <li>встановлення цільової температури;</li>
 *     <li>увімкнення та вимкнення Eco режиму;</li>
 *     <li>планування автоматичного вимкнення;</li>
 *     <li>розширений режим самодіагностики;</li>
 *     <li>виведення розширеного статусу роботи;</li>
 * </ul>
 *
 * <p>Клас також реалізує {@link AutoCloseable} для автоматичного очищення ресурсів,
 * включно з завершенням роботи планувальника задач і закриттям лог-файлу.</p>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.0
 */
public class AdvancedConditioner extends Conditioner implements ClimateDevice, AutoCloseable {

    /** Цільова температура, яку намагається підтримувати кондиціонер. */
    private int targetTemperature;

    /** Прапорець увімкнення Eco режиму. */
    private boolean ecoMode;

    /**
     * Планувальник задач для виконання автоматичного вимкнення.
     * Працює в окремому потоці.
     */
    private final ScheduledExecutorService scheduler;

    /**
     * Створює новий {@code AdvancedConditioner} з налаштуваннями за замовчуванням.
     *
     * @throws IOException якщо виникає помилка при відкритті файлу журналу
     */
    public AdvancedConditioner() throws IOException {
        super();
        this.targetTemperature = 22;
        this.ecoMode = false;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        log("AdvancedConditioner створено (Lab3).");
    }

    /**
     * Створює новий {@code AdvancedConditioner} із заданою цільовою температурою.
     *
     * @param targetTemperature початкове значення температури
     * @throws IOException якщо не вдалося відкрити файл журналу
     */
    public AdvancedConditioner(int targetTemperature) throws IOException {
        super();
        this.targetTemperature = targetTemperature;
        this.ecoMode = false;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
        log("AdvancedConditioner створено з targetTemperature=" + targetTemperature);
    }

    /**
     * Встановлює цільову температуру кліматичного пристрою.
     * <p>
     * Діапазон допустимих значень: 16–30°C.
     *
     * @param temp нове значення температури
     */
    @Override
    public void setTargetTemperature(int temp) {
        if (temp < 16 || temp > 30) {
            System.out.println("Температура повинна бути в межах 16–30°C!");
            log("Спроба встановити некоректну температуру: " + temp);
            return;
        }
        this.targetTemperature = temp;
        super.setTemperature(temp);
        log("Цільову температуру встановлено: " + temp);
    }

    /**
     * Увімкнення Eco режиму для економії енергії.
     * <p>
     * Eco режим підвищує цільову температуру на 1°C.
     */
    @Override
    public void enableEcoMode() {
        this.ecoMode = true;
        log("Еко-режим увімкнено");
        setTargetTemperature(Math.min(30, this.targetTemperature + 1));
    }

    /**
     * Вимикає Eco режим.
     */
    @Override
    public void disableEcoMode() {
        this.ecoMode = false;
        log("Еко-режим вимкнено");
    }

    /**
     * Планує автоматичне вимкнення кондиціонера через задану кількість хвилин.
     * <p>
     * Якщо {@code minutes <= 0}, то попередні заплановані задачі скасовуються.
     *
     * @param minutes час у хвилинах
     */
    @Override
    public void scheduleShutdown(int minutes) {
        if (minutes <= 0) {
            scheduler.shutdownNow();
            log("План вимкнення скасовано");
            System.out.println("План вимкнення скасовано");
            return;
        }

        scheduler.schedule(() -> {
            AdvancedConditioner.this.turnOff();
            AdvancedConditioner.this.log("Автоматичне вимкнення через " + minutes + " хв.");
        }, minutes, TimeUnit.MINUTES);

        log("Заплановано автоматичне вимкнення через " + minutes + " хв.");
        System.out.println("Заплановано автоматичне вимкнення через " + minutes + " хв.");
    }

    /**
     * Виконує повний цикл самоперевірки пристрою:
     * <ol>
     *     <li>увімкнення мотора;</li>
     *     <li>імітаційна діагностика;</li>
     *     <li>вимкнення мотора;</li>
     * </ol>
     */
    @Override
    public void performSelfTest() {
        log("Початок самоперевірки");
        System.out.println("Виконується самоперевірка...");

        this.turnOn();
        // Можна додати складнішу імітацію логіки
        this.turnOff();

        log("Самоперевірку завершено");
        System.out.println("Самоперевірка завершена.");
    }

    /**
     * Виводить розширений статус системи:
     * <ul>
     *     <li>стан мотору;</li>
     *     <li>поточна температура;</li>
     *     <li>стан фільтру;</li>
     *     <li>цільова температура;</li>
     *     <li>Eco режим;</li>
     * </ul>
     */
    @Override
    public void showStatus() {
        super.showStatus();
        System.out.println("Цільова температура: " + targetTemperature + "°C");
        System.out.println("Еко-режим: " + (ecoMode ? "увімкнено" : "вимкнено"));
        log("AdvancedConditioner: показано розширений статус");
    }

    /**
     * Коректно завершує роботу кондиціонера:
     * <ul>
     *     <li>зупиняє планувальник;</li>
     *     <li>закриває лог-файл суперкласу;</li>
     * </ul>
     */
    @Override
    public void close() {
        log("Завершення роботи AdvancedConditioner");
        scheduler.shutdownNow();
        super.close();
    }
}
