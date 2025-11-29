# KI304/Khmilovskyi/Lab9/Conditioner.py
# Базовий клас "Кондиціонер"

class Conditioner:
    """
    Клас, що описує базові характеристики кондиціонера.
    """

    def __init__(self, brand: str, power: float, temperature: float):
        """
        Ініціалізація об'єкта кондиціонера.
        :param brand: Назва бренду кондиціонера
        :param power: Потужність у ватах
        :param temperature: Поточна температура
        """
        self.brand = brand
        self.power = power
        self.temperature = temperature

    def turn_on(self):
        """Увімкнути кондиціонер"""
        print(f"{self.brand} кондиціонер увімкнено.")

    def turn_off(self):
        """Вимкнути кондиціонер"""
        print(f"{self.brand} кондиціонер вимкнено.")

    def set_temperature(self, temp: float):
        """Змінити температуру"""
        self.temperature = temp
        print(f"Температуру встановлено на {self.temperature}°C.")

    def __str__(self):
        """Рядкове представлення об'єкта"""
        return f"Кондиціонер {self.brand}: потужність {self.power} Вт, температура {self.temperature}°C"
