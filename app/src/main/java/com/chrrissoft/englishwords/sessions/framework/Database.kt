package com.chrrissoft.englishwords.sessions.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chrrissoft.inglishwords.data.session.SessionDao

@Database(entities = [], version = 1)
abstract class Database : RoomDatabase() {
    abstract val sessionDao: SessionDao
}