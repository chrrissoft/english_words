package com.chrrissoft.englishwords.auth.di

import com.chrrissoft.englishwords.InglishWordsApp
import com.chrrissoft.englishwords.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SingInRequest

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SingUpRequest

@Module
@InstallIn(SingletonComponent::class)
class GoogleIdentityModule {

    @Provides
    fun provideSingInClient(
        ctx: InglishWordsApp
    ) : SignInClient = Identity.getSignInClient(ctx)

//  TODO("add web client id")
    @Provides
    @SingInRequest
    fun provideSingInRequest(
        ctx: InglishWordsApp
    ) : BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(ctx.getString(R.string.your_web_client_id))
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .setAutoSelectEnabled(true)
            .build()
    }

    //  TODO("add web client id")
    @Provides
    @SingUpRequest
    fun provideSingUpRequest(
        ctx: InglishWordsApp
    ) : BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(ctx.getString(R.string.your_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build())
            .build()
    }
}
