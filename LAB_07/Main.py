# module: shaded_matrix.py
# Програма генерує зубчастий список, який містить лише заштриховані області квадратної матриці.
# У варіанті заштриховані верхній та нижній трикутники, що утворюються по діагоналях квадрата.

def generate_shaded_matrix(n, fill_char):
    """
    Генерує зубчастий список для заштрихованих трикутників.
    Верхній трикутник: рядки від 0 до середини (включно).
    Нижній трикутник: рядки від середини до кінця.
    """
    matrix = []

    mid = n // 2  # середина матриці

    for i in range(n):
        row = []
        for j in range(n):
            # Верхній заштрихований трикутник (до середини)
            if i <= mid and (j >= i and j < n - i):
                row.append(fill_char)
            # Нижній заштрихований трикутник (після середини)
            elif i >= mid and (j >= n - 1 - i and j <= i):
                row.append(fill_char)
            else:
                row.append(' ')
        matrix.append(row)

    return matrix


def print_matrix(matrix):
    """Виводить матрицю у зручному форматі."""
    for row in matrix:
        print(' '.join(row))


def main():
    """Основна функція програми."""
    try:
        n = int(input("Введіть розмір квадратної матриці (n): "))
        if n <= 0:
            print("Розмір має бути додатним числом.")
            return

        fill_char = input("Введіть символ-заповнювач: ")

        # Перевірка правильності введення символу
        if len(fill_char) != 1:
            print("Помилка: потрібно ввести лише один символ.")
            return

        matrix = generate_shaded_matrix(n, fill_char)

        print("\nСформований масив:")
        print_matrix(matrix)

    except ValueError:
        print("Помилка: введено некоректне значення для розміру матриці.")


# Виклик головної функції при безпосередньому запуску модуля
if __name__ == "__main__":
    main()