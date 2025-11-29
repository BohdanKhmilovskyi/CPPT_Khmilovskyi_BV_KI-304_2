# KI304/Khmilovskyi/Lab9/Main.py
# Точка входу в програму

from KI304.Khmilovskyi.Lab9.ClimateDevice import ClimateDevice

def main():
    """
    Головна функція програми.
    Створює об'єкт пристрою кліматконтролю та демонструє його роботу.
    """

    # Створення об'єкта
    device = ClimateDevice("Samsung", 1500, 22.0, 45.0)
    print(device)

    # Демонстрація методів
    device.turn_on()
    device.auto_climate_control(24.0, 50.0)
    print(device)
    device.turn_off()

if __name__ == "__main__":
    main()
