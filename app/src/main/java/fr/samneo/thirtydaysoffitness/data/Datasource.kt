package fr.samneo.thirtydaysoffitness.data

import fr.samneo.thirtydaysoffitness.R
import fr.samneo.thirtydaysoffitness.model.Exercise

object Datasource {

    enum class PairNumber {
        PAIR1,
        PAIR2,
        PAIR3,
        PAIR4,
        PAIR5,
        PAIR6,
        PAIR7,
        PAIR8,
        PAIR9,
        PAIR10,
        PAIR11,
        PAIR12,
        PAIR13,
        PAIR14,
        PAIR15
    }

    fun nextPair(currentPair: PairNumber): PairNumber {
        val pairs = PairNumber.values()
        val index = (currentPair.ordinal + 1) % pairs.size
        return pairs[index]
    }

    fun previousPair(currentPair: PairNumber): PairNumber {
        val pairs = PairNumber.values()
        val index = (currentPair.ordinal - 1 + pairs.size) % pairs.size
        return pairs[index]
    }

    private val allExercises = listOf(
        Exercise(1, R.string.exercice1_title, R.drawable.exercise1, R.string.exercice1_description),
        Exercise(2, R.string.exercice2_title, R.drawable.exercise2, R.string.exercice2_description),
        Exercise(3, R.string.exercice3_title, R.drawable.exercise3, R.string.exercice3_description),
        Exercise(
            4, R.string.exercice4_title, R.drawable.exercise4and18, R.string.exercice4_description
        ),
        Exercise(
            5, R.string.exercice5_title, R.drawable.exercise5and12, R.string.exercice5_description
        ),
        Exercise(6, R.string.exercice6_title, R.drawable.exercise6, R.string.exercice6_description),
        Exercise(7, R.string.exercice7_title, R.drawable.rest),
        Exercise(8, R.string.exercice8_title, R.drawable.exercise8, R.string.exercice8_description),
        Exercise(9, R.string.exercice9_title, R.drawable.exercise9, R.string.exercice9_description),
        Exercise(
            10,
            R.string.exercice10_title,
            R.drawable.exercise10and17,
            R.string.exercice10_description
        ),
        Exercise(
            11, R.string.exercice11_title, R.drawable.exercise11, R.string.exercice11_description
        ),
        Exercise(
            12,
            R.string.exercice12_title,
            R.drawable.exercise5and12,
            R.string.exercice12_description
        ),
        Exercise(
            13, R.string.exercice13_title, R.drawable.exercise13, R.string.exercice13_description
        ),
        Exercise(14, R.string.exercice14_title, R.drawable.rest),
        Exercise(
            15, R.string.exercice15_title, R.drawable.exercise15, R.string.exercice15_description
        ),
        Exercise(
            16, R.string.exercice16_title, R.drawable.exercise16, R.string.exercice16_description
        ),
        Exercise(
            17,
            R.string.exercice17_title,
            R.drawable.exercise10and17,
            R.string.exercice17_description
        ),
        Exercise(
            18,
            R.string.exercice18_title,
            R.drawable.exercise4and18,
            R.string.exercice18_description
        ),
        Exercise(
            19, R.string.exercice19_title, R.drawable.exercise19, R.string.exercice19_description
        ),
        Exercise(
            20, R.string.exercice20_title, R.drawable.exercise20, R.string.exercice20_description
        ),
        Exercise(21, R.string.exercice21_title, R.drawable.rest),
        Exercise(
            22, R.string.exercice22_title, R.drawable.exercise22, R.string.exercice22_description
        ),
        Exercise(
            23, R.string.exercice23_title, R.drawable.exercise23, R.string.exercice23_description
        ),
        Exercise(
            24, R.string.exercice24_title, R.drawable.exercise24, R.string.exercice24_description
        ),
        Exercise(
            25, R.string.exercice25_title, R.drawable.exercise25, R.string.exercice25_description
        ),
        Exercise(
            26, R.string.exercice26_title, R.drawable.exercise26, R.string.exercice26_description
        ),
        Exercise(27, R.string.exercice27_title, R.drawable.rest),
        Exercise(
            28, R.string.exercice28_title, R.drawable.exercise28, R.string.exercice28_description
        ),
        Exercise(
            29, R.string.exercice29_title, R.drawable.exercise29, R.string.exercice29_description
        ),
        Exercise(
            30, R.string.exercice30_title, R.drawable.exercise30, R.string.exercice30_description
        ),
    )

    fun collectTheExercisesInPairs(numberOfThePair: PairNumber): List<Exercise> {
        return when (numberOfThePair) {
            PairNumber.PAIR1 -> listOf(allExercises[0], allExercises[1])
            PairNumber.PAIR2 -> listOf(allExercises[2], allExercises[3])
            PairNumber.PAIR3 -> listOf(allExercises[4], allExercises[5])
            PairNumber.PAIR4 -> listOf(allExercises[6], allExercises[7])
            PairNumber.PAIR5 -> listOf(allExercises[8], allExercises[9])
            PairNumber.PAIR6 -> listOf(allExercises[10], allExercises[11])
            PairNumber.PAIR7 -> listOf(allExercises[12], allExercises[13])
            PairNumber.PAIR8 -> listOf(allExercises[14], allExercises[15])
            PairNumber.PAIR9 -> listOf(allExercises[16], allExercises[17])
            PairNumber.PAIR10 -> listOf(allExercises[18], allExercises[19])
            PairNumber.PAIR11 -> listOf(allExercises[20], allExercises[21])
            PairNumber.PAIR12 -> listOf(allExercises[22], allExercises[23])
            PairNumber.PAIR13 -> listOf(allExercises[24], allExercises[25])
            PairNumber.PAIR14 -> listOf(allExercises[26], allExercises[27])
            PairNumber.PAIR15 -> listOf(allExercises[28], allExercises[29])
        }
    }
}