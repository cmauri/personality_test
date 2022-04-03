package com.cesarmauri.personalitytest.ui.questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
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

            val textView: TextView = binding.textQuestions
            questionsViewModel.text.observe(viewLifecycleOwner, Observer {
                textView.text = it
            })
            return root
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}