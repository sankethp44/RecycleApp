pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url=uri("https://maven.mappls.com/repository/mappls/")
        }
    }
}
rootProject.name = "RecycleApp"
include(":app")
 