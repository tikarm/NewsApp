package com.example.newsapp.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.common.Resource
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.use_case.GetNewsFromLocalUseCase
import com.example.newsapp.domain.use_case.GetNewsFromRemoteUseCase
import com.example.newsapp.domain.use_case.SetNewsAsFavoriteUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

private const val TAG = "NewsViewModel"

class NewsViewModel(
    private val getNewsFromRemoteUseCase: GetNewsFromRemoteUseCase,
    private val getNewsFromLocalUseCase: GetNewsFromLocalUseCase,
    private val setNewsAsFavoriteUseCase: SetNewsAsFavoriteUseCase
) : ViewModel() {

    private val loadingMutableLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = loadingMutableLiveData

    private val textMessageMutableLiveData = MutableLiveData<Boolean>()
    val textMessageLiveData: LiveData<Boolean> = textMessageMutableLiveData

    fun getNews() {
        getNewsFromRemoteUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.e(TAG, "getNews: Got news successfully from API")
                    loadingMutableLiveData.postValue(false)
                }
                is Resource.Error -> {
//                    textMessageMutableLiveData.postValue(result.data == null)
                    loadingMutableLiveData.postValue(false)
                }

                is Resource.Loading -> {
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getNewsFromLocal(): LiveData<List<News>?> {
        return getNewsFromLocalUseCase.invoke()
    }

    fun setNewsAsFavorite(isFavorite: Boolean, id: String) {
        viewModelScope.launch { setNewsAsFavoriteUseCase.invoke(isFavorite, id) }
    }

    fun setTextMessage(state: Boolean) {
        textMessageMutableLiveData.postValue(state)
    }

    fun setProgressBar(state: Boolean) {
        loadingMutableLiveData.postValue(state)
    }
}