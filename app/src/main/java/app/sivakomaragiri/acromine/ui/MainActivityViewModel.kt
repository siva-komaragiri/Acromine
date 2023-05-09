package app.sivakomaragiri.acromine.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sivakomaragiri.acromine.domain.AcromineRepository
import app.sivakomaragiri.acromine.data.LongFormResponse
import app.sivakomaragiri.acromine.netwok.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: AcromineRepository) : ViewModel() {

    private val _longFormResult = MutableLiveData<NetworkResult<LongFormResponse>>()
    val longFormResponse : LiveData<NetworkResult<LongFormResponse>>
        get() = _longFormResult

    fun getLongForm(sf: String) {
        viewModelScope.launch {
            val response = repository.getLongForms(sf)
            _longFormResult.value = response
        }
    }
}