package KI304.Khmilovskyi.Lab6;

import java.util.ArrayList;
import java.util.List;

/**
 * Параметризований клас {@code Warehouse<T>} представляє універсальне сховище елементів.
 * <p>
 * Тип {@code T} має реалізовувати {@link Comparable}, що дозволяє:
 * <ul>
 *     <li>додавати об'єкти у сховище;</li>
 *     <li>видаляти об'єкти;</li>
 *     <li>отримувати список всіх елементів;</li>
 *     <li>знаходити максимальний елемент через {@link #findMax()};</li>
 * </ul>
 *
 * <p>Клас реалізує {@link AutoCloseable}, щоб автоматично очищати колекцію при завершенні роботи.</p>
 *
 * @param <T> тип елементів, що зберігаються у сховищі
 */
public class Warehouse<T extends Comparable<T>> implements AutoCloseable {

    /** Список збережених елементів. */
    private List<T> items;

    /**
     * Створює порожнє сховище.
     */
    public Warehouse() {
        items = new ArrayList<>();
    }

    /**
     * Додає елемент у сховище.
     *
     * @param item об'єкт для додавання
     */
    public void addItem(T item) {
        items.add(item);
        System.out.println("Додано: " + item);
    }

    /**
     * Видаляє елемент зі сховища.
     *
     * @param item об'єкт для видалення
     */
    public void removeItem(T item) {
        if (items.remove(item))
            System.out.println("Видалено: " + item);
        else
            System.out.println("Елемент не знайдено: " + item);
    }

    /**
     * @return список усіх елементів у сховищі
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * Знаходить максимальний елемент у сховищі.
     *
     * @return елемент із найбільшою "вагою" за compareTo,
     *         або {@code null}, якщо сховище порожнє
     */
    public T findMax() {
        if (items.isEmpty()) return null;

        T max = items.get(0);
        for (T item : items) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    /**
     * Очищає сховище після завершення роботи.
     * Викликається автоматично у try-with-resources.
     */
    @Override
    public void close() {
        items.clear();
        System.out.println("Сховище закрито.");
    }
}
