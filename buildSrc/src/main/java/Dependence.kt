object Dependence {

    private object Versions {
        const val core = "1.9.0"
        const val appcompat = "1.6.1"
        const val material = "1.8.0"
        const val constraint = "2.1.4"
        const val dagger = "2.42"
    }

    object Core {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.5.2"
    }

    object Di {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val kapt = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }


}