import java.util.LinkedHashMap;

class Number {
    Notation Notation;
    int Value;
    public static LinkedHashMap<Integer, String> RomanNumerals = new LinkedHashMap<>() {{
        put(500, "D");
        put(100, "C");
        put(50, "L");
        put(10, "X");
        put(5, "V");
        put(1, "I");
    }};
    public static int[] Keys = {500, 100, 50, 10, 5, 1};

    Number(String str){
        for (var i = 0; i < Numerals.romanNumerals.length; i++){
            if (str.compareTo(Numerals.romanNumerals[i]) == 0) {
                this.Notation = Notation.Roman;
                Value = i + 1;
                return;
            }
            if (str.compareTo(Numerals.arabNumerals[i]) == 0) {
                this.Notation = Notation.Arabic;
                Value = i + 1;
                return;
            }
        }
        throw new IllegalArgumentException("Один из операндов не удовлетворяет условию - целое число в римской или арабской систему счисления в диапазоне от 1 до 10");
    }

    Number(Notation notation, int value){
        Notation = notation;
        Value = value;
    }

    public Number performOperation(Number number, String operator) throws InvalidOperator {
        int value = Value;
        switch (operator) {
            case "+" -> value += number.Value;
            case "-" -> value -= number.Value;
            case "*" -> value *= number.Value;
            case "/" -> value /= number.Value;
            default -> throw new InvalidOperator("Неизвестный оператор!");
        }
        return new Number(Notation, value);
    }

    @Override
    public String toString() {
        if (Notation == Notation.Arabic)
            return Integer.toString(Value);
        String result = "";
        int value = Value;
        for (var i = 0; i < 6 && value > 0; i++) {
            int subtrahend = (int) Math.pow(10, Integer.toString(value).length() - 1);
            if (value - Keys[i] < 0 && Math.abs(value - Keys[i]) <= subtrahend) {
                result += RomanNumerals.get(subtrahend) + RomanNumerals.get(Keys[i]);
                value -= Keys[i] - subtrahend;
            }
            else if (value - Keys[i] >= 0) {
                result += RomanNumerals.get(Keys[i]);
                value -= Keys[i];
                i--;
            }
        }
        return result;
    }
}