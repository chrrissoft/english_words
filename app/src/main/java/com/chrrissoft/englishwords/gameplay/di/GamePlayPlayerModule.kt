package com.chrrissoft.englishwords.gameplay.di

import com.chrrissoft.englishwords.gameplay.framework.GamePlayPlayerImpl
import com.chrrissoft.inglishwords.domian.gameplay.GamePlayPlayer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class GamePlayPlayerModule {
    @Binds
    abstract fun provideGamePlayPlayer(
        impl: GamePlayPlayerImpl
    ) : GamePlayPlayer
}
