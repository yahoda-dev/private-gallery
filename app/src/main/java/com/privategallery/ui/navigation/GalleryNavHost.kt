package com.privategallery.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.privategallery.ui.gallery.GalleryRoute

@Composable
fun GalleryNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Gallery.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppDestination.Gallery.route) {
                GalleryRoute()
            }
        }
    }
}