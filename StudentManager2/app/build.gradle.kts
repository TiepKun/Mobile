plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("androidx.navigation.safeargs.kotlin") // Chỉ cần khi sử dụng Safe Args
}

android {
  namespace = "vn.edu.hust.studentman"
  compileSdk = 35

  defaultConfig {
    applicationId = "vn.edu.hust.studentman"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation("androidx.core:core-ktx:1.10.0")
  implementation("androidx.appcompat:appcompat:1.6.1")
  implementation("com.google.android.material:material:1.9.0")
  implementation("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation("androidx.navigation:navigation-fragment-ktx:2.7.1")
  implementation("androidx.navigation:navigation-ui-ktx:2.7.1")
}
