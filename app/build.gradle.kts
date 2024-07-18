plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 34
    namespace = "com.example.veritablejeu"

    defaultConfig {
        applicationId = "com.example.veritablejeu"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "Bêta 1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildToolsVersion = "34.0.0"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.common)
    implementation(libs.firebase.firestore)
    implementation(libs.core)
    testImplementation(libs.junit)
    testImplementation (libs.mockito.core)
    testImplementation (libs.robolectric)
    androidTestImplementation(libs.extJunit)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation (libs.junit.v113)
    androidTestImplementation (libs.espresso.core.v340)

    // Room dependencies
    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)
    annotationProcessor(libs.roomCompiler) // Utilisation du compilateur Room avec le traitement des annotations pour Java

    implementation(platform(libs.firebase.bom))

    implementation(libs.gson)

}