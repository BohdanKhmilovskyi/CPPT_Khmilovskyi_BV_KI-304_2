package KI304.Khmilovskyi.Lab4;

import java.io.IOException;
import java.util.Scanner;

/**
 * Клас {@code Main} є точкою входу до програми лабораторної роботи №4.
 * <p>
 * Програма демонструє використання класу {@link ExpressionCalculator} для
 * обчислення математичного виразу:
 *
 * <pre>
 *     y = 1 / sin(x)
 * </pre>
 *
 * Користувач вводить значення аргументу {@code x} у радіанах, після чого
 * програма:
 * <ol>
 *     <li>виконує обчислення виразу;</li>
 *     <li>виводить результат на екран;</li>
 *     <li>записує інформацію у файл;</li>
 *     <li>обробляє можливі виключення (помилки обчислення або вводу);</li>
 * </ol>
 *
 * <p>Клас використовує конструкцію try-with-resources, щоб забезпечити
 * автоматичне закриття ресурсу {@link ExpressionCalculator} після завершення роботи.</p>
 *
 * <p>Передбачено обробку:</p>
 * <ul>
 *     <li>{@link java.util.InputMismatchException} — якщо введено некоректні дані;</li>
 *     <li>{@link ArithmeticException} — якщо sin(x) = 0 і виникає ділення на нуль;</li>
 *     <li>{@link IOException} — якщо неможливо створити або відкрити файл;</li>
 *     <li>{@link Exception} — для інших непередбачуваних помилок;</li>
 * </ul>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.1
 */
public class Main {

    /**
     * Основний метод програми. Здійснює:
     * <ul>
     *     <li>зчитування значення {@code x} від користувача;</li>
     *     <li>передачу його у {@link ExpressionCalculator#calculate(double)};</li>
     *     <li>виведення результату або повідомлення про помилку;</li>
     * </ul>
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             ExpressionCalculator calc = new ExpressionCalculator()) {

            System.out.print("Введіть значення x (у радіанах): ");
            double x = scanner.nextDouble();

            try {
                double y = calc.calculate(x);
                System.out.printf("Результат: y = %.5f%n", y);
            } catch (ArithmeticException e) {
                System.out.println("Помилка обчислення: " + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("Помилка створення файлу: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        }
    }
}
