/**
 * Клас, що представляє число Фібоначчі з його номером та значенням.
 * Використовує record для автоматичної реалізації методів доступу.
 *
 * @author Петляківська Надія
 * @version 1.0
 */
public record FibonacciNumber(int position, long value) {

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


