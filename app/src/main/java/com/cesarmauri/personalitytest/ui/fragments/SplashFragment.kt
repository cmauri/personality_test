package com.cesarmauri.personalitytest.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cesarmauri.personalitytest.R
import com.cesarmauri.personalitytest.databinding.FragmentSplashBinding

class SplashFragment: Fragment() {
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSplashBinding.inflate(inflater, container, false)

        handler.postDelayed(Runnable {
            findNavController().navigate(R.id.action_splashFragment_to_startFragment)
        }, 2000)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null);
    }
}