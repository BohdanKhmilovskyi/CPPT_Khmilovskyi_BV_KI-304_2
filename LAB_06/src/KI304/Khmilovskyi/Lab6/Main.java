package KI304.Khmilovskyi.Lab6;

/**
 * Клас {@code Main} є драйверною програмою для демонстрації роботи
 * параметризованого класу {@link Warehouse}.
 * <p>
 * У програмі створюються різні типи товарів ({@link FoodProduct} та {@code Electronics}),
 * які додаються у сховище. Далі здійснюється:
 * <ul>
 *     <li>перегляд усіх товарів;</li>
 *     <li>пошук найдорожчого товару (через метод {@link Warehouse#findMax()});</li>
 *     <li>видалення одного елемента зі сховища;</li>
 * </ul>
 * <p>
 * Використовується конструкція try-with-resources, оскільки {@link Warehouse}
 * реалізує {@link AutoCloseable} та очищує ресурси при закритті.
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.0
 */
public class Main {
    /**
     * Точка входу до програми. Створює об'єкти товарів, додає їх у сховище,
     * виводить всі елементи, знаходить максимальний елемент і демонструє роботу
     * параметризованого типу.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        try (Warehouse<Product> warehouse = new Warehouse<>()) {

            // Створення різних товарів
            FoodProduct apple = new FoodProduct("Яблуко", 15.5, 52);
            FoodProduct bread = new FoodProduct("Хліб", 22.0, 250);
            Electronics phone = new Electronics("Смартфон", 9999.0, 24);
            Electronics laptop = new Electronics("Ноутбук", 32000.0, 12);

            // Додавання у сховище
            warehouse.addItem(apple);
            warehouse.addItem(bread);
            warehouse.addItem(phone);
            warehouse.addItem(laptop);

            // Виведення всіх елементів
            System.out.println("\nВміст сховища:");
            for (Product p : warehouse.getItems())
                System.out.println(p);

            // Пошук максимального елементу (за ціною)
            Product max = warehouse.findMax();
            System.out.println("\nНайдорожчий товар: " + max);

            // Видалення одного товару
            warehouse.removeItem(bread);

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
