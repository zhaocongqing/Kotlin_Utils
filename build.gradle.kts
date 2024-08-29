plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    // 发布插件
    id("maven-publish")
    id("signing")
}

android {
    namespace = "com.learning.tools"
    compileSdk = 34

    defaultConfig {
        minSdk = 23
        consumerProguardFiles("consumer-rules.pro")
    }

    configurations {
        all {
            // to prevent two lint errors:
            // 1) commons-logging defines classes that conflict with classes now provided by Android
            // 2) httpclient defines classes that conflict with classes now provided by Android
            exclude("org.apache.httpcomponents", "httpclient")
        }
    }

    lint {
        abortOnError = true
        disable += "UnusedResources"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.activity:activity-ktx:1.9.1")
    implementation("com.google.code.gson:gson:2.11.0")
}

//tasks {
//    val sourceFiles = android.sourceSets.getByName("main").java.srcDirs
//
//    register<Javadoc>("withJavadoc") {
//        isFailOnError = false
//
//        // the code needs to be compiled before we can create the Javadoc
//        dependsOn(android.libraryVariants.toList().last().javaCompileProvider)
//
//        if (! project.plugins.hasPlugin("org.jetbrains.kotlin.android")) {
//            setSource(sourceFiles)
//        }
//
//        // add Android runtime classpath
//        android.bootClasspath.forEach { classpath += project.fileTree(it) }
//
//        // add classpath for all dependencies
//        android.libraryVariants.forEach { variant ->
//            variant.javaCompileProvider.get().classpath.files.forEach { file ->
//                classpath += project.fileTree(file)
//            }
//        }
//
//        // We don't need javadoc for internals.
//        exclude("**/internal/*")
//
//        // Append Java 8 and Android references
//        val options = options as StandardJavadocDocletOptions
//        options.links("https://developer.android.com/reference")
//        options.links("https://docs.oracle.com/javase/8/docs/api/")
//
//        // Workaround for the following error when running on on JDK 9+
//        // "The code being documented uses modules but the packages defined in ... are in the unnamed module."
//        if (JavaVersion.current() >= JavaVersion.VERSION_1_9) {
//            options.addStringOption("-release", "8")
//        }
//    }
//
//    register<Jar>("withJavadocJar") {
//        archiveClassifier.set("javadoc")
//        dependsOn(named("withJavadoc"))
//        val destination = named<Javadoc>("withJavadoc").get().destinationDir
//        from(destination)
//    }
//
//    register<Jar>("withSourcesJar") {
//        archiveClassifier.set("sources")
//        from(sourceFiles)
//    }
//}

// 示例：https://juejin.cn/post/7395866502715850787?searchId=202408231048057FA9C73F6CFFD0D83A27

//afterEvaluate {
//    fun Project.get(name: String, def: String = "$name not found") =
//        properties[name] as String? ?: def
//
//    fun Project.getRepositoryUrl(): java.net.URI {
//        val isRelease = !get("VERSION_NAME").contains("SNAPSHOT")
//        val releaseUrl = get("RELEASE_REPOSITORY_URL", "https://oss.sonatype.org/service/local/staging/deploy/maven2/")
//        val snapshotUrl = get("SNAPSHOT_REPOSITORY_URL", "https://oss.sonatype.org/content/repositories/snapshots/")
//        return uri(if (isRelease) releaseUrl else snapshotUrl)
//    }
//
//    publishing {
//        publications {
//            // 1. configure repositories
//            repositories {
//                maven {
//                    url = getRepositoryUrl()
//                    credentials {
//                        username = project.get("ossrhUsername")
//                        password = project.get("ossrhPassword")
//                    }
//                }
//            }
//            // 2. configure publication
//            val publicationName = project.get("POM_NAME", "publication")
//            create<MavenPublication>(publicationName) {
//                from(components["release"])
//
//                artifact(tasks.named<Jar>("withJavadocJar"))
//                tasks.named("generateMetadataFileFor${rootProject.name}Publication") {
//                    dependsOn("withSourcesJar")
//                }
//
//                pom {
//                    groupId = project.get("GROUP_ID")
//                    artifactId = project.get("ARTIFACT_ID")
//                    version = project.get("VERSION_NAME")
//
//                    name.set(project.get("POM_NAME"))
//                    description.set(project.get("POM_DESCRIPTION"))
//                    url.set(project.get("POM_URL"))
//                    packaging = project.get("POM_PACKAGING", "aar")
//
//                    //SCM Information
//                    scm {
//                        url.set(project.get("POM_SCM_URL"))
//                        connection.set(project.get("POM_SCM_CONNECTION"))
//                        developerConnection.set(project.get("POM_SCM_DEV_CONNECTION"))
//                    }
//
//                    //configure organization
//                    organization {
//                        name.set(project.get("POM_ORGANIZATION_NAME"))
//                        url.set(project.get("POM_ORGANIZATION_URL"))
//                    }
//
//                    //configure developers
//                    developers {
//                        developer {
//                            id.set(project.get("POM_DEVELOPER_ID"))
//                            name.set(project.get("POM_DEVELOPER_NAME"))
//                            email.set(project.get("POM_DEVELOPER_EMAIL"))
//                            url.set(project.get("POM_DEVELOPER_URL"))
//                        }
//                    }
//
//                    //configure licenses
//                    licenses {
//                        license {
//                            name.set(project.get("POM_LICENCE_NAME"))
//                            url.set(project.get("POM_LICENCE_URL"))
//                        }
//                    }
//
//                }
//            }
//
//            // 3. configure signing
//            signing {
//                val keyId = project.get("signing.keyId")
//                val keyPassword = project.get("signing.keyPassword")
//                val secretKeyRingFile = project.get("signing.secretKeyRingFile")
//                useInMemoryPgpKeys(keyId, keyPassword, secretKeyRingFile)
//                sign(publishing.publications.getByName(publicationName))
//            }
//        }
//    }
//}