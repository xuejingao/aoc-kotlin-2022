import Outcome.*

private val selectedScoreMap = hashMapOf<String, Int>(
        "X" to 1,
        "Y" to 2,
        "Z" to 3,
)

private enum class Outcome {
    WIN, LOSE, DRAW
}

private val winScoreMap = hashMapOf<Outcome, Int>(
        WIN to 6,
        LOSE to 0,
        DRAW to 3,
)

fun main() {
    fun determineResult(opp: String, you: String): Outcome {
        return if(opp[0].plus(23).compareTo(you[0]) == 0) DRAW
        else if(opp == "A" && you == "Z" ||
                opp == "B" && you == "X" ||
                opp == "C" && you == "Y") LOSE
        else WIN
    }

    fun calculateScore(opponentChoice: Char, yourChoice: Char): Int {
        val result = determineResult(opponentChoice.toString(), yourChoice.toString())

        var yourScore = selectedScoreMap[yourChoice.toString()]?.plus(winScoreMap[result]!!)

        return yourScore!!
    }


    fun part1(input: List<String>): Int {
        var scoreFromFollowing = 0

        for( round in input ) {
            val opponent = round[0]
            val yours = round[2]

            scoreFromFollowing += calculateScore(opponent, yours)
        }
        return scoreFromFollowing
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
//    println(part2(input))
}
