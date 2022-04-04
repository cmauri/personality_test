package com.cesarmauri.personalitytest.ui.questions

import androidx.lifecycle.*
import com.cesarmauri.personalitytest.domain.model.QuestionSet
import com.cesarmauri.personalitytest.domain.repository.QuestionSetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel
@Inject constructor(private val questionSetRepository: QuestionSetRepository) : ViewModel() {

    private lateinit var questionSet: QuestionSet
    private lateinit var answerNumbers: IntArray

    private var currentQuestionNumber = -1
    private val _questionNumber = MutableLiveData<String>()
    private val _questionStatement = MutableLiveData<String>()
    private val _answers = MutableLiveData<List<String>>()
    private val _hasPreviousQuestion = MutableLiveData<Boolean>().apply { value = false }
    private val _hasNextQuestion = MutableLiveData<Boolean>().apply { value = false }
    private val _canGoNext = MutableLiveData<Boolean>().apply { value = false }
    private val _selectedAnswer = MutableLiveData<Int>().apply { value = -1 }

    val questionNumber: LiveData<String> = _questionNumber
    val questionStatement: LiveData<String> = _questionStatement
    val answers: LiveData<List<String>> = _answers
    val hasPreviousQuestion: LiveData<Boolean> = _hasPreviousQuestion
    val hasNextQuestion: LiveData<Boolean> = _hasNextQuestion
    val canGoNext: LiveData<Boolean> = _canGoNext
    val selectedAnswer: LiveData<Int> = _selectedAnswer

    init {
        viewModelScope.launch {
            questionSet = questionSetRepository.get()
            answerNumbers = IntArray(questionSet.questions.size) { -1 }
            selectQuestion(0)
        }
   }

    fun updateSelectedAnswer(i: Int) {
        _selectedAnswer.value = i
        answerNumbers[currentQuestionNumber] = i
        _canGoNext.value = true
    }

    fun goNext() {
        if (currentQuestionNumber < questionSet.questions.size-1)
            selectQuestion(++currentQuestionNumber)
    }

    fun goPrevious() {
        if (currentQuestionNumber > 0)
            selectQuestion(--currentQuestionNumber)
    }

    private fun selectQuestion(number: Int) {
        currentQuestionNumber = number

        val question = questionSet.questions[number]
        val totalQuestions = questionSet.questions.size

        _questionNumber.value = "Question ${number+1} of $totalQuestions"
        _questionStatement.value = question.statement
        _answers.value = question.answers.map { it.text }
        _hasPreviousQuestion.value = number > 0
        _hasNextQuestion.value = number < totalQuestions - 1
        _selectedAnswer.value = answerNumbers[number]
        _canGoNext.value = answerNumbers[number] != -1
    }
}