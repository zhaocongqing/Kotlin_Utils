plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
//    `maven-publish`
}

//group = "com.learning"
//version = "1.0.0"

android {
    namespace = "com.learning.tools"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
        consumerProguardFiles("consumer-rules.pro")
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

    //发布maven
//    publishing {
//        singleVariant("release") {
//            //发布源码
//            withSourcesJar()
//        }
//    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.activity:activity-ktx:1.9.0")
}

//publishing {
//    publications {
//
//    }
//}