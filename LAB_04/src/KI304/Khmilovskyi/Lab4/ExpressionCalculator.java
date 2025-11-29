package KI304.Khmilovskyi.Lab4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Клас {@code ExpressionCalculator} призначений для обчислення математичного
 * виразу:
 *
 * <pre>
 *     y = 1 / sin(x)
 * </pre>
 *
 * <p>Всі результати обчислень (а також повідомлення про помилки) автоматично
 * записуються у текстовий файл {@code result.txt}. Це реалізовано для зручності
 * відстеження виконання програми та аналізу можливих виключень.</p>
 *
 * <p>Особливості реалізації:</p>
 * <ul>
 *     <li>використання механізму try-with-resources через {@link AutoCloseable};</li>
 *     <li>логування всіх операцій у файл;</li>
 *     <li>коректна обробка виключень (зокрема ділення на нуль, якщо sin(x) = 0);</li>
 * </ul>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.1
 */
public class ExpressionCalculator implements AutoCloseable {

    /** Потік для запису у файл result.txt. */
    private PrintWriter writer;

    /**
     * Конструктор відкриває файл {@code result.txt} для логування.
     * Якщо файл не існує, він буде створений.
     *
     * @throws IOException якщо файл неможливо створити або відкрити
     */
    public ExpressionCalculator() throws IOException {
        writer = new PrintWriter(new FileWriter("result.txt", true));
        log("=== Нова сесія обчислень ===");
    }

    /**
     * Обчислює значення математичного виразу y = 1 / sin(x).
     *
     * @param x значення аргументу (у радіанах)
     * @return числовий результат
     *
     * @throws ArithmeticException якщо sin(x) = 0 та відбувається ділення на нуль
     * @throws RuntimeException для інших непередбачуваних помилок
     */
    public double calculate(double x) {
        try {
            double sinX = Math.sin(x);

            if (Math.abs(sinX) < 1e-10) {
                throw new ArithmeticException("Ділення на нуль! sin(x) = 0");
            }

            double result = 1.0 / sinX;
            log("x = " + x + "; y = " + result);
            return result;

        } catch (ArithmeticException e) {
            log("Помилка обчислення при x = " + x + ": " + e.getMessage());
            throw e;

        } catch (Exception e) {
            log("Невідома помилка при x = " + x + ": " + e.getMessage());
            throw e;
        }
    }

    /**
     * Допоміжний метод, що записує повідомлення у файл журналу.
     *
     * @param message текст для запису у файл
     */
    private void log(String message) {
        writer.println(message);
        writer.flush();
    }

    /**
     * Закриває файл журналу після завершення роботи обчислювача.
     * <p>
     * Метод викликається автоматично конструкцією try-with-resources.
     */
    @Override
    public void close() {
        log("=== Завершення обчислень ===");
        writer.println();
        writer.flush();
        writer.close();
    }
}
