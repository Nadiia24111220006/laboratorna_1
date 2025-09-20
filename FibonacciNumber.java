/**
 * Клас, що представляє число Фібоначчі з його номером та значенням
 */
public record FibonacciNumber(int position, long value) {
    /**
     * Конструктор класу FibonacciNumber
     *
     * @param position позиція числа у послідовності Фібоначчі
     * @param value    значення числа Фібоначчі
     */
    public FibonacciNumber {
    }


    /**
     * Перевіряє, чи закінчується число на задану цифру
     *
     * @param digit цифра для перевірки (0-9)
     * @return true, якщо число закінчується на задану цифру
     */
    public boolean endsWithDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Цифра повинна бути від 0 до 9");
        }
        return (value % 10) == digit;
    }

    /**
     * Повертає рядкове представлення об'єкта
     *
     * @return рядок з інформацією про число Фібоначчі
     */
    @Override
    public String toString() {
        return "F(" + position + ") = " + value;
    }
}


