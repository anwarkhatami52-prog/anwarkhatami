package com.anwarkhatami.sorani.di

import android.content.Context
import androidx.room.Room
import com.anwarkhatami.sorani.data.local.database.SoraniDatabase
import com.anwarkhatami.sorani.data.local.dao.WordDao
import com.anwarkhatami.sorani.data.repository.WordRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideSoraniDatabase(
        @ApplicationContext context: Context
    ): SoraniDatabase {
        return Room.databaseBuilder(
            context,
            SoraniDatabase::class.java,
            SoraniDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideWordDao(database: SoraniDatabase): WordDao {
        return database.wordDao()
    }

    @Singleton
    @Provides
    fun provideWordRepository(wordDao: WordDao): WordRepository {
        return WordRepository(wordDao)
    }
}
