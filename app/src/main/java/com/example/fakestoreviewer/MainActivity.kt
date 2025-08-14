package com.example.fakestoreviewer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: ProductViewModel = viewModel()

            NavHost(navController = navController, startDestination = "home") {
                composable("home") {
                    HomeScreen(viewModel, navController)
                }
                composable(
                    "detail/{productId}",
                    arguments = listOf(navArgument("productId") {
                        type = NavType.IntType
                    })
                ) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                    DetailScreen(productId, viewModel)
                }
            }
        }
    }
}
