package com.chrrissoft.englishwords.auth.ui.state

import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.identity.SignInCredential

sealed interface AuthProviderCredential {
    data class GoogleCredential(val credential: SignInCredential) : AuthProviderCredential
    data class FacebookCredential(val credential: LoginResult) : AuthProviderCredential
    object EmailCredential : AuthProviderCredential
}