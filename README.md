This is a Kotlin Multiplatform project targeting Android, iOS, Web.

This App is a chat demo app

<b>Web Page :</b>

  <img width="1728" alt="Screenshot 2024-12-15 at 4 00 01 PM" src="https://github.com/user-attachments/assets/a528d2da-0004-4da5-a880-b073fed0cd37" />


<b>iOS App :</b>
 
  <img width="526" alt="Screenshot 2024-12-15 at 4 18 30 PM" src="https://github.com/user-attachments/assets/56f9d89b-be4f-4946-b107-1bbf726bdfa1" />



* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.
