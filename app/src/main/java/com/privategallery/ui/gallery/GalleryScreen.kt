package com.privategallery.ui.gallery

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.privategallery.ui.component.ItemCard

@Composable
fun GalleryRoute(
    viewModel: GalleryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var pendingCardIndex by rememberSaveable {
        mutableIntStateOf(-1)
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
    ) { uri ->
        val cardIndex = pendingCardIndex

        if (uri != null && cardIndex >= 0) {
            viewModel.onImagePicked(
                cardIndex = cardIndex,
                imageUri = uri.toString(),
            )
        }

        pendingCardIndex = -1
    }

    GalleryScreen(uiState = uiState, onCardClick = { cardIndex ->
        pendingCardIndex = cardIndex

        photoPickerLauncher.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    })
}

@Composable
fun GalleryScreen(
    uiState: GalleryUiState,
    onCardClick: (Int) -> Unit,
) {
    val context = LocalContext.current;

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // content scope
        items(uiState.imageUris.size, key = { index -> index }) { index ->
            ItemCard(
                imageUri = uiState.imageUris[index],
                onCustomClick = {
                    onCardClick(index)
                }
            )
        }
    }
}