package com.mec.shopping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mec.shopping.destinations.AddEventRoute
import com.mec.shopping.destinations.EventDetailsRoute
import com.mec.shopping.destinations.HomeRoute
import com.mec.shopping.ui.event.add.AddEventPage
import com.mec.shopping.ui.event.details.EventDetailsPage
import com.mec.shopping.ui.home.HomePage
import com.mec.shopping.ui.theme.ShoppingTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingApp()
        }
    }
}

@Composable
fun ShoppingApp(modifier: Modifier = Modifier) {
    ShoppingTheme {
        NavController(modifier)
    }
}

@Composable
private fun NavController(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomePage(
                navigateToAddEvent = { navController.navigate(AddEventRoute) },
                navigateToEventDetails = { id, name ->
                    navController.navigate(EventDetailsRoute(id, name))
                },
                modifier = modifier
            )
        }
        composable<AddEventRoute> {
            AddEventPage(
                navigateBack = { navController.popBackStack() },
                navigateUp = { navController.navigateUp() },
                modifier = modifier
            )
        }
        composable<EventDetailsRoute> {
            EventDetailsPage(
                navigateBack = { navController.popBackStack() },
                navigateUp = { navController.navigateUp() },
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
}