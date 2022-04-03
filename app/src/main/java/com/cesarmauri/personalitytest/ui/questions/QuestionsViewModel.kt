package com.cesarmauri.personalitytest.ui.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is questions Fragment"
    }
    val text: LiveData<String> = _text
}