package KI304.Khmilovskyi.Lab5;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Клас {@code ExpressionCalculator} призначений для обчислення значень
 * математичного виразу:
 *
 * <pre>
 *     y = 1 / sin(x)
 * </pre>
 *
 * <p>
 * Реалізовано:
 * <ul>
 *     <li>обробку виключення при діленні на нуль (коли {@code sin(x) = 0});</li>
 *     <li>логування результатів та помилок у текстовий файл {@code result.txt};</li>
 *     <li>підтримку інтерфейсу {@link AutoCloseable} для коректного закриття ресурсу.</li>
 * </ul>
 *
 * <p>
 * Клас використовується у лабораторних роботах для демонстрації:
 * обробки виключень, роботи з файлами та конструкції try-with-resources.
 * </p>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.1
 */
public class ExpressionCalculator implements AutoCloseable {

    /** Потік для запису журналу обчислень у файл {@code result.txt}. */
    private PrintWriter writer;

    /**
     * Створює новий екземпляр {@code ExpressionCalculator} та відкриває файл
     * {@code result.txt} для дописування.
     *
     * @throws IOException якщо файл неможливо створити або відкрити
     */
    public ExpressionCalculator() throws IOException {
        writer = new PrintWriter(new FileWriter("result.txt", true));
        log("=== Нова сесія обчислень ===");
    }

    /**
     * Обчислює значення виразу {@code y = 1 / sin(x)}.
     *
     * @param x значення аргументу (у радіанах)
     * @return результат обчислення
     *
     * @throws ArithmeticException якщо {@code sin(x) = 0} і виникає ділення на нуль
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
     * Записує повідомлення в лог-файл.
     *
     * @param message текстове повідомлення для журналу
     */
    private void log(String message) {
        writer.println(message);
        writer.flush();
    }

    /**
     * Закриває лог-файл після завершення роботи.
     * <p>
     * Метод викликається автоматично при використанні
     * {@code ExpressionCalculator} у конструкції try-with-resources.
     */
    @Override
    public void close() {
        log("=== Завершення обчислень ===");
        writer.println(); // порожній рядок для відділення сесій
        writer.flush();
        writer.close();
    }
}
