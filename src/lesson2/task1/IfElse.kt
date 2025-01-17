@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

fun main() {
    val res = segmentLength(-3, 0, 0, 1)
    println(res)

    val res1 = ageDescription(131)
    println(res1)
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    var a = age
    if (age < 0 || age > 200) return "null"
    if (age <= 14) {
        return when (age) {
            0, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 -> "$age лет"
            1 -> "$age год"
            2, 3, 4 -> "$age года"
            else -> "$age не существует"
        }
    } else if (age in 100..114) {
        return when (age) {
            100, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114 -> "$age лет"
            101 -> "$age год"
            102, 103, 104 -> "$age года"
            else -> "$age не существует"
        }
    } else {
        a %= 10
        return when (a) {
            0, 5, 6, 7, 8, 9 -> "$age лет"
            1 -> "$age год"
            2, 3, 4 -> "$age года"
            else -> "$age не существует"
        }
    }
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = (s1 + s2 + s3) / 2
    return if (s <= s1) {
        s / v1
    } else if (s > s1 && s <= s1 + s2) {
        t1 + (s - s1) / v2
    } else t1 + t2 + (s - s1 - s2) / v3
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    return if (kingX != rookX1 && kingY != rookY1 && kingX != rookX2 && kingY != rookY2) {
        0
    } else if ((kingX == rookX1 || kingY == rookY1) && (kingX != rookX2 && kingY != rookY2)) {
        1
    } else if ((kingX != rookX1 && kingY != rookY1) && (kingX == rookX2 || kingY == rookY2)) {
        2
    } else 3
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    return if (kingX != rookX && kingY != rookY && (kingX - kingY != bishopX - bishopY && kingX + kingY != bishopX + bishopY)) {
        0
    } else if ((kingX == rookX || kingY == rookY) && (kingX - kingY != bishopX - bishopY && kingX + kingY != bishopX + bishopY)) {
        1
    } else if ((kingX != rookX && kingY != rookY) && (kingX - kingY == bishopX - bishopY || kingX + kingY == bishopX + bishopY)) {
        2
    } else {
        3
    }
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int { // 3 3 6
    return if (a > 0 && b > 0 && c > 0) {
        if (a + b >= c && a + c >= b && b + c >= a) {
            if (a * a < b * b + c * c && b * b < a * a + c * c && c * c < a * a + b * b) {
                0
            } else if (a * a == b * b + c * c || b * b == a * a + c * c || c * c == a * a + b * b) {
                1
            } else if (a * a > b * b + c * c || b * b > a * a + c * c || c * c > a * a + b * b) {
                2
            } else -1
        } else -1
    } else -1
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return if (b >= a && d >= c) {
        if (b < c) {
            -1
        } else if (a >= c && b <= d) {
            b - a
        } else if (c >= a && d <= b) {
            d - c
        } else if (a <= c && b >= c && b <= d) {
            b - c
        } else if (a >= c && b >= d && d >= a) {
            d - a
        } else if (a > d) {
            -1
        } else -1
    } else -1
} // -3 0 0 1
