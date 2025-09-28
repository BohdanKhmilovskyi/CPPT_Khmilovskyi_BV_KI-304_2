package KI304.Khmilovskyi.Lab2;

/**
 * Клас-драйвер для тестування {@link Conditioner}.
 * Демонструє роботу методів кондиціонера.
 */
public class ConditionerDriver {
    public static void main(String[] args) {
        try {
            Conditioner conditioner = new Conditioner();

            conditioner.turnOn();
            conditioner.setTemperature(20);
            conditioner.decreaseTemp();
            conditioner.enableCooling();
            conditioner.showStatus();

            conditioner.cleanFilter();
            conditioner.enableHeating();
            conditioner.increaseTemp();
            conditioner.showStatus();

            conditioner.turnOff();
            conditioner.close();

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}
