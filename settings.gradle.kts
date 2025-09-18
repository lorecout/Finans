
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        id("com.google.gms.google-services") version "4.4.3"
    }
}

rootProject.name = "Finans"
include(":gastos_manager")
