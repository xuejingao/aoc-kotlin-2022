import Outcome.*

private val selectedScoreMap = hashMapOf<String, Int>(
        "X" to 1, // rock
        "Y" to 2, // paper
        "Z" to 3, // scissor
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

    fun calculateScore(opponentChoice: String, yourChoice: String): Int {
        val result = determineResult(opponentChoice.toString(), yourChoice)

        var yourScore = selectedScoreMap[yourChoice]?.plus(winScoreMap[result]!!)

        return yourScore!!
    }


    fun part1(input: List<String>): Int {
        var scoreFromFollowing = 0

        for( round in input ) {
            val opponent = round[0].toString()
            val yours = round[2].toString()

            scoreFromFollowing += calculateScore(opponent, yours)
        }
        return scoreFromFollowing
    }

    fun calculateYourSelection(opponentSelection: String, result: Outcome): String {
        return if(result == DRAW) {
            when(opponentSelection) {
                "A" -> "X"
                "B" -> "Y"
                "C" -> "Z"
                else -> throw error("invalid mapping")
            }
        } else if(result == WIN) {
            when(opponentSelection) {
                "A" -> "Y"
                "B" -> "Z"
                "C" -> "X"
                else -> throw error("invalid mapping")
            }
        } else {
            when(opponentSelection) {
                "A" -> "Z"
                "B" -> "X"
                "C" -> "Y"
                else -> throw error("invalid mapping")
            }
        }
    }

    fun part2(input: List<String>): Int {
        // X = lose
        // Y = Draw
        // Z = WIN
        // Calculate the points

        var totalPoints = 0
        for (round in input) {
            val opponentsChoice = round[0].toString()
            val endRoundResult = round[2].toString().let {
                when(it) {
                    "X" -> LOSE
                    "Y" -> DRAW
                    "Z" -> WIN
                    else -> throw error("Not Valid Input")
                }
            }

            val yourChoice = calculateYourSelection(opponentsChoice, endRoundResult)
            totalPoints += calculateScore(opponentsChoice, yourChoice)

        }

        return totalPoints
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
