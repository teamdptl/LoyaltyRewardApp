plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("io.realm.kotlin") version "1.11.0"
}

android {
    namespace = "com.example.loyaltyrewardapp"
    compileSdk = 34

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    defaultConfig {
        applicationId = "com.example.loyaltyrewardapp"
        minSdk = 29
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
    implementation("io.coil-kt:coil:2.6.0")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
    implementation("androidx.camera:camera-mlkit-vision:1.4.0-alpha05")
    implementation("com.google.android.gms:play-services-location:20.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth")

    val composeBom = platform("androidx.compose:compose-bom:2024.02.02")
    implementation("androidx.compose:compose-bom:2024.05.00")
    androidTestImplementation("androidx.compose:compose-bom:2024.05.00")

    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.9.0")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.8.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.mlkit:barcode-scanning:17.2.0")
    implementation("androidx.camera:camera-camera2:1.3.3")
    implementation("androidx.camera:camera-lifecycle:1.3.3")
    implementation("androidx.camera:camera-view:1.3.3")
    implementation("androidx.camera:camera-core:1.3.3")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Coil load image
    implementation("io.coil-kt:coil-compose:2.6.0")
//    implementation("com.google.accompanist:accompanist-glide:0.17.0")


    // QrCode display
    implementation("com.lightspark:compose-qr-code:1.0.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // RealmDB local key-value database
    implementation("io.realm.kotlin:library-base:1.16.0")
    implementation("io.realm.kotlin:library-sync:1.16.0") // If using Device Sync

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0") // If using coroutines with the SDK
    // https://mvnrepository.com/artifact/androidx.compose.runtime/runtime-livedata
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.7")

    // Data store
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    implementation ("androidx.activity:activity-compose:1.9.0")


    // State
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.7")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation ("androidx.activity:activity-ktx:1.9.0")

    // File picker
    implementation("io.github.huhx:compose-image-picker:1.0.8")

    // Location
    implementation("com.github.BirjuVachhani:locus-android:3.0.1")

    // QR Scan
    implementation("io.github.kalinjul.easyqrscan:scanner:0.1.3")

    // Shimmer Loading
    implementation("com.valentinilk.shimmer:compose-shimmer:1.3.0")
}