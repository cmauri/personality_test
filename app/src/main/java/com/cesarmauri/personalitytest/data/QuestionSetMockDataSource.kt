package com.cesarmauri.personalitytest.data

import com.cesarmauri.personalitytest.domain.model.Answer
import com.cesarmauri.personalitytest.domain.model.Question
import com.cesarmauri.personalitytest.domain.model.QuestionSet
import com.cesarmauri.personalitytest.domain.repository.QuestionSetRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class QuestionSetMockDataSource : QuestionSetRepository {
    override suspend fun get(): QuestionSet = withContext(Dispatchers.IO) {
        // simulate time to get a network response
        delay(500)

        QuestionSet(listOf(
            Question("You arrive at the party, but no one you know is there yet. " +
                    "What would you do?", listOf(
                Answer("Take some drink and join the first conversation you find", 10),
                Answer("Hide in a corner and stare you cellphone", 3),
                Answer("Go next to other people and wait until they invite to the conversation", 5),
                Answer("Go out, text your friends, and try again later", 0),
            )),
            Question("What about being alone?", listOf(
                Answer("You absolutely hate it", 10),
                Answer("You absolutely love it. You prefer that than being with people", 0),
                Answer("You to be with people sometimes, but too much gets you down", 3),
                Answer("You enjoy both being alone and being around people", 5),
            )),
            Question("You're having a discussion with a colleague regarding a project " +
                    "you are running. You:", listOf(
                Answer("Don’t dare contradict her", 0),
                Answer("Think that she is obviously right", 2),
                Answer("Defend your own point of view, tooth and nail", 10),
                Answer("Continuously interrupt your colleague", 5),
            )),
            Question("You go dinning outside with friends and you have a hard " +
                    "time when people:", listOf(
                Answer("Ask you to tell a story in front of everyone else", 2),
                Answer("Talk privately between themselves", 6),
                Answer("Hang around you all evening", 3),
                Answer("Always drag the conversation back to themselves", 8),
            )),
        ))
    }
}