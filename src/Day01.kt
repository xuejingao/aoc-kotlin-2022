import java.util.Collections
import java.util.PriorityQueue
import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var mostCalories = 0

        var currentCount = 0
        for (calorie in input) {
            currentCount = if (calorie.isBlank()) 0 else currentCount + calorie.toInt()
            mostCalories = max(mostCalories, currentCount)
        }

        return mostCalories
    }

    fun part2(input: List<String>): Int {
        val maxheap = PriorityQueue<Int>(Collections.reverseOrder())

        var currentCount = 0
        for (calorie in input) {
            currentCount = if (calorie.isBlank()) {
                maxheap.add(currentCount)
                0
            } else currentCount + calorie.toInt()
        }
        maxheap.add(currentCount) // For the Last Element
        var top3Sum = 0
        for ( i in 1..3) {
            top3Sum += maxheap.remove()
        }

        return top3Sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println("Part 1:" + part1(input))
    println("Part 2:" + part2(input))
}
