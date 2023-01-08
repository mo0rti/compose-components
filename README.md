<h1 align="center">Pin Code with Numeric Pad</h1>

<div align="center">
  <img src="images/screenshot.png" alt="pin code pad demo" width=350>
</div>
<br>

This repo is a jetpack compose library to ask user for a pin code and display it on boxes.

## Getting Started

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
    implementation "com.github.mo0rti:pincode-compose:LATEST_VERSION"
}
```

## Usage

Examples are in the [source code](https://github.com/mo0rti/pincode-compose/blob/main/app/src/main/java/bluevelvet/sample/pincode/MainActivity.kt).

```kotlin
@Composable
fun Usage() {
    var pincode by remember { mutableStateOf("") }

    PinBox(pincode)
    PinPad(pincode) {
        pincode = it.pinCode
        if (it is PinPadResult.EntryFinished) {
            Log.d(tag, "Pincode entry is finished, pincode is $pincode")
        } else {
            Log.d(tag, "Pincode changed to $pincode")
        }
    }
}
```

The default length for pin code is 6. To change it pass the desired length as second parameter to PinBox and PinPad:
```
    ...

    PinBox(pincode = pincode, maxPinLength = 4)
    PinPad(pincode = pincode, maxPinLength = 4) {
        ....
    }
    ...

```

PinBox hides the entered pin code by default, to change this to make the pin code visible to the user, pass the false to `isPinHidden` parameter:
```
    ...

    PinBox(pincode = pincode, isPinHidden = false)

```