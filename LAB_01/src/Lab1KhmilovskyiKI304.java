import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Лабораторна робота №1.
 * <p>
 * Програма будує квадратну матрицю розміром n×n, в якій символами-заповнювачами
 * позначаються верхній і нижній трикутники, що утворюються при розділенні квадрата
 * діагоналями. Лівий і правий трикутники залишаються порожніми (заповнені пробілами).
 * <p>
 * Основний алгоритм:
 * <ul>
 *   <li>Зчитати з клавіатури розмір квадратної матриці (n).</li>
 *   <li>Зчитати один символ-заповнювач. Якщо введено кілька символів – програма завершує роботу.</li>
 *   <li>Сформувати квадратний зубчастий масив згідно з умовою варіанта.</li>
 *   <li>Вивести масив у консоль для візуального перегляду.</li>
 *   <li>Записати результат у текстовий файл <b>output.txt</b>.</li>
 * </ul>
 * <p>
 * Програма реалізована на мові Java з використанням таких класів:
 * <ul>
 *   <li>{@link java.util.Scanner} – для зчитування даних з клавіатури.</li>
 *   <li>{@link java.io.PrintWriter} – для зручного запису матриці у файл.</li>
 * </ul>
 *
 * <h2>Приклад роботи:</h2>
 * Якщо ввести <b>n = 5</b> та символ <b>*</b>, то результат виглядатиме так:
 * <pre>
 * * * * * *
 *   * * *
 *     *
 *   * * *
 * * * * * *
 * </pre>
 *
 * <p><b>Призначення:</b> ознайомлення з роботою масивів та організацією
 * введення/виведення у Java.</p>
 *
 * @author Khmilovskyi KI-304
 */
public class Lab1KhmilovskyiKI304 {

    /**
     * Конструктор за замовчуванням.
     * Ініціалізує об'єкт класу без параметрів.
     */
    public Lab1KhmilovskyiKI304() {
    }

    /**
     * Головний метод програми.
     * Зчитує розмір матриці та символ-заповнювач, формує зубчастий масив,
     * виводить його на консоль та записує у файл output.txt.
     *
     * @param args аргументи командного рядка (не використовуються у цій програмі)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть розмір квадратної матриці (n): ");
            int n = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Введіть один символ-заповнювач: ");
            String input = scanner.nextLine();

            if (input.length() != 1) {
                System.out.println("Помилка: потрібно ввести рівно один символ!");
                return;
            }

            char fill = input.charAt(0);

            char[][] array = new char[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((i <= j && i + j < n) || (i >= j && i + j >= n - 1)) {
                        array[i][j] = fill;
                    } else {
                        array[i][j] = ' ';
                    }
                }
            }

            System.out.println("Результат:");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(array[i][j] + " ");
                }
                System.out.println();
            }

            try (PrintWriter writer = new PrintWriter("output.txt")) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        writer.print(array[i][j] + " ");
                    }
                    writer.println();
                }
            }

            System.out.println("Результат записано у файл output.txt");

        } catch (Exception e) {
            System.out.println("Сталася помилка введення: " + e.getMessage());
        }
    }
}
