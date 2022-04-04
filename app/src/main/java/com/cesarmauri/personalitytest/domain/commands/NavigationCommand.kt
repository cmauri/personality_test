package com.cesarmauri.personalitytest.domain.commands

import com.cesarmauri.personalitytest.R

sealed class NavigationCommand(val id: Int) {
    object Questions: NavigationCommand(R.id.navigation_questions)
    object Result: NavigationCommand(R.id.navigation_result)
}