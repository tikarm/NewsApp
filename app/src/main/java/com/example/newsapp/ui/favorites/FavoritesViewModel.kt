package com.example.newsapp.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.domain.model.News
import com.example.newsapp.domain.use_case.GetFavoritesUseCase
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(private val getFavoritesUseCase: GetFavoritesUseCase) :
    ViewModel() {

    private val textMessageMutableLiveData = MutableLiveData<Boolean>()
    val textMessageLiveData: LiveData<Boolean> = textMessageMutableLiveData

//    fun getFavoriteNews(): LiveData<List<News>?> {
//        getFavoritesUseCase.invoke().onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    loadingMutableLiveData.postValue(false)
//                    textMessageMutableLiveData.postValue(null)
//                    favoritesLiveData = result.data!!
//                }
//                is Resource.Error -> {
//                    textMessageMutableLiveData.postValue(result.message)
//                    loadingMutableLiveData.postValue(false)
//                }
//
//                is Resource.Loading -> {
//                    loadingMutableLiveData.postValue(true)
//                }
//            }
//        }.launchIn(viewModelScope)
//        return favoritesLiveData
//    }

    fun getFavoriteNews() : LiveData<List<News>?>{
        return getFavoritesUseCase.invoke()
    }

    fun setTextMessage(state : Boolean){
        textMessageMutableLiveData.postValue(state)
    }
}