plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    // 发布插件
//    id("com.vanniktech.maven.publish")
}

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
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.activity:activity-ktx:1.9.1")
    implementation("com.google.code.gson:gson:2.11.0")
}

// 可使用 https://central.sonatype.org/register/central-portal/#waituntil
//publishing {
//    repositories {
//        maven {
//            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
//            credentials {
//                username = project.findProperty("ossrhUsername") as String
//                password = project.findProperty("ossrhPassword") as String
//            }
//        }
//    }
//}


//mavenPublishing {
//    configure(
//        AndroidMultiVariantLibrary(
//            sourcesJar = true,
//            publishJavadocJar = true
//        )
//    )
//
//    publishToMavenCentral(SonatypeHost.S01,true)
//
//    coordinates(
//        project.properties["GROUP_ID"] as String,
//        project.properties["ARTIFACT_ID"] as String,
//        project.properties["VERSION_NAME"] as String,
//    )
//
//    pom {
//        name.set(project.properties["POM_NAME"] as String)
//        description.set(project.properties["POM_DESCRIPTION"] as String)
//        inceptionYear.set(project.properties["POM_INCEPTION_YEAR"] as String)
//        url.set(project.properties["POM_URL"] as String)
//
//        licenses {
//            license {
//                name.set(project.properties["POM_LICENCE_NAME"] as String)
//                url.set(project.properties["POM_LICENCE_URL"] as String)
//            }
//        }
//
//        developers {
//            developer {
//                id.set(project.properties["POM_DEVELOPER_ID"] as String)
//                name.set(project.properties["POM_DEVELOPER_NAME"] as String)
//                email.set(project.properties["POM_DEVELOPER_EMAIL"] as String)
//                url.set(project.properties["POM_DEVELOPER_URL"] as String)
//            }
//        }
//
//        scm {
//            connection.set(project.properties["POM_SCM_CONNECTION"] as String)
//            developerConnection.set(project.properties["POM_SCM_DEV_CONNECTION"] as String)
//            url.set(project.properties["POM_SCM_URL"] as String)
//        }
//    }
//
//    signAllPublications()
//}