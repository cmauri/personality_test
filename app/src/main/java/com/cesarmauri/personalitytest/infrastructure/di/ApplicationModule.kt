package com.cesarmauri.personalitytest.infrastructure.di

import android.content.Context
import com.cesarmauri.personalitytest.data.QuestionSetMockDataSource
import com.cesarmauri.personalitytest.domain.repository.QuestionSetRepository
import com.cesarmauri.personalitytest.infrastructure.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: App) {
    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideQuestionSetRepository(): QuestionSetRepository = QuestionSetMockDataSource()
}