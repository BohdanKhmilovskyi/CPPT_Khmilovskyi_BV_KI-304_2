package KI304.Khmilovskyi.Lab5;

import java.io.*;

/**
 * Клас {@code FileHandler} інкапсулює операції читання та запису даних
 * у текстові та двійкові файли.
 * <p>
 * Реалізує допоміжні методи для:
 * <ul>
 *     <li>запису рядків у текстовий файл;</li>
 *     <li>читання вмісту текстового файлу та виведення у консоль;</li>
 *     <li>запису виразу та результату у двійковий файл;</li>
 *     <li>читання двійкових даних (вираз + результат) та виведення у консоль.</li>
 * </ul>
 *
 * <p>
 * Використовується у лабораторній роботі №5 для демонстрації роботи
 * з потоками введення/виведення у Java.
 * </p>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.0
 */
public class FileHandler {

    /**
     * Записує переданий текстовий рядок у вказаний текстовий файл.
     * <p>
     * Дані дописуються у кінець файлу, а не перезаписують його.
     *
     * @param fileName ім'я файлу
     * @param content  текст для запису
     */
    public void writeToTextFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content);
            writer.newLine();
            writer.newLine(); // порожній рядок для зручності перегляду
        } catch (IOException e) {
            System.out.println("Помилка запису у текстовий файл: " + e.getMessage());
        }
    }

    /**
     * Зчитує вміст текстового файлу та виводить його у консоль.
     *
     * @param fileName ім'я файлу для читання
     */
    public void readFromTextFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("Вміст текстового файлу:");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання з текстового файлу: " + e.getMessage());
        }
    }

    /**
     * Записує вираз і його числовий результат у двійковий файл.
     * <p>
     * Формат запису:
     * <ul>
     *     <li>{@link DataOutputStream#writeUTF(String)} — рядок з виразом;</li>
     *     <li>{@link DataOutputStream#writeDouble(double)} — числовий результат;</li>
     * </ul>
     *
     * @param fileName   ім'я двійкового файлу
     * @param expression текст математичного виразу
     * @param result     числовий результат
     */
    public void writeToBinaryFile(String fileName, String expression, double result) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName, true))) {
            out.writeUTF(expression);
            out.writeDouble(result);
        } catch (IOException e) {
            System.out.println("Помилка запису у двійковий файл: " + e.getMessage());
        }
    }

    /**
     * Зчитує вміст двійкового файлу, у якому зберігаються пари:
     * вираз (рядок) + результат (double),
     * і виводить їх у консоль.
     *
     * @param fileName ім'я двійкового файлу для читання
     */
    public void readFromBinaryFile(String fileName) {
        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            System.out.println("Вміст двійкового файлу:");
            while (in.available() > 0) {
                String expr = in.readUTF();
                double result = in.readDouble();
                System.out.println(expr + " = " + result);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання з двійкового файлу: " + e.getMessage());
        }
    }
}
