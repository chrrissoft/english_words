package com.chrrissoft.englishwords.gameplay.di

import android.media.MediaPlayer
import com.chrrissoft.englishwords.InglishWordsApp
import com.chrrissoft.englishwords.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MediaPlayerModule {

    @Provides
    fun provideMediaPlayer(
        app: InglishWordsApp
    ) : MediaPlayer {
        return MediaPlayer.create(app, R.raw.word_fail)
    }
}
