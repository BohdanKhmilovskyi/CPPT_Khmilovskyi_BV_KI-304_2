package KI304.Khmilovskyi.Lab6;

/**
 * Клас {@code FoodProduct} представляє харчовий продукт.
 * <p>
 * Розширює базовий клас {@link Product}, додаючи поле:
 * <ul>
 *     <li>{@code calories} — калорійність продукту</li>
 * </ul>
 *
 * <p>Клас перевизначає метод {@link #toString()} для виведення
 * розширеної інформації про харчовий товар.</p>
 *
 * @author
 *     Bohdan Khmilovskyi, KI-304
 * @version 1.0
 */
public class FoodProduct extends Product {

    /** Калорійність продукту (ккал). */
    private int calories;

    /**
     * Створює новий харчовий продукт.
     *
     * @param name назва
     * @param price ціна
     * @param calories калорійність у ккал
     */
    public FoodProduct(String name, double price, int calories) {
        super(name, price);
        this.calories = calories;
    }

    /**
     * Повертає текстове представлення харчового продукту.
     *
     * @return рядок із назвою, ціною та калорійністю
     */
    @Override
    public String toString() {
        return "Харчовий продукт: " + name + ", ціна: " + price +
                ", калорійність: " + calories + " ккал";
    }
}
