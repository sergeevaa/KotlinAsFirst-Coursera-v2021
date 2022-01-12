@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import java.lang.Math.*
import java.time.*


/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)


fun main() {
    val res = brickPasses(500, 4, 40, 40, 40)
    println(res)
}

/**
 * Простая (2 балла)
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val a = number / 1000
    val b = number / 100 % 10
    val c = number % 100 / 10
    val d = number % 10
    return a + b == c + d
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean =
    x1 == x2 || y1 == y2 || x1 - y1 == x2 - y2 || x1 + y1 == x2 + y2

/**
 * Простая (2 балла)
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    val y1 = year % 100 / 10
    val y2 = year % 10
    return if (year > 0) {
        if ((year % 100) % 4 == 0 || (y1 == 0 && y2 == 0)) {
            when (month) {
                1, 3, 5, 7, 8, 10, 12 -> 31
                4, 6, 9, 11 -> 30
                2 -> 29
                else -> -1
            }
        } else when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            2 -> 28
            4, 6, 9, 11 -> 30
            else -> -1
        }
    } else -1
}

/**
 * Простая (2 балла)
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    val ab = sqrt(sqr(x2 - x1) + sqr(y2 - y1))
    val a = abs(r1 - r2)
    return ab <= a
}

/**
 * Средняя (3 балла)
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean =
    ((a <= r && b <= r) || (b <= r && a <= s) || (c <= r && b <= r) || (b <= r && c <= s) || (a <= r && c <= r) || (c <= r && a <= s))
