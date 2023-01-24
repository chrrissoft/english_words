package com.chrrissoft.englishwords.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrrissoft.englishwords.auth.ui.AuthScreen
import com.chrrissoft.englishwords.navigation.Screen.AuthScreen

@Composable
fun App(activity: Activity) {
    val navigation = rememberNavController()

    NavHost(navigation, AuthScreen.route) {
        composable(AuthScreen.route) {
            AuthScreen(activity)
        }
    }

}