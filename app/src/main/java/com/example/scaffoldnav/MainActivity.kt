package com.example.scaffoldnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldnav.ui.theme.ScaffoldnavTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldnavTheme {
                    ScaffoldApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController){
    // Manage the expanded state of the dropdown
    var expanded by remember { mutableStateOf(false) }
    // Creating the top app bar with the title and dropdown menu
    TopAppBar(
        title = { Text(title)},
        actions = {
            IconButton(onClick = { expanded = !expanded}
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            // Dropdown menu with two items info and settings
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(text = { Text(text = "Info")}, onClick = { navController.navigate("info")})
                DropdownMenuItem(text = { Text(text = "Settings")}, onClick = { navController.navigate("settings") })
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController){
    TopAppBar(title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp()}) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)

            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    // Scaffold for the main screen with the MainTopBar as the top app bar
    Scaffold(
        topBar = { MainTopBar(title = "My App", navController = navController) }
    ) {paddingValues ->
        Text(modifier = Modifier.padding(paddingValues), text = "Content for Home screen" )
    }
}

@Composable
fun InfoScreen(navController: NavController) {
    // Scaffold for the info screen with the ScreenTopBar as the top app bar
    Scaffold(
        topBar = { ScreenTopBar(title = "Info", navController = navController) }
    ) {paddingValues ->
        Text(modifier = Modifier.padding(paddingValues), text = "Content for Info screen" )
    }
}

@Composable
fun SettingsScreen(navController: NavController) {
    // Scaffold for the settings screen with the ScreenTopBar as the top app bar
    Scaffold(
        topBar = { ScreenTopBar(title = "Settings", navController = navController) }
    ) {paddingValues ->
        Text(modifier = Modifier.padding(paddingValues), text = "Content for Settings screen" )
    }
}


@Composable
fun ScaffoldApp(){
    // Created a NavHost for navigation
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    )
    {
        composable(route = "Home"){
            MainScreen(navController)
        }
        composable(route = "Info"){
            InfoScreen(navController)
        }
        composable(route = "Settings"){
            SettingsScreen(navController)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldnavTheme {
    }
}