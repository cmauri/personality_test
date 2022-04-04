package com.cesarmauri.personalitytest.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cesarmauri.personalitytest.R
import com.cesarmauri.personalitytest.databinding.FragmentQuestionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : Fragment() {
    private val questionsViewModel: QuestionsViewModel by viewModels()
    private var _binding: FragmentQuestionsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        FragmentQuestionsBinding.inflate(inflater, container, false).let { binding ->
            _binding = binding
            val root: View = binding.root

            setObservers(binding)
            setListener(binding)

            return root
        }
    }

    private fun setObservers(binding: FragmentQuestionsBinding) {
        questionsViewModel.questionNumber.observe(viewLifecycleOwner) {
            binding.questionNumber.text = it
        }

        questionsViewModel.questionStatement.observe(viewLifecycleOwner) {
            binding.questionStatement.text = it
        }

        questionsViewModel.answers.observe(viewLifecycleOwner) {
            val answerViews = answersAsArray(binding)
            for (view in answerViews) view.visibility = View.GONE

            var prefix = 'A'
            for (pair in answerViews.zip(it)) {
                pair.first.visibility = View.VISIBLE
                pair.first.text = "$prefix: ${pair.second}"
                prefix++
            }
        }

        questionsViewModel.hasPreviousQuestion.observe(viewLifecycleOwner) {
            binding.buttonPrevious.visibility = if (it) View.VISIBLE else View.GONE
        }

        questionsViewModel.hasNextQuestion.observe(viewLifecycleOwner) {
            binding.buttonNext.visibility = if (it) View.VISIBLE else View.GONE
        }

        questionsViewModel.canGoNext.observe(viewLifecycleOwner) {
            binding.buttonNext.isEnabled = it
        }

        questionsViewModel.selectedAnswer.observe(viewLifecycleOwner) {
            for ((i, answer) in answersAsArray(binding).withIndex()) {
                if (i == it) selectAnswer(answer)
                else unSelectAnswer(answer)
            }
        }
    }

    private fun setListener(binding: FragmentQuestionsBinding) {
        for ((i, answer) in answersAsArray(binding).withIndex()) {
            answer.setOnClickListener { questionsViewModel.updateSelectedAnswer(i) }
        }

        binding.buttonPrevious.setOnClickListener { questionsViewModel.goPrevious() }
        binding.buttonNext.setOnClickListener { questionsViewModel.goNext() }
    }

    private fun selectAnswer(answer: TextView) {
        answer.background = ContextCompat.getDrawable(requireContext(), R.drawable.back_selected)
    }

    private fun unSelectAnswer(answer: TextView) {
        answer.background = ContextCompat.getDrawable(requireContext(), R.drawable.back_not_selected)
    }

    // for simplicity, a maximum of 4 answers per question is assumed
    private fun answersAsArray(binding: FragmentQuestionsBinding): Array<TextView> {
        return arrayOf(binding.answer1, binding.answer2, binding.answer3, binding.answer4)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
