import java.util.HashMap;
import java.util.Map;

public class Main {
    public static Map<Character, Integer> map = new HashMap<>(); //мап для перевода из римских в арабские
    static
    {
        map.put('I', 1);
        map.put('V',  5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
    }
    public static void main(String[] args) {
        System.out.println(calc("V * IX"));
    }
    public static String calc(String input) {
        String res = "";
        try {
            String[] splitLine = input.split(" "); //разбил на 2 числа и знак
            if (splitLine.length > 3) {    //проверка
                throw new Exception();
            } else if (splitLine.length < 3) {
                throw new Exception();
            }
            String metaS = splitLine[1]; //2 элемент знак действия
            if (splitLine[0].charAt(0) >= 49 && splitLine[0].charAt(0) < 58) { //проверка на арабскую цифру
                int a = Integer.parseInt(splitLine[0]); //1 элемент число
                int b = Integer.parseInt(splitLine[2]); //3 элемент число
                if (!(a != 0 && b != 0 && a + b <= 20)) {
                    throw new Exception();
                }
                switch (metaS) { //операция в зависимости от знака
                    case "/":
                        res += (int) (a / b);
                        break;
                    case "+":
                        res += a + b;
                        break;
                    case "-":
                        res += a - b;
                        break;
                    case "*":
                        res += a * b;
                        break;
                }
            }
            else { //если римские цифры
                String r1 = splitLine[0];
                String r2 = splitLine[2];
                switch (metaS) { //действие в зависимости от знака
                    case "/" : res = intToRoman(romanToInt(r1) / romanToInt(r2));
                        break;
                    case "*" : res = intToRoman(romanToInt(r1) * romanToInt(r2));
                        break;
                    case "+" : res = intToRoman(romanToInt(r1) + romanToInt(r2));
                        break;
                    default: throw new Exception(); //так как отрицательных римских цифр нет
                }
            }

        }
        catch (Exception e)
        {
            System.out.println("oops");
        }
        return res;
    }
    public static int romanToInt(String s) //функци для перевода из римских в арабские
    {
        int res = 0; //результат
        int prev = 0; //предыдущее число
        for(int i = s.length() - 1; i >= 0; i--) //обратный цикл
        {
            int cur = map.get(s.charAt(i)); //нынешнее число
            if(cur < prev) //если оно меньше предыдущего, то из результата вычитаем текущее число
            {
                res -= cur;
            }
            else res += cur; //если больше или равно приблавляем
            prev = cur;
        }
        return res;
    }
    public static String intToRoman(int s) //функция для перевода из арабских в римские
    {
        int[] values = {1,4,5,9,10,40,50,90,100}; //беру 100 потому что макс результат Х * Х
        String[] rom = {"I", "IV", "V", "IX", "X", "XL", "L", "LM", "M"};
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = values.length - 1; i >= 0 && s > 0; i--)
        {
            while(s >= values[i]) 
            {
                s -= values[i];
                stringBuilder.append(rom[i]);
            }
        }
        return stringBuilder.toString();
    }


}