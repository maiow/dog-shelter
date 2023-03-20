object Dependence {

    private object Versions {
        const val core = "1.9.0"
        const val appcompat = "1.6.1"
        const val material = "1.8.0"
        const val constraint = "2.1.4"
        const val dagger = "2.44.2"
        const val googleServices = "4.3.15"
        const val firebaseBom = "31.2.1"
        const val nav_version = "2.5.3"

    }

    object Core {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.5.2"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"
    }

    object Di {
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
        const val kapt = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }

    object Firebase {
        const val googleServices = "com.google.gms:google-services:${Versions.googleServices}"
        const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
        const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
        const val firebaseDatabase = "com.google.firebase:firebase-database-ktx" //Realtime Database
        //const val firestore = "com.google.firebase:firebase-firestore-ktx" //Cloud Firestore
        //const val firebaseCore = "com.google.firebase:firebase-core"
        //const val firebaseStorage = "com.google.firebase:firebase-storage-ktx" //Cloud Storage
    }


}