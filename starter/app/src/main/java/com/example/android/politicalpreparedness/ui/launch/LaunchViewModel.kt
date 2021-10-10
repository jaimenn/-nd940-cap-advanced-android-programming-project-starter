package com.example.android.politicalpreparedness.ui.launch

import android.app.Application
import com.example.android.politicalpreparedness.core.base.BaseViewModel
import com.example.android.politicalpreparedness.core.base.NavigationCommand

class LaunchViewModel(val app: Application) : BaseViewModel(app) {
    fun onUpcomingElectionsClicked() {
        navigationCommand.value =
            NavigationCommand.To(LaunchFragmentDirections.actionLaunchFragmentToElectionsFragment())
    }

    fun onFindRepresentationsClicked() {
        navigationCommand.value =
            NavigationCommand.To(LaunchFragmentDirections.actionLaunchFragmentToRepresentativeFragment())
    }
}
