package com.example.doandtph42307

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.doandtph42307.Screen.HomeScreen
import com.example.doandtph42307.Screen.WellcomScreen


enum class ROUTER_SCREEN_NAME {
    WELCOME,
    HOME,
}

@Composable
fun AppNavHost(navcontroller: NavHostController) {
    NavHost(navController = navcontroller, startDestination = ROUTER_SCREEN_NAME.WELCOME.name) {
        composable(ROUTER_SCREEN_NAME.WELCOME.name){
            WellcomScreen(navcontroller)
        }
        composable(ROUTER_SCREEN_NAME.HOME.name){
            HomeScreen(navcontroller)
        }
    }
}


