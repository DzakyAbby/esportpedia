package com.dzakyabby.esportpedia.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dzakyabby.esportpedia.remote.ApiConfig
import com.dzakyabby.esportpedia.remote.ResultState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import androidx.lifecycle.viewModelScope
import com.dzakyabby.esportpedia.remote.HeroResponseItem


class MainViewModel: ViewModel() {
    private val _HeroResponse = MutableLiveData<ResultState<List<HeroResponseItem>>>()
    val HeroResponse: MutableLiveData<ResultState<List<HeroResponseItem>>> = _HeroResponse

    private val _forceLogout = MutableLiveData(false)
    val forceLogout : LiveData<Boolean> = _forceLogout



    fun getHero() {
        _HeroResponse.value = ResultState.Loading
        viewModelScope.launch {
            try {
                // Panggil API dari file ApiConfig.kt
                val response = ApiConfig.getApiService().getHeroes()
                val heroes = response
                Log.d("test", "getHeroes: ${heroes.size}")

                // Kirim ke result state
                _HeroResponse.postValue(ResultState.Success(heroes))
                Log.d("test", "getHeroes: ${_HeroResponse.value}")
            } catch (e: HttpException) {
                if (e.code() == 401) {
                    _forceLogout.postValue(true)
                }
            } catch (e: Exception) {
                _HeroResponse.postValue(ResultState.Failure(e))
                Log.d("test", "getHeroes: ${e.message}")
            }
        }
    }
}

