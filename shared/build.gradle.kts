plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    id("com.rickclephas.kmp.nativecoroutines")
}

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

                implementation(KotlinX.coroutines.core)
                implementation(Square.sqlDelight.extensions.coroutines)

                implementation("com.squareup.sqldelight:runtime:_")

                api(Koin.core)
                api(Koin.test)

                api(Touchlab.kermit)
            }
        }
        val commonTest by getting


        val androidMain by getting {
            dependencies {
                implementation(Square.sqlDelight.drivers.android)
            }
        }
        val androidTest by getting


        val iosMain by getting {
            dependencies {
                implementation(Square.sqlDelight.drivers.native)
            }
        }
        val iosTest by getting


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

sqldelight {
    database("NoteDatabase") {
        packageName = "lunabee.studio.sqldelight"
    }
}