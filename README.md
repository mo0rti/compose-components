<h1 align="center">Composents (Compose components)</h1>

<div align="center">
  <img src="./components/docs/images/weblogo.png" alt="Compose components" width=500>
</div>
<br>

This repo contains a set of jetpack compose components to help you build your compose application faster.

## Getting Started
[![](https://jitpack.io/v/mo0rti/compose-components.svg)](https://jitpack.io/#mo0rti/compose-components)
[![Project Status: Active – The project has reached a stable, usable state and is being actively developed.](https://www.repostatus.org/badges/latest/active.svg)](https://www.repostatus.org/#active)
[![Compatible with Compose — 1.4.0](https://img.shields.io/badge/Compatible%20with%20Compose-1.4.0-brightgreen)](https://developer.android.com/jetpack/androidx/releases/compose-foundation#1.3.0)

### Old configuration
Add the following code to your project's _root_ `build.gradle` file:

```groovy
repositories {
    maven { url "https://jitpack.io" }
}
```

### New configuration
Add the following code to your project's `settings.gradle` file:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

Next, add the dependency below to your _module_'s `build.gradle` file:

```gradle
dependencies {
    implementation "com.github.mo0rti:compose-components:LATEST_VERSION"
}
```

## [Documentations](components/ui)

