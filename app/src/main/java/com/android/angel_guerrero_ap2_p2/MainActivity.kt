package com.android.angel_guerrero_ap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.rememberNavBackStack
import com.android.angel_guerrero_ap2_p2.presentation.navigation.AppNavDisplay
import com.android.angel_guerrero_ap2_p2.presentation.navigation.Screen
import com.android.angel_guerrero_ap2_p2.ui.theme.GastosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GastosTheme {
                val backStack = rememberNavBackStack(Screen.GastoList)

                val items = listOf(
                    TopLevelRoute("Gastos", Screen.GastoList, Icons.Default.List)
                )

                Scaffold(
                    bottomBar = {
                        val currentDestination = backStack.lastOrNull()
                        val isDetail = currentDestination is Screen.GastoDetail

                        if (!isDetail && items.size > 1) {
                            NavigationBar {
                                items.forEach{ item ->
                                    NavigationBarItem(
                                        icon = { Icon(item.icono, contentDescription = item.nombre) },
                                        label = { Text(item.nombre) },
                                        selected = currentDestination == item.ruta,
                                        onClick = {
                                            if (currentDestination != item.ruta) {
                                                backStack.clear()
                                                backStack.add(item.ruta)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppNavDisplay(
                        backStack = backStack,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    }
}

data class TopLevelRoute<T : Screen>(
    val nombre: String,
    val ruta: T,
    val icono: ImageVector
)