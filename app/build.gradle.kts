plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

@Suppress("UnstableApiUsage")
android {
    compileSdkVersion = AppConfig.compileSDK
    buildToolsVersion = AppConfig.buildToolsVersion
    namespace = "com.shreyas.nytimes"

    defaultConfig {
        applicationId = "com.shreyas.nytimes"
        minSdk = AppConfig.minSDK
        targetSdk = AppConfig.targetSDK
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    buildFeatures {
        compose = true
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    viewBinding {
        android.buildFeatures.viewBinding = true
    }
    dataBinding {
        android.buildFeatures.dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        javaParameters = true
    }
    kapt {
        generateStubs = true
        correctErrorTypes = true
        javacOptions {
            option("-Adagger.fastInit=enabled")
        }
    }
    lint {
        warningsAsErrors = true
        abortOnError = true
    }
    packaging {
        resources.excludes.add("META-INF/*")
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
    sourceSets {
        getByName("main").java.srcDir("src/main/java")
        getByName("test").java.srcDirs("src/test/java")
        getByName("test").resources.srcDirs("src/test/assets")
    }
}

tasks.withType<Test> {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

dependencies {

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(AppDependencies.kotlinStdLib)
    implementation(AppDependencies.kotlinCoRoutinesCore)
    implementation(AppDependencies.jakeWhartonCoRoutineAdapter)
    implementation(AppDependencies.appcompat)
    implementation(AppDependencies.material)
    implementation(AppDependencies.coreKtx)
    implementation(AppDependencies.composeUiAndroidTests)

    kapt(AppDependencies.lifeCycleCompiler)
    implementation(AppDependencies.lifeCycleRunTime)
    implementation(AppDependencies.lifeCycleExtensions)
    implementation(AppDependencies.lifeCycleKTX)
    implementation(AppDependencies.lifeCycleLiveDataKTX)

    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.okhttp3Interceptor)
    implementation(AppDependencies.gsonConverter)
    implementation(AppDependencies.coil)
    implementation(AppDependencies.coilCompose)
    implementation(AppDependencies.coilSvg)
    implementation(AppDependencies.gson)

    kapt(AppDependencies.daggerProcessor)
    kapt(AppDependencies.daggerCompiler)
    implementation(AppDependencies.dagger)
    implementation(AppDependencies.daggerAndroid)
    implementation(AppDependencies.daggerAndroidSupport)
    implementation(AppDependencies.espressoIdling)

    implementation(AppDependencies.composeActivities)
    implementation(AppDependencies.composeMaterialDesign)
    implementation(AppDependencies.composeAnimations)
    implementation(AppDependencies.composeTooling)
    implementation(AppDependencies.composeToolingPreview)
    implementation(AppDependencies.composeViewModels)
    implementation(AppDependencies.composeNavigation)
    implementation(AppDependencies.composeLiveData)

    testImplementation(AppDependencies.kotlinCoRoutinesCoreTest)
    testImplementation(AppDependencies.junit)
    testImplementation(AppDependencies.robolectric)
    testImplementation(AppDependencies.archCoreTesting)
    testImplementation(AppDependencies.junitRunner)
    testImplementation(AppDependencies.mockito)
    testImplementation(AppDependencies.hamcrest)
    testImplementation(AppDependencies.truth)
    testImplementation(AppDependencies.mockk)
    testImplementation(AppDependencies.mockkAndroid)
    testImplementation(AppDependencies.nharmaanMockito)
    testImplementation(AppDependencies.okHttpMockServer)

    kaptTest(AppDependencies.daggerProcessor)
    kaptTest(AppDependencies.daggerCompiler)
    testImplementation(AppDependencies.dagger)
    testImplementation(AppDependencies.daggerAndroid)
    testImplementation(AppDependencies.daggerAndroidSupport)

    androidTestImplementation(AppDependencies.androidXTestRunner)
    androidTestImplementation(AppDependencies.androidxTestRules)
    androidTestImplementation(AppDependencies.extJUnit)
    androidTestImplementation(AppDependencies.espressoCore)
    androidTestImplementation(AppDependencies.espressoContrib)
    androidTestImplementation(AppDependencies.mockito)
    androidTestImplementation(AppDependencies.mockk)
    androidTestImplementation(AppDependencies.mockkAndroid)
    androidTestImplementation(AppDependencies.navigationTesting)
    androidTestImplementation(AppDependencies.composeUITests)

    debugImplementation(AppDependencies.fragmentTesting)
    debugImplementation(AppDependencies.composeTooling)
    debugImplementation(AppDependencies.composeUIManifest)
}