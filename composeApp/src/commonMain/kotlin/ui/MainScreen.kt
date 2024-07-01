package ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import navigation.MainNavGraph
import navigation.bottomnav.BottomNavigationBar

@Composable
fun MainScreen() {
    val bottomBarNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = bottomBarNavController)
        },
        content = {
            MainNavGraph(navController = bottomBarNavController)
        }
    )
}

