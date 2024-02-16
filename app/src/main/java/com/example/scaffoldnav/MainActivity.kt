package com.example.scaffoldnav

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.scaffoldnav.ui.theme.ScaffoldnavTheme

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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldApp() {
    Scaffold(
        topBar = { MyTopBar()},
        content = { Text(text = "Content for Home Screen") },
        //bottomBar = { BottomAppBar(content = { Text(text = "Bottom bar") }) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(){
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(title = { Text(text = "My App") },
        navigationIcon ={
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Filled.Menu, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { expanded = !expanded}) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(text = { Text(text = "Info") }, onClick = { /*TODO*/ })
                DropdownMenuItem(text = { Text(text = "Settings") }, onClick = { /*TODO*/ })
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldnavTheme {
        ScaffoldApp()
    }
}