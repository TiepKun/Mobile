plugins {
  alias(libs.plugins.android.application) apply false
  alias(libs.plugins.kotlin.android) apply false
}

buildscript {
  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath(libs.gradle)
    classpath(libs.kotlin.gradle.plugin)
    // Safe Args Plugin (nếu sử dụng Navigation Component)
    classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.4")
  }
}
