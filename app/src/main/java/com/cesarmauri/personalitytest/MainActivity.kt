package com.cesarmauri.personalitytest

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.cesarmauri.personalitytest.databinding.ActivityMainBinding
import com.cesarmauri.personalitytest.domain.commands.NavigationCommand
import com.cesarmauri.personalitytest.ui.model.QuestionsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: QuestionsViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeNavigation()
    }

    private fun observeNavigation() {
        viewModel.navigation.observe(this) { event ->
            event.getContentIfNotHandled()?.let { command ->
                handleNavigation(command)
            }
        }
    }

    private fun handleNavigation(command: NavigationCommand) {
        findNavController(R.id.nav_host_fragment_activity_main).navigate(command.id)
    }
}