object Dependence {

    private object Versions {
        const val core = "1.9.0"
        const val appcompat = "1.6.1"
        const val material = "1.8.0"
        const val constraint = "2.1.4"
    }

    object Core{
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    }

}