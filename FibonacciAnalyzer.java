import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Головний клас програми для роботи з числами Фібоначчі.
 * Дозволяє генерувати послідовність, шукати числа за критерієм
 * та взаємодіяти з користувачем через командний рядок або консоль.
 *
 * @author Петляківська Надія
 * @version 1.0
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
    public static List<FibonacciNumber> findNumbersEndingWith(FibonacciNumber[] sequence, int digit) {
        List<FibonacciNumber> result = new ArrayList<>();

        for (FibonacciNumber fibNumber : sequence) {
            if (fibNumber.endsWithDigit(digit)) {
                result.add(fibNumber);
            }
        }

        return result;
    }

    /**
     * Обробляє аргументи командного рядка
     * @param args аргументи командного рядка
     * @return масив з двох елементів [n, digit]
     */
    private static int[] processCommandLineArguments(String[] args) {
        int n = Integer.parseInt(args[0]);
        int digit = Integer.parseInt(args[1]);
        System.out.println("Використано аргументи командного рядка:");
        System.out.println("N = " + n + ", цифра = " + digit);
        return new int[]{n, digit};
    }

    /**
     * Зчитує дані з клавіатури
     * @param scanner об'єкт Scanner для зчитування вводу
     * @return масив з двох елементів [n, digit]
     */
    private static int[] readInputFromKeyboard(Scanner scanner) {
        System.out.println("Введіть кількість чисел Фібоначчі (N):");
        int n = scanner.nextInt();

        System.out.println("Введіть цифру для пошуку (0-9):");
        int digit = scanner.nextInt();

        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Цифра повинна бути від 0 до 9");
        }

        return new int[]{n, digit};
    }

    /**
     * Виводить вхідні дані програми
     * @param n кількість чисел
     * @param digit шукана цифра
     */
    private static void printInputData(int n, int digit) {
        System.out.println("\n=== ВХІДНІ ДАНІ ===");
        System.out.println("Кількість чисел: " + n);
        System.out.println("Шукана цифра: " + digit);
    }

    /**
     * Виводить послідовність чисел Фібоначчі
     * @param sequence послідовність чисел
     * @param n кількість чисел
     */
    private static void printFibonacciSequence(FibonacciNumber[] sequence, int n) {
        System.out.println("\n=== ПЕРШІ " + n + " ЧИСЕЛ ФІБОНАЧЧІ ===");
        for (FibonacciNumber fibNumber : sequence) {
            System.out.println(fibNumber);
        }
    }

    /**
     * Виводить результати пошуку
     * @param result список знайдених чисел
     * @param digit шукана цифра
     * @param n кількість чисел
     */
    private static void printResults(List<FibonacciNumber> result, int digit, int n) {
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
    }

    /**
     * Головний метод програми
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n;
            int digit;

            // Обробка вхідних даних
            int[] input;
            if (args.length == 2) {
                input = processCommandLineArguments(args);
            } else {
                input = readInputFromKeyboard(scanner);
            }
            n = input[0];
            digit = input[1];

            // Генерація послідовності Фібоначчі
            FibonacciNumber[] fibonacciSequence = generateFibonacciSequence(n);

            // Виведення даних
            printInputData(n, digit);
            printFibonacciSequence(fibonacciSequence, n);

            // Пошук та виведення результатів
            List<FibonacciNumber> result = findNumbersEndingWith(fibonacciSequence, digit);
            printResults(result, digit, n);

        } catch (NumberFormatException e) {
            System.out.println("Помилка: некоректний формат числа");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Сталася неочікувана помилка: " + e.getMessage());
        }
    }
}
