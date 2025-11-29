# KI304/Khmilovskyi/Lab9/ClimateDevice.py
# Похідний клас "Пристрій кліматконтролю"

from KI304.Khmilovskyi.Lab9.Conditioner import Conditioner

class ClimateDevice(Conditioner):
    """
    Похідний клас, який розширює функціонал кондиціонера
    та додає автоматичне регулювання вологості.
    """

    def __init__(self, brand: str, power: float, temperature: float, humidity: float):
        """
        Ініціалізація пристрою кліматконтролю.
        :param brand: Назва бренду
        :param power: Потужність
        :param temperature: Температура
        :param humidity: Вологість (%)
        """
        super().__init__(brand, power, temperature)
        self.humidity = humidity

    def set_humidity(self, humidity: float):
        """Змінити рівень вологості"""
        self.humidity = humidity
        print(f"Вологість встановлено на {self.humidity}%.")

    def auto_climate_control(self, desired_temp: float, desired_humidity: float):
        """
        Автоматичне регулювання температури та вологості.
        """
        print(f"--- Автоматичне налаштування клімату ---")
        self.set_temperature(desired_temp)
        self.set_humidity(desired_humidity)
        print(f"Клімат встановлено: {desired_temp}°C, {desired_humidity}% вологості.")

    def __str__(self):
        """Рядкове представлення"""
        return (f"Кліматичний пристрій {self.brand}: потужність {self.power} Вт, "
                f"температура {self.temperature}°C, вологість {self.humidity}%")
