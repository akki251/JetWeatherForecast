plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    // Got an error that Room doesn't support kapt, migrated to ksp
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.jetweatherforecast"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jetweatherforecast"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.navigation.compose)
//    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    val hiltVers = "2.50"
    implementation("com.google.dagger:hilt-android:$hiltVers")
    implementation("com.google.dagger:hilt-android-gradle-plugin:$hiltVers")
    ksp("com.google.dagger:hilt-compiler:$hiltVers")
    ksp("androidx.hilt:hilt-compiler:1.1.0") // 1.0.0 in course
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    //hilt nav above was 1.0.0-alpha03 in vid


    //material icons
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    // coroutines
    val crVers = "1.7.3" // 1.5.2 in course
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${crVers}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${crVers}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${crVers}")

    //coroutine lifecycle scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0") //2.4.0 in course
    // lifecycle-runtime-ktx already in auto generated dependencies,
    // otherwise it would be here


    //coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    //retrofit & json converter
    val rfVers = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$rfVers")
    implementation("com.squareup.retrofit2:converter-gson:$rfVers")

    //OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0") // 5.0.0-alpha.2 in course

    //Room
    val roomVers = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVers")
    annotationProcessor("androidx.room:room-compiler:$roomVers")
    ksp("androidx.room:room-compiler:$roomVers")
    implementation("androidx.room:room-ktx:$roomVers")
    implementation("androidx.compose.material3:material3-android:1.2.1")


}
