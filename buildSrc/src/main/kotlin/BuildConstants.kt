import org.gradle.api.JavaVersion
import org.gradle.api.Project
import java.util.Properties

object BuildConstants {

    object MainSettings {
        const val targetSdkVersion = 33
        const val compileSdk = 33
        const val minSdk = 25
        const val applicationId = "com.training.app"
        const val versionName = "0.0.1"
        const val versionCode = 1
        const val jvmTarget = "17"
        const val kotlinVersion = "1.8.21"
        val sourceCompatibility = JavaVersion.VERSION_17
        val targetCompatibility = JavaVersion.VERSION_17
    }

    val platform = mutableListOf<String>().apply {
        val firebase = "32.0.0"
        add("com.google.firebase:firebase-bom:$firebase")
    }



    val appDependencies = mutableListOf<String>().apply {
        val zappbase_version = "2.3.15"
        add("com.zapp-studio.zappbase:core-gms:${zappbase_version}")
        add("com.zapp-studio.zappbase:databinding:${zappbase_version}")
        add("com.zapp-studio.zappbase:webservice:${zappbase_version}")
        add("com.zapp-studio.zappbase:picker:${zappbase_version}")

        add("com.google.firebase:firebase-config-ktx")
        add("com.google.firebase:firebase-analytics-ktx")
        add("com.google.firebase:firebase-crashlytics")
        add("com.google.firebase:firebase-messaging-ktx")

        val appCompat_version = "1.6.1"
        add("androidx.appcompat:appcompat:$appCompat_version")

        val coroutines_play_services = "1.7.0"
        add("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutines_play_services")

        val timber = "5.0.1"
        add("com.jakewharton.timber:timber:$timber")

        val flexible_divider_version = "1.4.0"
        add("com.yqritc:recyclerview-flexibledivider:$flexible_divider_version")

        val ktx_lifecycle = "2.6.1"
        add("androidx.lifecycle:lifecycle-viewmodel-ktx:$ktx_lifecycle")

        val runtimePermission = "1.1.2"
        add("com.github.florent37:runtime-permission-kotlin:$runtimePermission")

    }

    val appDesugaring = mutableListOf<String>().apply {
        val desugar_jdk_libs = "2.0.3"
        add("com.android.tools:desugar_jdk_libs:${desugar_jdk_libs}")
    }

    val kaptDependencies = mutableListOf<String>().apply {
        val moshiVersion = "1.14.0"
        add("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
    }

    object Classpath {
        const val androidGradlePlugin = "com.android.tools.build:gradle:8.0.2"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${MainSettings.kotlinVersion}"
        const val googleServices = "com.google.gms:google-services:4.3.15"
        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-gradle:2.9.5"
        const val ktlintPlugin = "org.jlleitschuh.gradle:ktlint-gradle:10.3.0"
    }

    fun getVersionName(): String = "AModularized_" + MainSettings.versionName + "_" + MainSettings.versionCode


    fun getLocalProperties(project: Project): Properties {
        val localProperties = java.util.Properties()
        project.file("local.properties").let {
            if (it.exists())
                localProperties.load(java.io.FileInputStream(it))
        }
        return localProperties
    }
    fun mavenRepositories() = listOf(
        "https://plugins.gradle.org/m2/",
        "https://oss.sonatype.org/content/repositories/snapshots",
        "https://jitpack.io",
        "https://maven.fabric.io/public"
    )

}
