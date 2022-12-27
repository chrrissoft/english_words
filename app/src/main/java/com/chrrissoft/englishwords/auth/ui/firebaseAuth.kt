package com.chrrissoft.englishwords.auth.ui

import androidx.compose.runtime.Composable
import com.chrrissoft.englishwords.auth.ui.state.FirebaseAuthResultState
import com.chrrissoft.englishwords.auth.ui.state.FirebaseAuthResultState.*

@Composable
fun FireBaseResultUi(state: FirebaseAuthResultState) {
    when (state) {
        None -> {}
        Cancel -> {}
        Loading -> {}
        is Error -> {}
        is Success -> {}
    }
}