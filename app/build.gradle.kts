plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

@Suppress("UnstableApiUsage")
android {

    compileSdk = BuildConstants.MainSettings.compileSdk
    defaultConfig {
        versionName = BuildConstants.MainSettings.versionName
        versionCode = BuildConstants.MainSettings.versionCode
        applicationId = BuildConstants.MainSettings.applicationId
        namespace = BuildConstants.MainSettings.applicationId
        minSdk = BuildConstants.MainSettings.minSdk
        targetSdk = BuildConstants.MainSettings.targetSdkVersion
        setProperty("archivesBaseName", BuildConstants.getVersionName())
        vectorDrawables.useSupportLibrary = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = BuildConstants.MainSettings.sourceCompatibility
        targetCompatibility = BuildConstants.MainSettings.sourceCompatibility
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lint {
        disable.add("NullSafeMutableLiveData")
    }

    signingConfigs {
/* //TODO
create("release") {
            storePassword = "debugdebug"
            keyPassword = "debugdebug"
            storeFile = file("../buildsystem/keystore/debug.jks")
            keyAlias = "debug"
        }*/
        getByName("debug") {
            storePassword = "debugdebug"
            keyPassword = "debugdebug"
            storeFile = file("../buildsystem/keystore/debug.jks")
            keyAlias = "debug"
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            manifestPlaceholders["crashlyticsEnabled"] = false
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = true
            manifestPlaceholders["crashlyticsEnabled"] = true
            // signingConfig = signingConfigs.getByName("release")

            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }

    kotlinOptions {
        jvmTarget = BuildConstants.MainSettings.jvmTarget
    }

    flavorDimensions.add("environment")
    productFlavors {
        create("develop") {
            applicationIdSuffix = ".dev"
            buildConfigField("String", "BASE_PATH", "\"https://neting-api-dev.zapp.dev/\"")
        }
        create("pre") {
            applicationIdSuffix = ".pre"
            buildConfigField("String", "BASE_PATH", "\"https://neting-api-dev.zapp.dev/\"")
        }
        create("production") {
            buildConfigField("String", "BASE_PATH", "\"https://neting-api-dev.zapp.dev/\"")
        }
    }
}

dependencies {
    BuildConstants.platform.forEach {
        api(platform(it))
    }

    BuildConstants.appDependencies.forEach {
        implementation(it)
    }

    BuildConstants.appDesugaring.forEach {
        coreLibraryDesugaring(it)
    }

    BuildConstants.kaptDependencies.forEach {
        kapt(it)
    }
}
