package com.deerhunter.themoviedatabase.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deerhunter.themoviedatabase.repository.configuration.IConfigurationRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val configurationRepository: IConfigurationRepository) :
    ViewModel() {
    val liveData: LiveData<State>

    init {
        liveData = MutableLiveData(State.Loading)
        viewModelScope.launch {
            configurationRepository.updateConfiguration()
            liveData.value = State.Loaded
        }
    }

    sealed class State {
        object Loading : State()
        object Loaded : State()
        data class Error(val ex: Throwable) : State()
    }
}
