package com.example.android.politicalpreparedness.di


import com.example.android.politicalpreparedness.data.Election
import com.example.android.politicalpreparedness.data.ElectionDataSource
import com.example.android.politicalpreparedness.data.ElectionRepository
import com.example.android.politicalpreparedness.data.source.local.ElectionDatabase
import com.example.android.politicalpreparedness.data.source.remote.CivicsApi
import com.example.android.politicalpreparedness.ui.election.ElectionsViewModel
import com.example.android.politicalpreparedness.ui.election.VoterInfoViewModel
import com.example.android.politicalpreparedness.ui.launch.LaunchViewModel
import com.example.android.politicalpreparedness.ui.representative.RepresentativeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single { ElectionDatabase.getInstance(get()).electionDao }
}

val apiModule = module {
    single {
        return@single CivicsApi.createRetrofitService()
    }
}

val repositoryModule = module {
    single { ElectionRepository(get(), get()) as ElectionDataSource }
}

val launchModule = module {
    viewModel { LaunchViewModel(get()) }
}

val electionModule = module {
    viewModel { ElectionsViewModel(get(), get()) }
}

val voterInfoModule = module {
    viewModel { (election: Election) -> VoterInfoViewModel(get(), election, get()) }
}

val representativeModule = module {
    viewModel { RepresentativeViewModel(get(), get()) }
}

