package com.cesarmauri.personalitytest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.cesarmauri.personalitytest.R
import com.cesarmauri.personalitytest.databinding.FragmentResultBinding
import com.cesarmauri.personalitytest.ui.model.QuestionsViewModel

class ResultFragment: Fragment() {
    private val viewModel: QuestionsViewModel by activityViewModels()
    private var _binding: FragmentResultBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        FragmentResultBinding.inflate(inflater, container, false).let { binding ->
            _binding = binding
            val root: View = binding.root

            viewModel.isExtrovert.observe(viewLifecycleOwner) { isExtrovert ->
                if (isExtrovert) {
                    binding.resultTitle.setText(R.string.title_you_are_extrovert)
                    binding.imageExtrovert.visibility = View.VISIBLE
                    binding.imageIntrovert.visibility = View.GONE
                }
                else {
                    binding.resultTitle.setText(R.string.title_you_are_introvert)
                    binding.imageExtrovert.visibility = View.GONE
                    binding.imageIntrovert.visibility = View.VISIBLE
                }
            }

            viewModel.score.observe(viewLifecycleOwner) { score ->
                binding.resultScore.text = getString(R.string.result_your_score, score)
            }

            return root
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}