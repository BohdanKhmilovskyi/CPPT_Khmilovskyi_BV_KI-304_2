package KI304.Khmilovskyi.Lab6;

/**
 * Абстрактний клас {@code Product} є базовим класом для всіх типів товарів.
 * <p>
 * Містить спільні поля:
 * <ul>
 *     <li>{@code name} — назва товару;</li>
 *     <li>{@code price} — ціна товару;</li>
 * </ul>
 *
 * <p>
 * Клас реалізує інтерфейс {@link Comparable}, що дозволяє порівнювати товари
 * між собою за ціною — цю функціональність використовує {@link Warehouse} при пошуку максимального.
 * </p>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.0
 */
public abstract class Product implements Comparable<Product> {

    /** Назва товару. */
    protected String name;

    /** Ціна товару. */
    protected double price;

    /**
     * Конструктор створює продукт із заданою назвою та ціною.
     *
     * @param name назва товару
     * @param price ціна товару
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /** @return ціна товару */
    public double getPrice() { return price; }

    /** @return назва товару */
    public String getName() { return name; }

    /**
     * Порівнює товари за ціною.
     *
     * @param other інший товар
     * @return результат порівняння двох чисел (цін)
     */
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }

    /**
     * Повертає текстове представлення товару.
     *
     * @return рядок виду "Назва (ціна: X)"
     */
    @Override
    public String toString() {
        return name + " (ціна: " + price + ")";
    }
}
