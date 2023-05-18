package cz.cvut.fit.biand.homework2.navigation

import androidx.annotation.StringRes
import cz.cvut.fit.biand.homework2.R

sealed class Screen(val route: String) {
    sealed class TopLevel(val route: String) {
        abstract val icon: Int

        @get:StringRes
        abstract val name: Int

        object ListScreen : TopLevel("list") {
            override val icon: Int
                get() = R.drawable.ic_characters
            override val name: Int
                get() = R.string.characters
        }

        object FavouritesScreen : TopLevel("favourites") {
            override val icon: Int
                get() = R.drawable.ic_favorites_filled
            override val name: Int
                get() = R.string.favorites
        }

        companion object {
            val all get() = listOf(ListScreen, FavouritesScreen)
        }
    }
    object DetailScreen : Screen("detail")
    object SearchScreen : Screen("search")
}
