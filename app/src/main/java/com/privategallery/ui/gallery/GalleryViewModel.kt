package com.privategallery.ui.gallery

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class GalleryViewModel @Inject constructor(

): ViewModel() {
    private val _uiState = MutableStateFlow(GalleryUiState())
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    fun onImagePicked(
        cardIndex: Int,
        imageUri: String,
    ){
        _uiState.update { currentState ->
            currentState.copy(
                imageUris = currentState.imageUris.mapIndexed { index, oldUri ->
                    if (index == cardIndex) imageUri else oldUri
                }
            )
        }
    }
}