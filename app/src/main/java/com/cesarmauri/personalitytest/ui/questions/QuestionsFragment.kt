package com.cesarmauri.personalitytest.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cesarmauri.personalitytest.R
import com.cesarmauri.personalitytest.databinding.FragmentQuestionsBinding

class QuestionsFragment : Fragment() {

    private lateinit var questionsViewModel: QuestionsViewModel
    private var _binding: FragmentQuestionsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        questionsViewModel =
            ViewModelProvider(this).get(QuestionsViewModel::class.java)

        FragmentQuestionsBinding.inflate(inflater, container, false).let { binding ->
            _binding = binding
            val root: View = binding.root

            setObservers(binding)
            setListener(binding)

            return root
        }
    }

    private fun setObservers(binding: FragmentQuestionsBinding) {
        questionsViewModel.questionNumber.observe(viewLifecycleOwner, {
            binding.questionNumber.text = it
        })

        questionsViewModel.questionStatement.observe(viewLifecycleOwner, {
            binding.questionStatement.text = it
        })

        questionsViewModel.answer1.observe(viewLifecycleOwner, {
            binding.answer1.text = "A: $it"
        })

        questionsViewModel.answer2.observe(viewLifecycleOwner, {
            binding.answer2.text = "B: $it"
        })

        questionsViewModel.answer3.observe(viewLifecycleOwner, {
            binding.answer3.text = "C: $it"
        })

        questionsViewModel.answer4.observe(viewLifecycleOwner, {
            binding.answer4.text = "D: $it"
        })

        questionsViewModel.hasPreviousQuestion.observe(viewLifecycleOwner, {
            binding.buttonPrevious.visibility = if (it) View.VISIBLE else View.GONE
        })

        questionsViewModel.hasNextQuestion.observe(viewLifecycleOwner, {
            binding.buttonNext.visibility = if (it) View.VISIBLE else View.GONE
        })

        questionsViewModel.canGoNext.observe(viewLifecycleOwner, {
            binding.buttonNext.isEnabled = it
        })

        questionsViewModel.selectedAnswer.observe(viewLifecycleOwner, {
            for ((i, answer) in answersAsArray(binding).withIndex()) {
                if (i == it - 1) selectAnswer(answer)
                else unSelectAnswer(answer)
            }
        })
    }

    private fun setListener(binding: FragmentQuestionsBinding) {
        for ((i, answer) in answersAsArray(binding).withIndex()) {
            answer.setOnClickListener { questionsViewModel.updateSelectedAnswer(i+1) }
        }
    }

    private fun selectAnswer(answer: TextView) {
        answer.background = ContextCompat.getDrawable(requireContext(), R.drawable.back_selected)
    }

    private fun unSelectAnswer(answer: TextView) {
        answer.background = ContextCompat.getDrawable(requireContext(), R.drawable.back_not_selected)
    }

    private fun answersAsArray(binding: FragmentQuestionsBinding): Array<TextView> {
        return arrayOf(binding.answer1, binding.answer2, binding.answer3, binding.answer4)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}