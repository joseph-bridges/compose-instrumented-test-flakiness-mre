/*
 * Copyright 2024 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.example.composebugrepro"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.composebugrepro"
        minSdk = 24
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
val composeVersion = "1.7.0-beta03"
dependencies {
    constraints {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

        implementation("androidx.core:core-ktx:1.13.1")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
        implementation("androidx.activity:activity-compose:1.9.0")
        implementation("androidx.compose.ui:ui:$composeVersion")
        implementation("androidx.compose.ui:ui-graphics:$composeVersion")
        implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
        implementation("androidx.compose.material3:material3:1.3.0-beta03")
        implementation("androidx.tracing:tracing:1.2.0")
        implementation("androidx.tracing:tracing-ktx:1.2.0")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.2.0")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.0")
        androidTestImplementation("androidx.tracing:tracing:1.2.0")
        androidTestImplementation("androidx.tracing:tracing-ktx:1.2.0")
        androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
        debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
        debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")

    }
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.2")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.ui:ui-graphics:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.material3:material3:1.3.0-beta03")
    implementation("androidx.tracing:tracing:1.2.0")
    implementation("androidx.tracing:tracing-ktx:1.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}
