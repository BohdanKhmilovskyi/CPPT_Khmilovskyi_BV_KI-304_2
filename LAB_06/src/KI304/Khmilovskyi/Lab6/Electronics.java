package KI304.Khmilovskyi.Lab6;

/**
 * Клас Electronics — реалізує електронний товар.
 */
public class Electronics extends Product {
    private int warrantyMonths;

    public Electronics(String name, double price, int warrantyMonths) {
        super(name, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String toString() {
        return "Електроніка: " + name + ", ціна: " + price + ", гарантія: " + warrantyMonths + " міс.";
    }
}