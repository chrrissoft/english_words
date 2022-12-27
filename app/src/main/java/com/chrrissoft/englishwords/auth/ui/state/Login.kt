package com.chrrissoft.englishwords.auth.ui.state

sealed interface Login {
    object SingIn : Login
    object SingUp : Login
}