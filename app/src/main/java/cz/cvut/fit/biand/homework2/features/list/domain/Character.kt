package cz.cvut.fit.biand.homework2.features.list.domain

import androidx.annotation.DrawableRes
import cz.cvut.fit.biand.homework2.R

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: String,
    val location: String,
    @DrawableRes val imageRes: Int = R.drawable.avatar1,
    val image: String = ""
)
