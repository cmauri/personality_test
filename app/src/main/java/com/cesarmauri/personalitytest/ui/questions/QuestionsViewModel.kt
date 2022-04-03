package com.cesarmauri.personalitytest.ui.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {

    private val _questionNumber = MutableLiveData<String>().apply {
        value = "Question 1 of 10"
    }

    private val _questionStatement = MutableLiveData<String>().apply {
        value = "This is the question statament. Lorem fistrum hasta luego Lucas papaar papaar " +
                "hasta luego Lucas apetecan benemeritaar quietooor."
    }

    private val _answer1 = MutableLiveData<String>().apply {
        value = "This is the first answer"
    }

    private val _answer2 = MutableLiveData<String>().apply {
        value = "This is the second answer"
    }

    private val _answer3 = MutableLiveData<String>().apply {
        value = "This is the third answer"
    }

    private val _answer4 = MutableLiveData<String>().apply {
        value = "This is the fourth answer"
    }

    private val _hasPreviousQuestion = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val _hasNextQuestion = MutableLiveData<Boolean>().apply {
        value = true
    }

    private val _canGoNext = MutableLiveData<Boolean>().apply {
        value = false
    }

    private val _selectedAnswer = MutableLiveData<Int>().apply {
        value = 0
    }

    val questionNumber: LiveData<String> = _questionNumber
    val questionStatement: LiveData<String> = _questionStatement
    val answer1: LiveData<String> = _answer1
    val answer2: LiveData<String> = _answer2
    val answer3: LiveData<String> = _answer3
    val answer4: LiveData<String> = _answer4
    val hasPreviousQuestion: LiveData<Boolean> = _hasPreviousQuestion
    val hasNextQuestion: LiveData<Boolean> = _hasNextQuestion
    val canGoNext: LiveData<Boolean> = _canGoNext
    val selectedAnswer: LiveData<Int> = _selectedAnswer
    fun updateSelectedAnswer(i: Int) {
        _selectedAnswer.value = i
        _canGoNext.value = true
    }
}