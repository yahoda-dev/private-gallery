package com.privategallery

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.privategallery.ui.navigation.GalleryNavHost
import com.privategallery.ui.theme.PrivateGalleryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. (선택) 보안 앱: 스크린샷 및 최근 앱 미리보기 캡처 차단
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
        // 2. 시스템 UI(Edge-to-Edge) 활성화
        enableEdgeToEdge()

        // 3. Compose 최상위 테마 및 NavHost 연결
        setContent {
            PrivateGalleryTheme {
                GalleryNavHost()
            }
        }
    }
}
