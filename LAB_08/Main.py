# lab25_module.py
# Програма реалізує обчислення виразу y = 1 / sin(x)
# та функції для читання/запису результатів у текстовому та двійковому форматах.

import math
import struct


def calculate_y(x_values):
    """
    Обчислює значення y = 1 / sin(x) для кожного елемента списку x_values.
    Якщо sin(x) = 0, то додає None (щоб уникнути ділення на нуль).
    """
    results = []
    for x in x_values:
        try:
            sin_x = math.sin(x)
            if sin_x == 0:
                results.append(None)
            else:
                results.append(1 / sin_x)
        except Exception:
            results.append(None)
    return results


# ------------------------- ТЕКСТОВІ ФУНКЦІЇ -------------------------

def write_to_text_file(filename, x_values, y_values):
    """
    Записує результати у текстовий файл у форматі:
    x = ..., y = ...
    """
    with open(filename, "w", encoding="utf-8") as f:
        for x, y in zip(x_values, y_values):
            if y is None:
                f.write(f"x = {x:.4f}, y = undefined (sin(x)=0)\n")
            else:
                f.write(f"x = {x:.4f}, y = {y:.6f}\n")


def read_from_text_file(filename):
    """Зчитує текстовий файл пострічково та повертає список рядків."""
    with open(filename, "r", encoding="utf-8") as f:
        return f.readlines()


# ------------------------- ДВІЙКОВІ ФУНКЦІЇ -------------------------

def write_to_binary_file(filename, y_values):
    """
    Записує значення y у двійковий файл у форматі float (4 байти).
    Якщо значення None — записує 0.0.
    """
    with open(filename, "wb") as f:
        for y in y_values:
            value = 0.0 if y is None else y
            f.write(struct.pack('f', value))


def read_from_binary_file(filename):
    """
    Зчитує двійковий файл і повертає список значень float.
    """
    results = []
    with open(filename, "rb") as f:
        while chunk := f.read(4):
            results.append(struct.unpack('f', chunk)[0])
    return results


# ------------------------- ГОЛОВНА ФУНКЦІЯ -------------------------

def main():
    """
    Основна функція програми:
    1. Зчитує x з клавіатури (через пробіл);
    2. Обчислює y = 1/sin(x);
    3. Записує результати у текстовий і двійковий файли;
    4. Зчитує та виводить назад дані з обох файлів.
    """
    try:
        x_values = list(map(float, input("Введіть значення x через пробіл: ").split()))
    except ValueError:
        print("Помилка: введено некоректні числа.")
        return

    y_values = calculate_y(x_values)

    # Запис у файли
    write_to_text_file("results.txt", x_values, y_values)
    write_to_binary_file("results.bin", y_values)

    # Зчитування назад
    print("\nДані з текстового файлу:")
    print("".join(read_from_text_file("results.txt")))

    print("Дані з двійкового файлу:")
    print(read_from_binary_file("results.bin"))


# Виконання модуля напряму
if __name__ == "__main__":
    main()
