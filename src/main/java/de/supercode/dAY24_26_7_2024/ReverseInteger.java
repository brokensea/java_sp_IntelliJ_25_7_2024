package de.supercode.dAY24_26_7_2024;

public class ReverseInteger {

    public static void main(String[] args) {

        System.out.println(reverse(123)); // : 321
        System.out.println(reverse(-123)); // : -321
        System.out.println(reverse(120)); // : 21
        System.out.println(reverse(1534236469)); // : 0 (überlauf)
    }

    public static int reverse(int number) {
        int result = 0;
        int sign = (number < 0) ? -1 : 1;  // Speichert das Vorzeichen des Eingabewertes
        number = Math.abs(number);

        // Schleife, bis x keine Ziffern mehr enthält
        while (number != 0) {
            int digit = number % 10; // Extrahiere die letzte Ziffer von x
            number /= 10; // Entferne die letzte Ziffer von x


//            if (result * 10 + digit > Integer.MAX_VALUE) {
//
//            }
            // Überprüfe auf Überlauf
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return 0;
            }
            result = result * 10 + digit;// Füge die extrahierte Ziffer dem Ergebnis hinzu
        }
        return result * sign; // Stelle das ursprüngliche Vorzeichen wieder her und gib das Ergebnis zurück
    }

}

//
//思路分析
//处理负号：
//
//如果输入的整数是负数，需要记录下这个信息，并在反转完成后将结果乘以 -1 以恢复负号。
//反转数字：
//
//逐位提取数字，并构建反转后的结果。
//检查溢出：
//
//在每一步操作后，检查当前结果是否可能导致溢出。如果发现溢出情况，则返回 0。
//具体步骤
//初始化：
//
//记录整数的符号（正数或负数）。
//使用一个变量来存储反转后的结果，初始化为 0。
//反转过程：
//
//使用一个循环逐位提取整数的每一位，构建反转后的结果。
//在每次添加新数字之前，检查是否会超出32位有符号整数的范围。
//处理结果：
//
//根据原始整数的符号调整结果的符号。
//
//
//将溢出检查条件写成 if (result * 10 + digit > Integer.MAX_VALUE) 看似直观，但实际上这种写法在很多情况下是不安全的，因为它可能会引发溢出。以下是详细解释为什么需要避免这种直接比较方式，并解释为什么 if (result > (Integer.MAX_VALUE - digit) / 10) 更合适。
//
//为什么不能直接使用 result * 10 + digit > Integer.MAX_VALUE
//直接计算可能导致溢出:
//
//在计算 result * 10 + digit 时，如果 result 很大，直接进行这个计算可能会导致溢出。
//例如，假设 result 是 2,147,483,647 减去 1（即 2,147,483,646），而 digit 是 1。计算 result * 10 + digit 时，结果将是 21,474,836,461，这已经超出了32位有符号整数的范围。

//
//int result = 2147483646;
//int digit = 1;
//// 结果 = 2147483646 * 10 + 1 = 21474836461
//// 21474836461 超出了 32 位整数的范围
//
//避免溢出的条件检查:
//
//通过条件 if (result > (Integer.MAX_VALUE - digit) / 10)，我们避免了在实际计算之前的溢出风险。
//这个条件的目的是确保 result 在乘以 10 并加上 digit 时不会超出 Integer.MAX_VALUE。
//我们先计算 (Integer.MAX_VALUE - digit) / 10，这个值是 result 在不会导致溢出的情况下可以安全地乘以 10 的最大值。
//详细解释条件检查
//计算溢出安全条件
//假设我们当前有：
//
//result：当前的结果值
//digit：当前要添加的数字
//为了确保计算 result * 10 + digit 不会溢出，我们需要满足以下条件：
//
//如果 result 的值大于 (Integer.MAX_VALUE - digit) / 10，那么 result * 10 + digit 就会超出 Integer.MAX_VALUE 的范围，从而导致溢出。