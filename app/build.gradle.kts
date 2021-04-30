plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.apollographql.apollo").version("2.5.5")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("com.example.roomtrial")
        minSdkVersion(23)
        targetSdkVersion(30)
        versionCode(1)
        versionName("1.0")

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val room_version = "2.2.6"
    val kotlin_version = "1.4.32"

    kapt("androidx.room:room-compiler:$room_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    
    implementation("androidx.room:room-runtime:$room_version")   
    implementation("androidx.room:room-ktx:$room_version")    
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.0-rc01")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.0-rc01")
    implementation("androidx.paging:paging-runtime-ktx:2.1.2")
    implementation("androidx.security:security-crypto:1.0.0-rc02")

    implementation("com.google.android.material:material:1.3.0")    
    implementation("com.apollographql.apollo:apollo-runtime:2.5.5")
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.5.5")
    implementation("com.theartofdev.edmodo:android-image-cropper:2.8.+")

    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

}

apollo {
    generateKotlinModels.set(true)
}