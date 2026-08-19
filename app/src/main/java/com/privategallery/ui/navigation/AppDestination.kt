package com.privategallery.ui.navigation

sealed interface AppDestination {
    val route: String

    /// 잠금 해제 화면 경로
    object Unlock : AppDestination {
        override val route: String = "unlock"
    }
    /// 갤러리 화면 경로
    object Gallery : AppDestination {
        override val route: String = "gallery"
    }
    /// 사진 상세 보기 화면 경로
    object Detail : AppDestination {
        override val route: String = "detail/{photoId}"
        fun createRoute(photoId: Long): String = "detail/$photoId"
    }
    /// 설정 화면 경로
    object Settings : AppDestination {
        override val route: String = "settings"
    }
}