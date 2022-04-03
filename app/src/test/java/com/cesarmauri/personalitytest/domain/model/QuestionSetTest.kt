package com.cesarmauri.personalitytest.domain.model

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class QuestionSetTest {

    private lateinit var questions: QuestionSet

    @Before
    fun setUp() {
        questions = QuestionSet(listOf(
            Question("First question", listOf(
                Answer("A", 2), Answer("B", 5), Answer("C", 10)
            )),
            Question("First question", listOf(
                Answer("A", 6), Answer("B", 8), Answer("C", 2)
            )),
        ))
    }

    @Test
    fun `no answers add up to 0`() {
        assertEquals(0, questions.computeScore(listOf()))
    }

    @Test
    fun `one answer should add up to its own value`() {
        assertEquals(5, questions.computeScore(listOf(1)))
    }

    @Test
    fun `A_C should add up 2`() {
        assertEquals(2, questions.computeScore(listOf(0, 2)))
    }

    @Test
    fun `C_B should add up 9`() {
        assertEquals(9, questions.computeScore(listOf(2, 1)))
    }

    @Test
    fun `additional answers are ignored`() {
        assertEquals(9, questions.computeScore(listOf(2, 1, 0)))
    }

    @Test
    fun `no questions should add up to 0`() {
        assertEquals(0, QuestionSet(listOf()).computeScore(listOf(1)))
    }

    @Test
    fun `scores are floored`() {
        assertEquals(5, questions.computeScore(listOf(1, 0)))
    }

    @Test
    fun `less than zero means introvert`() {
        assertFalse(questions.isExtrovert(4))
    }

    @Test
    fun `more than five means extrovert`() {
        assertTrue(questions.isExtrovert(7))
    }

    @Test
    fun `five means extrovert`() {
        assertTrue(questions.isExtrovert(5))
    }
}
