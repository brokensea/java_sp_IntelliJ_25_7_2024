package de.supercode.dAY24_26_7_2024;

public class MyAtoi {
    public static int myAtoi(String string) {

        // 1. Führende Leerzeichen ignorieren
        string = string.trim();
        if (string.isEmpty()) {
            return 0;
        }
        // 2. Vorzeichen verarbeiten
        int sign = 1;  // Standardmäßig positiv
        int index = 0;
        if (string.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (string.charAt(index) == '+') {
            index++;// Positives Vorzeichen erkannt, kein Einfluss auf das Ergebnis, daher nur Index erhöhen
        }

        // 3. Zahlen lesen und konvertieren
        int result = 0;
        while (index < string.length()) {
            char c = string.charAt(index);  // Aktuellen Charakter an der Position `index` lesen
            if (c < '0' || c > '9') {
                break;
            }
            int digit = c - '0'; // Umwandlung des Charakters in die entsprechende Ziffer

            // Überlauf behandeln
            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + digit;// An den bisherigen Wert `result` die neue Ziffer anhängen
            index++;
        }
        return result * sign; // Ergebnis mit dem entsprechenden Vorzeichen zurückgeben

    }
}
//
//
//while (index < string.length()) {
//    char c = string.charAt(index);
//    if (c < '0' || c > '9') {
//        break;
//    }
//    int digit = c - '0';
//    if (result > (Integer.MAX_VALUE - digit) / 10) {
//        return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
//    }
//    result = result * 10 + digit;
//    index++;
//}
//return result * sign;
//1. while (index < string.length())
//目的：确保 index 不超过字符串的长度，以避免越界访问。
//行为：只要 index 小于字符串的长度，就继续执行循环体。
//2. char c = string.charAt(index)
//目的：获取字符串中当前位置的字符。
//行为：charAt(index) 方法返回字符串中 index 位置的字符。
//3. if (c < '0' || c > '9') { break; }
//目的：检查当前字符是否是有效的数字字符。
//行为：如果 c 不是数字字符（即小于 '0' 或大于 '9'），就退出循环。这样可以跳过非数字字符，并终止转换过程。
//4. int digit = c - '0';
//目的：将字符转换为相应的整数值。
//行为：通过 c - '0' 将字符 c 转换为整数。例如，字符 '5' 转换为整数 5。这是因为字符 '0' 的 ASCII 值是 48，字符 '1' 的 ASCII 值是 49，所以 '5' - '0' 就得到 5。
//5. if (result > (Integer.MAX_VALUE - digit) / 10) { return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE; }
//目的：检查是否会发生整数溢出。
//行为：
//Integer.MAX_VALUE 是 2^31 - 1，表示 32 位有符号整数的最大值。
//result > (Integer.MAX_VALUE - digit) / 10 的意思是，如果 result 乘以 10 再加上 digit 会超出 Integer.MAX_VALUE，则发生溢出。
//如果发生溢出，返回 Integer.MAX_VALUE 或 Integer.MIN_VALUE，取决于符号 sign。
//6. result = result * 10 + digit;
//目的：构建最终的整数值。
//行为：
//result * 10 将之前的结果向左移动一位（即乘以 10），为新的数字 digit 留出位置。
//+ digit 将当前数字字符的值加到结果中。
//7. index++;
//目的：移动到下一个字符。
//行为：递增 index，以便在下一次循环中处理下一个字符。
//8. return result * sign;
//目的：返回最终的整数结果。
//行为：根据之前确定的符号 sign（1 或 -1）调整结果的符号，并返回。
//例子说明
//假设输入字符串是 "42", sign 是 1（正数），index 从 0 开始：
//
//第一次循环：
//
//char c = '4'
//'4' 是数字，所以继续。
//digit = 4 - '0' = 4
//result = 0 * 10 + 4 = 4
//index++，index = 1
//第二次循环：
//
//char c = '2'
//'2' 是数字，所以继续。
//digit = 2 - '0' = 2
//result = 4 * 10 + 2 = 42
//index++，index = 2
//循环结束：index 达到字符串末尾，返回 result * sign = 42 * 1 = 42
//
//通过这个循环，myAtoi 能够正确地将字符串 "42" 转换为整数 42。同时，这段代码也考虑了潜在的溢出问题，确保最终结果在 32 位有符号整数范围内。