package com.chrrissoft.englishwords.auth.di

import com.chrrissoft.inglishwords.data.auth.FacebookAuthRepo
import com.chrrissoft.englishwords.auth.framework.FacebookAuthRepoImpl
import com.chrrissoft.englishwords.auth.framework.FirebaseAuthRepoImpl
import com.chrrissoft.englishwords.auth.framework.GoogleAuthRepoImpl
import com.chrrissoft.inglishwords.data.auth.FirebaseAuthRepo
import com.chrrissoft.inglishwords.data.auth.GoogleAuthRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposModule {

    @Binds
    abstract fun provideGoogleAuthRepo(
        repoImpl: GoogleAuthRepoImpl
    ) : GoogleAuthRepo

    @Binds
    abstract fun provideFirebaseAuthRepo(
        repoImpl: FirebaseAuthRepoImpl
    ) : FirebaseAuthRepo

    @Binds
    abstract fun provideFacebookAuthRepo(
        repoImpl: FacebookAuthRepoImpl
    ) : FacebookAuthRepo

}
