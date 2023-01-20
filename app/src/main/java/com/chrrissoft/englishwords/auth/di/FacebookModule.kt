package com.chrrissoft.englishwords.auth.di

import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FacebookModule {

    @Provides
    @Singleton
    fun provideCallback() : CallbackManager {
        return CallbackManager.Factory.create()
    }

    @Provides
    @Singleton
    fun provideManager() : LoginManager {
        return LoginManager.getInstance()
    }

}