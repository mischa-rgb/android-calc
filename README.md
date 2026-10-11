# Recall Calculator for Android

A store-style calculator with named memories. Each memory keeps a trail: how it got its value and every sum it was used in. Runs fully offline; memories are saved on the phone.

## What's inside

| Part | What it does |
|---|---|
| `app/src/main/assets/www/index.html` | The calculator itself (same page as the claude.ai version, with fonts and maths library bundled so it works offline) |
| `app/src/main/java/.../MainActivity.java` | Opens that page full screen; Back closes the memories sheet before leaving the app |
| `app/recall-signing.keystore` | Fixed signing key, so every new build installs over the old one and keeps your memories |
| `.github/workflows/build-apk.yml` | Builds the APK on GitHub's servers, no installs needed on your Mac |

## Get the APK: option A, Android Studio on your Mac

1. Install Android Studio from https://developer.android.com/studio and open it once to finish setup.
2. **File → Open**, pick the `RecallCalculator` folder, click **Trust Project**.
3. Wait for the bottom bar to finish "Gradle sync". The first time downloads build tools and takes several minutes.
4. **Build → Build App Bundle(s) / APK(s) → Build APK(s)**.
5. Click **locate** in the pop-up. The file is `app/build/outputs/apk/debug/app-debug.apk`.

## Get the APK: option B, GitHub builds it for you

1. Create a **private** repository on GitHub (keep it private: it holds the signing key).
2. Upload everything in this folder, including the hidden `.github` folder, to the `main` branch.
3. Open the **Actions** tab. The "Build APK" run starts on its own and takes about 3 to 5 minutes.
4. Open the finished run, download **Recall-Calculator-APK** under Artifacts, and unzip it to get `app-release.apk`.

## Install it on your phone

1. Get the APK onto the phone (Google Drive, email to yourself, or USB).
2. Tap the file. Android asks you to allow installs from that app (Drive, Files, Gmail): allow it.
3. Tap **Install**. If Play Protect says the developer is unknown, tap **More details → Install anyway**. This is expected for a personal app that isn't on the Play Store.
4. Open **Recall** from your app drawer.

## Updating later

Change the page, bump `versionCode` in `app/build.gradle.kts` (1 → 2), build again, and install over the top. Same signing key, so your memories stay.

## Good to know

- Memories in the app are separate from the claude.ai version; they don't sync between the two.
- Uninstalling the app deletes its memories. Android's own backup to your Google account usually covers app data, but don't rely on it alone.
- Google is phasing in developer verification for sideloaded apps (wider rollout planned from 2027). If installs get blocked later, installing from Android Studio over USB is unaffected today.
