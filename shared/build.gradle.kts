plugins {
    kotlin("multiplatform")
    id("com.android.library")

    // Ktor serialization
    kotlin("plugin.serialization")

    // sqldelight
    id("com.squareup.sqldelight")

    // Flow for IOS
    id("com.rickclephas.kmp.nativecoroutines")

    // Moko
    id("dev.icerock.mobile.multiplatform-resources")
}


kotlin {
    android()

    ios {
        binaries {
            framework {
                baseName = "shared"
                export("dev.icerock.moko:resources:_")
            }
        }
    }

    if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    sourceSets {

        all {
            languageSettings.apply {
                optIn("kotlin.RequiresOptIn")
            }
        }

        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))

                // Coroutines
                implementation(KotlinX.coroutines.core)

                // Sqldelight
                implementation("com.squareup.sqldelight:runtime:_")
                implementation(Square.sqlDelight.extensions.coroutines)

                // Ktor
                implementation(Ktor.client.core)
                implementation(Ktor.client.json)
                implementation(Ktor.client.logging)
                implementation("io.ktor:ktor-client-content-negotiation:_")
                implementation("io.ktor:ktor-serialization-kotlinx-json:_")

                // Di
                implementation(Koin.core)
                implementation(Koin.test)

                // Log
                implementation(Touchlab.kermit)

                api("dev.icerock.moko:resources:_")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(KotlinX.coroutines.test)
                implementation(Ktor.client.mock)
                implementation(Square.sqlDelight.drivers.jdbcSqlite)
            }
        }

        val androidMain by getting {
            dependencies {
                // Sqldelight
                implementation(Square.sqlDelight.drivers.android)

                // Ktor
                implementation(Ktor.client.okHttp)
            }
        }
        val androidTest by getting

        val iosMain by getting {
            dependencies {
                // Sqldelight
                implementation(Square.sqlDelight.drivers.native)

                // Ktor
                implementation(Ktor.client.darwin)
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
    database("UserDatabase") {
        packageName = "lunabee.studio.sqldelight"
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "lunabee.studio.resources"
}
