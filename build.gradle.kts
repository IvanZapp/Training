apply(plugin = "org.jlleitschuh.gradle.ktlint")
apply(plugin = "org.jlleitschuh.gradle.ktlint-idea")


buildscript {

    repositories {
        mavenCentral()
        google()
        BuildConstants.mavenRepositories().forEach {
            maven {
                url = uri(it)
            }
        }
        maven {
            url = uri("https://maven.pkg.github.com/ZappStudio/ZappBase")
            credentials {
                username = BuildConstants.getLocalProperties(project).getProperty("USER_EMAIL") ?: System.getenv("USER_EMAIL")
                password =BuildConstants.getLocalProperties(project).getProperty("USER_API_KEY")  ?: System.getenv("USER_API_KEY")
            }
        }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21")
        classpath("com.google.gms:google-services:4.3.15")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.9.5")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:11.3.2")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        BuildConstants.mavenRepositories().forEach {
            maven {
                url = uri(it)
            }
        }
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jlleitschuh.gradle.ktlint-idea")
    // Optionally configure plugin
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(true)
    }

    repositories {
        mavenCentral()
        google()
        BuildConstants.mavenRepositories().forEach {
            maven {
                url = uri(it)
            }
        }
        maven {
            url = uri("https://maven.pkg.github.com/ZappStudio/ZappBase")
            credentials {
                username = BuildConstants.getLocalProperties(project).getProperty("USER_EMAIL") ?: System.getenv("USER_EMAIL")
                password =BuildConstants.getLocalProperties(project).getProperty("USER_API_KEY")  ?: System.getenv("USER_API_KEY")
            }
        }
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
