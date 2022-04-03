package com.cesarmauri.personalitytest.domain.model

/**
 * Model classes
 *
 * For the sake of simplicity, let's assume each answer maps to an extroversion rate between
 * 0 and 10, where 0 means maximum introversion and 10 maximum extroversion. The final score
 * is also an integer between 0 an 10.
 */
data class QuestionSet(val questions: List<Question>) {
    fun computeScore(answers: List<Int>): Int {
        val validAnswers = answers.take(questions.size)

        if (validAnswers.isEmpty()) return 0

        return validAnswers.zip(questions) { i, question -> question.answers[i].score }
            .sum() / validAnswers.size
    }

    fun isExtrovert(score: Int): Boolean = score >= 5
}

data class Question(val statement: String, val answers: List<Answer>)

data class Answer(val text: String, val score: Int)