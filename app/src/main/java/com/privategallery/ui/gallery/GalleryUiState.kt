package com.privategallery.ui.gallery

data class GalleryUiState(
    val imageUris: List<String?> = List(4) { null },
    val isLoading: Boolean = false,
    val error:String? = null,
){

}
