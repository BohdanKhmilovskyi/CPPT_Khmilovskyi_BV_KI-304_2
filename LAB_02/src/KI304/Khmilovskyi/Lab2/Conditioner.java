package KI304.Khmilovskyi.Lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Клас {@code Conditioner} моделює роботу кондиціонера.
 * Він має внутрішні складові: мотор, пульт та фільтр.
 * Програма веде протокол дій у файл log.txt.
 *
 * <p><b>Призначення:</b> ознайомлення з принципами ООП,
 * використання пакетів, конструкторів, методів та протоколювання.</p>
 *
 * @author
 * @version 1.0
 */
public class Conditioner {
    private Motor motor;
    private Remote remote;
    private Filter filter;
    private PrintWriter logWriter;

    /**
     * Конструктор за замовчуванням.
     * Створює кондиціонер із базовими складовими.
     */
    public Conditioner() throws IOException {
        this.motor = new Motor();
        this.remote = new Remote();
        this.filter = new Filter();
        this.logWriter = new PrintWriter(new FileWriter("log.txt", true));
        log("Кондиціонер створено (конструктор за замовчуванням)");
    }

    /**
     * Конструктор з параметрами.
     *
     * @param motor мотор
     * @param remote пульт
     * @param filter фільтр
     */
    public Conditioner(Motor motor, Remote remote, Filter filter) throws IOException {
        this.motor = motor;
        this.remote = remote;
        this.filter = filter;
        this.logWriter = new PrintWriter(new FileWriter("log.txt", true));
        log("Кондиціонер створено (конструктор з параметрами)");
    }

    // --- Методи кондиціонера ---
    public void turnOn() {
        motor.start();
        log("Кондиціонер увімкнено");
    }

    public void turnOff() {
        motor.stop();
        log("Кондиціонер вимкнено");
    }

    public void setTemperature(int temp) {
        remote.setTemperature(temp);
        log("Встановлено температуру: " + temp);
    }

    public void increaseTemp() {
        remote.increaseTemp();
        log("Температура збільшена");
    }

    public void decreaseTemp() {
        remote.decreaseTemp();
        log("Температура зменшена");
    }

    public void cleanFilter() {
        filter.clean();
        log("Фільтр очищено");
    }

    public void showStatus() {
        String status = "Стан: мотор=" + motor.isRunning() +
                ", температура=" + remote.getTemperature() +
                ", фільтр=" + filter.getStatus();
        System.out.println(status);
        log("Отримано стан: " + status);
    }

    public void enableCooling() {
        motor.setMode("Охолодження");
        log("Режим встановлено: охолодження");
    }

    public void enableHeating() {
        motor.setMode("Обігрів");
        log("Режим встановлено: обігрів");
    }

    public void close() {
        log("Роботу з кондиціонером завершено");
        logWriter.close();
    }

    // --- Внутрішній метод для логування ---
    private void log(String message) {
        logWriter.println(message);
        logWriter.flush();
    }

    // --- Допоміжні внутрішні класи ---
    public static class Motor {
        private boolean running;
        private String mode;

        public void start() { running = true; }
        public void stop() { running = false; }
        public boolean isRunning() { return running; }
        public void setMode(String mode) { this.mode = mode; }
        public String getMode() { return mode; }
    }

    public static class Remote {
        private int temperature = 22;

        public void setTemperature(int temp) { this.temperature = temp; }
        public void increaseTemp() { this.temperature++; }
        public void decreaseTemp() { this.temperature--; }
        public int getTemperature() { return temperature; }
    }

    public static class Filter {
        private boolean clean = true;

        public void clean() { clean = true; }
        public void use() { clean = false; }
        public String getStatus() { return clean ? "чистий" : "брудний"; }
    }
}
