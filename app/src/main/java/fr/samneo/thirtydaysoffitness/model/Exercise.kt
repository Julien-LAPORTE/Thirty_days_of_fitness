package fr.samneo.thirtydaysoffitness.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Exercise(
    val exerciseNumber: Int,
    @StringRes val title: Int,
    @DrawableRes val image: Int,
    @StringRes val description: Int? = null
)
