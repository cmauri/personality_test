package com.cesarmauri.personalitytest.infrastructure.di

import com.cesarmauri.personalitytest.data.QuestionSetMockDataSource
import com.cesarmauri.personalitytest.domain.repository.QuestionSetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class ApplicationModule() {
    @Binds
    abstract fun provideQuestionSetRepository(p: QuestionSetMockDataSource): QuestionSetRepository
}