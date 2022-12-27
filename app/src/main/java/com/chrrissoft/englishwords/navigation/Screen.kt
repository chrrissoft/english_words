package com.chrrissoft.englishwords.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    private val baseRoute: String,
    private val arg: List<NavArg> = emptyList()
) {

    val route = run {
        val arg = arg.map { "{${it.key}}" }
        listOf(baseRoute).plus(arg).joinToString("/")
    }

    val args = arg.map {
        navArgument(it.key) { type = it.type }
    }

    object SplashScreen : Screen("splash")
    object AuthScreen : Screen("auth")

}

enum class NavArg(val key: String, val type: NavType<*>)