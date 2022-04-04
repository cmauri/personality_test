package com.cesarmauri.personalitytest.domain.repository

import com.cesarmauri.personalitytest.domain.model.QuestionSet

interface QuestionSetRepository {
    suspend fun get(): QuestionSet
}