package KI304.Khmilovskyi.Lab3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Абстрактний клас {@code Conditioner} моделює роботу базового кондиціонера.
 * <p>
 * До складу кондиціонера входять:
 * <ul>
 *     <li>{@link Motor} — двигун кондиціонера;</li>
 *     <li>{@link Remote} — пульт для керування температурою;</li>
 *     <li>{@link Filter} — фільтр очищення повітря;</li>
 * </ul>
 *
 * <p>Клас виконує базові функції кондиціонера:</p>
 * <ul>
 *     <li>увімкнення/вимкнення;</li>
 *     <li>встановлення температури;</li>
 *     <li>зміна температури;</li>
 *     <li>очищення фільтра;</li>
 *     <li>перегляд статусу;</li>
 * </ul>
 *
 * <p>Клас є абстрактним, оскільки містить абстрактний метод
 * {@link #performSelfTest()}, який повинен бути реалізований у підкласах.</p>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.0
 */
public abstract class Conditioner {

    /** Двигун кондиціонера. */
    private Motor motor;

    /** Пульт керування температурою. */
    private Remote remote;

    /** Повітряний фільтр. */
    private Filter filter;

    /** Засіб для логування подій. */
    private PrintWriter logWriter;

    /**
     * Створює новий кондиціонер з компонентами за замовчуванням.
     *
     * @throws IOException якщо не вдається відкрити лог-файл
     */
    public Conditioner() throws IOException {
        this.motor = new Motor();
        this.remote = new Remote();
        this.filter = new Filter();
        this.logWriter = new PrintWriter(new FileWriter("log.txt", true));
        log("Кондиціонер створено (конструктор за замовчуванням)");
    }

    /**
     * Створює кондиціонер із вказаними компонентами.
     *
     * @param motor двигун
     * @param remote пульт
     * @param filter фільтр
     * @throws IOException якщо не вдається відкрити лог-файл
     */
    public Conditioner(Motor motor, Remote remote, Filter filter) throws IOException {
        this.motor = motor;
        this.remote = remote;
        this.filter = filter;
        this.logWriter = new PrintWriter(new FileWriter("log.txt", true));
        log("Кондиціонер створено (конструктор з параметрами)");
    }

    /**
     * Увімкнення кондиціонера.
     */
    public void turnOn() {
        motor.start();
        log("Кондиціонер увімкнено");
    }

    /**
     * Вимкнення кондиціонера.
     */
    public void turnOff() {
        motor.stop();
        log("Кондиціонер вимкнено");
    }

    /**
     * Встановлює нову температуру.
     *
     * @param temp значення температури
     */
    public void setTemperature(int temp) {
        remote.setTemperature(temp);
        log("Встановлено температуру: " + temp);
    }

    /** Збільшує температуру на 1°C. */
    public void increaseTemp() {
        remote.increaseTemp();
        log("Температура збільшена");
    }

    /** Зменшує температуру на 1°C. */
    public void decreaseTemp() {
        remote.decreaseTemp();
        log("Температура зменшена");
    }

    /**
     * Очищає фільтр кондиціонера.
     */
    public void cleanFilter() {
        filter.clean();
        log("Фільтр очищено");
    }

    /**
     * Виводить інформацію про поточний стан кондиціонера.
     */
    public void showStatus() {
        String status = "Стан: МОТОР = " + motor.isRunning() +
                "; ТЕМПЕРАТУРА = " + remote.getTemperature() +
                "; ФІЛЬТР = " + filter.getStatus();
        System.out.println(status);
        log("Отримано стан: " + status);
    }

    /**
     * Увімкнення режиму охолодження.
     */
    public void enableCooling() {
        motor.setMode("Охолодження");
        log("Режим встановлено: Охолодження");
    }

    /**
     * Увімкнення режиму обігріву.
     */
    public void enableHeating() {
        motor.setMode("Обігрів");
        log("Режим встановлено: Обігрів");
    }

    /**
     * Закриває лог та завершує роботу кондиціонера.
     */
    public void close() {
        log("Роботу з кондиціонером завершено");
        logWriter.close();
    }

    /**
     * Записує повідомлення у лог-файл.
     *
     * @param message текст повідомлення
     */
    protected void log(String message) {
        logWriter.println(message);
        logWriter.flush();
    }

    /**
     * Абстрактний метод самоперевірки.
     * <p>
     * Має бути реалізований у підкласах.
     */
    public abstract void performSelfTest();

    // ======================== Внутрішні статичні класи ========================

    /**
     * Клас {@code Motor} моделює роботу двигуна кондиціонера.
     */
    public static class Motor {
        private boolean running;
        private String mode;

        public void start() { running = true; }
        public void stop() { running = false; }
        public boolean isRunning() { return running; }
        public void setMode(String mode) { this.mode = mode; }
        public String getMode() { return mode; }
    }

    /**
     * Клас {@code Remote} — пульт керування температурою.
     */
    public static class Remote {

        private int temperature = 22;

        public void setTemperature(int temp) { this.temperature = temp; }
        public void increaseTemp() { this.temperature++; }
        public void decreaseTemp() { this.temperature--; }
        public int getTemperature() { return temperature; }
    }

    /**
     * Клас {@code Filter} — моделює стан повітряного фільтра.
     */
    public static class Filter {

        private boolean clean = true;

        public void clean() { clean = true; }
        public void use() { clean = false; }
        public String getStatus() { return clean ? "чистий" : "брудний"; }
    }
}
