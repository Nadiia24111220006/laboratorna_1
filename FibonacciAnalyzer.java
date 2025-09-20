import java.util.ArrayList;
import java.util.Scanner; /**
 * Головний клас програми для роботи з числами Фібоначчі
 */
public class FibonacciAnalyzer {

    /**
     * Генерує перші N чисел Фібоначчі
     * @param n кількість чисел для генерації
     * @return масив об'єктів FibonacciNumber
     */
    public static FibonacciNumber[] generateFibonacciSequence(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N повинно бути більше 0");
        }

        FibonacciNumber[] sequence = new FibonacciNumber[n];

        sequence[0] = new FibonacciNumber(1, 1);
        if (n >= 2) {
            sequence[1] = new FibonacciNumber(2, 1);
        }

        long a = 1, b = 1;
        for (int i = 2; i < n; i++) {
            long next = a + b;
            sequence[i] = new FibonacciNumber(i + 1, next);
            a = b;
            b = next;
        }

        return sequence;
    }

    /**
     * Знаходить числа Фібоначчі, що закінчуються на задану цифру
     * @param sequence послідовність чисел Фібоначчі
     * @param digit цифра для пошуку
     * @return список чисел, що закінчуються на задану цифру
     */
    public static ArrayList<FibonacciNumber> findNumbersEndingWith(FibonacciNumber[] sequence, int digit) {
        ArrayList<FibonacciNumber> result = new ArrayList<>();

        for (FibonacciNumber fibNumber : sequence) {
            if (fibNumber.endsWithDigit(digit)) {
                result.add(fibNumber);
            }
        }

        return result;
    }

    /**
     * Головний метод програми
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            int n;
            int digit;

            // Обробка аргументів командного рядка
            if (args.length == 2) {
                n = Integer.parseInt(args[0]);
                digit = Integer.parseInt(args[1]);
                System.out.println("Використано аргументи командного рядка:");
                System.out.println("N = " + n + ", цифра = " + digit);
            } else {
                // Введення даних з клавіатури
                System.out.println("Введіть кількість чисел Фібоначчі (N):");
                n = scanner.nextInt();

                System.out.println("Введіть цифру для пошуку (0-9):");
                digit = scanner.nextInt();

                if (digit < 0 || digit > 9) {
                    System.out.println("Помилка: цифра повинна бути від 0 до 9");
                    return;
                }
            }

            // Генерація послідовності Фібоначчі
            FibonacciNumber[] fibonacciSequence = generateFibonacciSequence(n);

            // Виведення вхідних даних
            System.out.println("\n=== ВХІДНІ ДАНІ ===");
            System.out.println("Кількість чисел: " + n);
            System.out.println("Шукана цифра: " + digit);

            // Виведення всіх чисел Фібоначчі
            System.out.println("\n=== ПЕРШІ " + n + " ЧИСЕЛ ФІБОНАЧЧІ ===");
            for (FibonacciNumber fibNumber : fibonacciSequence) {
                System.out.println(fibNumber);
            }

            // Пошук чисел, що закінчуються на задану цифру
            ArrayList<FibonacciNumber> result = findNumbersEndingWith(fibonacciSequence, digit);

            // Виведення результатів
            System.out.println("\n=== РЕЗУЛЬТАТИ ===");
            if (result.isEmpty()) {
                System.out.println("Жодне з перших " + n + " чисел Фібоначчі не закінчується на цифру " + digit);
            } else {
                System.out.println("Числа Фібоначчі, що закінчуються на " + digit + ":");
                for (FibonacciNumber fibNumber : result) {
                    System.out.println(fibNumber);
                }
                System.out.println("Всього знайдено: " + result.size() + " чисел");
            }

        } catch (NumberFormatException e) {
            System.out.println("Помилка: некоректний формат числа");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Сталася неочікувана помилка: " + e.getMessage());
        }
    }
}
