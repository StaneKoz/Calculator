import java.util.Scanner;

public class Main {
    public static String calc(String input) throws InvalidExpressionFormat, InvalidOperator, InvalidResult{
        String[] expression = input.split(" ");
        if (expression.length != 3){
            throw new InvalidExpressionFormat("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        var firstArgument = new Number(expression[0]);
        var secondArgument = new Number(expression[2]);
        if (firstArgument.Notation != secondArgument.Notation)
            throw new IllegalArgumentException("Используются одновременно разные системы счисления");
        var result = firstArgument.performOperation(secondArgument, expression[1]);
        if (result.Notation == Notation.Roman && result.Value < 1)
            throw new InvalidResult("Результатом работы с римскими числами могут быть только положительные числа");
        return result.toString();
    }

    public static void main(String[] args) throws InvalidExpressionFormat, InvalidOperator, InvalidResult {
        System.out.println("Вам приветствует тестовый калькулятор.\nПодерживаемые системы счисления:" +
                "римская и арабская\nДиапазон: от 1 до 10 включительно\nДоступные операторы: +, -, *, /");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите выражение или 'x', если хотите выйти: ");
            String input = scanner.nextLine();
            if (!input.equals("x"))
                System.out.println(input + " = " + calc(input));
            else return;
        }
    }
}