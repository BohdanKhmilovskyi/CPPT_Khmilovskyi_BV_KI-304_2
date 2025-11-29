package KI304.Khmilovskyi.Lab5;

import java.io.*;

/**
 * Клас {@code Main} є точкою входу до програми лабораторної роботи №5.
 * <p>
 * Програма демонструє:
 * <ul>
 *     <li>використання класу {@link ExpressionCalculator} для обчислення виразу {@code y = 1 / sin(x)};</li>
 *     <li>чтення даних з текстового файлу за допомогою {@link FileHandler};</li>
 *     <li>запис службового тексту у новий текстовий файл;</li>
 *     <li>демонстрацію запису результатів у двійковий файл за допомогою {@link DataOutputStream}.</li>
 * </ul>
 * <p>
 * Обчислення виконуються для фіксованого набору значень {@code x}, визначених у масиві:
 * {@code {0.5, 1.0, π/4, π/2, π}}.
 *
 * <p>У випадку виникнення помилок роботи з файлами перехоплюється {@link IOException}.</p>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.0
 */
public class Main {

    /**
     * Точка входу в програму.
     * <p>
     * Послідовність дій:
     * <ol>
     *     <li>створюється об'єкт {@link FileHandler} і виводиться вміст файлу {@code result.txt};</li>
     *     <li>створюється {@link ExpressionCalculator} для обчислення виразу {@code y = 1 / sin(x)};</li>
     *     <li>для кожного значення {@code x} виконується обчислення та вивід результату у консоль;</li>
     *     <li>створюється текстовий файл {@code results_text.txt} з заголовком;</li>
     *     <li>формується двійковий файл {@code results_binary.bin}, куди послідовно
     *         записуються пари значень (x, y).</li>
     * </ol>
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {

        FileHandler fh = new FileHandler();
        fh.readFromTextFile("result.txt");

        try (ExpressionCalculator calc = new ExpressionCalculator()) {

            double[] values = {0.5, 1.0, Math.PI / 4, Math.PI / 2, Math.PI};
            for (double x : values) {
                try {
                    double y = calc.calculate(x);
                    System.out.println("x = " + x + "; y = " + y);
                } catch (Exception e) {
                    System.out.println("Помилка при x = " + x + ": " + e.getMessage());
                }
            }

            // Запис службового тексту у новий текстовий файл
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("results_text.txt"))) {
                bw.write("Результати обчислень з файлу result.txt:\n");
                bw.flush();
                System.out.println("Результати записано у текстовий файл.");
            }

            // Демонстрація запису у двійковий файл
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("results_binary.bin"))) {
                for (double x : values) {
                    dos.writeDouble(x);
                    dos.writeDouble(1.0 / Math.sin(x));
                }
                System.out.println("Результати записано у двійковий файл.");
            }

        } catch (IOException e) {
            System.out.println("Помилка при роботі з файлами: " + e.getMessage());
        }
    }
}
