package cz.cvut.fit.biand.homework2.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.cvut.fit.biand.homework2.features.favourites.presentation.FavouritesScreen
import cz.cvut.fit.biand.homework2.features.detail.presentation.DetailScreen
import cz.cvut.fit.biand.homework2.features.list.presentation.ListScreen
import cz.cvut.fit.biand.homework2.features.search.presentation.SearchScreen
import cz.cvut.fit.biand.homework2.ui.theme.Blue

@Composable
fun Navigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.TopLevel.ListScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screen.TopLevel.ListScreen.route) {
                ListScreen(
                    navigateToSearch = { navController.navigate(Screen.SearchScreen.route) },
                    navigateToDetails = {  navController.navigate(Screen.DetailScreen.route + "/$it") })
            }
            composable(
                route = Screen.DetailScreen.route + "/{id}",
                arguments = listOf(
                    navArgument(name = "id") {
                        type = NavType.IntType
                    },
                ),
            ) { entry ->
                DetailScreen(
                    navController = navController,
                    id = entry.arguments?.getInt("id"),
                )
            }
            composable(route = Screen.SearchScreen.route) {
                SearchScreen(
                    navController = navController,
                )
            }
            composable(route = Screen.TopLevel.FavouritesScreen.route) {
                FavouritesScreen()
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    if (currentDestination?.route == Screen.DetailScreen.route + "/{id}"
        || currentDestination?.route == Screen.SearchScreen.route) {
        return
    }
    BottomNavigation {
        Screen.TopLevel.all.forEach { screen ->
            if (currentDestination != null) {
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = screen.icon), contentDescription = null) },
                    selectedContentColor = Blue,
                    unselectedContentColor = Black,
                    label = { Text(stringResource(screen.name)) },
                    selected = currentDestination.hierarchy.any { it.route == screen.route },
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

