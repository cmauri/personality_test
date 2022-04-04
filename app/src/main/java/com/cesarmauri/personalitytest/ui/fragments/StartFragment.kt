package com.cesarmauri.personalitytest.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cesarmauri.personalitytest.R
import com.cesarmauri.personalitytest.databinding.FragmentStartBinding

class StartFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartBinding.inflate(inflater, container, false)
        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_questions)
        }
        return binding.root

    }
}
