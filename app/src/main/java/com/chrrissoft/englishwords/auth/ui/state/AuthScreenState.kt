package com.chrrissoft.englishwords.auth.ui.state


data class AuthScreenState(
    val login: Login = Login.SingUp,
    val provider: AuthProvider = AuthProvider.None,
    val singInEmailState: SingInEmailState = SingInEmailState(),
    val singUpEmailState: SingUpEmailState = SingUpEmailState(),
    val authProviderResultState: AuthProviderResultState = AuthProviderResultState.None,
    val firebaseAuthResultState: FirebaseAuthResultState = FirebaseAuthResultState.None
) {
    enum class AuthProvider { Google, Facebook, None }

    data class EmailData(
        val text: String = "", val isInvalid: Boolean = false
    )

    data class PassData(
        val text: String = "", val isInvalid: Boolean = false
    )

    data class SingInEmailState(
        val email: EmailData = EmailData(),
        val pass: PassData = PassData(),
        val passIsVisible: Boolean = false
    )

    data class SingUpEmailState(
        val email: EmailData = EmailData(),
        val pass1: PassData = PassData(),
        val pass2: PassData = PassData(),
        val passIsVisible: Boolean = false
    )

}

