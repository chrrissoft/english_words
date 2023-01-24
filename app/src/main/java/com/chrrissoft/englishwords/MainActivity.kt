package com.chrrissoft.englishwords

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.chrrissoft.englishwords.auth.ui.AuthViewModel
import com.chrrissoft.englishwords.gameplay.ui.GamePlayScreen
import com.chrrissoft.englishwords.navigation.App
import com.chrrissoft.englishwords.theme.EnglishWordsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val loginViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InglishWordsApp { App(this) }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        loginViewModel.callbackManager.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)
    }
}

@Composable
private fun InglishWordsApp(content: @Composable () -> Unit) {
    val remember = rememberSystemUiController()
    val color = MaterialTheme.colorScheme.secondaryContainer
    LaunchedEffect(Unit) {
        remember.setStatusBarColor(color)
    }
    EnglishWordsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) { GamePlayScreen() }
    }
}