package com.anwarkhatami.sorani.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anwarkhatami.sorani.data.local.dao.WordDao
import com.anwarkhatami.sorani.data.local.entity.WordEntity

@Database(
    entities = [WordEntity::class],
    version = 1,
    exportSchema = true
)
abstract class SoraniDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao

    companion object {
        const val DATABASE_NAME = "sorani_dictionary.db"
    }
}
