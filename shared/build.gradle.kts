plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.6.10"
    id("com.android.library")
}

version = "1.0"

kotlin {
    android()

    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))

                implementation("io.ktor:ktor-serialization-kotlinx-json:_")
                implementation("io.ktor:ktor-client-content-negotiation:_")

                implementation(Ktor.client.core)
                implementation(Ktor.client.json)
                implementation(Ktor.client.logging)

                implementation(Koin.core)
                implementation(Koin.test)

                implementation(Touchlab.kermit)

            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Ktor.client.okHttp)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:_")
            }
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}