# فەرهەنگی سۆرانی - Sorani Kurdish Dictionary

## 📱 Project Overview

A professional, production-ready Android application for creating and managing a personal Sorani Kurdish dictionary with persistent storage, powerful search, and beautiful Material Design 3 UI.

## 🎯 Features

- ✅ Add, edit, delete Kurdish words
- ✅ Persistent Room Database storage
- ✅ Powerful instant search
- ✅ Favorites system
- ✅ Statistics dashboard
- ✅ Backup & restore (JSON, SQLite, TXT)
- ✅ Dark/Light mode
- ✅ Offline operation
- ✅ Material Design 3 UI
- ✅ Tablet & phone support

## 🛠️ Technology Stack

- **Language**: Kotlin
- **Architecture**: MVVM + Repository Pattern
- **UI**: Jetpack Compose + Material Design 3
- **Database**: Room Database
- **Navigation**: Jetpack Navigation Component
- **DI**: Hilt
- **Async**: Coroutines + StateFlow
- **Build**: Gradle (Kotlin DSL)

## 📋 Project Structure

```
sorani-dictionary/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/com/anwarkhatami/sorani/
│   │   │   │   ├── ui/
│   │   │   │   ├── data/
│   │   │   │   ├── domain/
│   │   │   │   ├── di/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts
└── settings.gradle.kts
```

## 🚀 Getting Started

### Prerequisites
- Android Studio 2024.1+
- JDK 17+
- Android SDK 34+
- Gradle 8.0+

### Installation

1. Clone the repository
2. Open in Android Studio
3. Build and run on emulator or device

## 📦 Build & Release

### Debug Build
```bash
./gradlew assembleDebug
```

### Release Build
```bash
./gradlew assembleRelease
```

## 👨‍💻 Author

Anwar Khatami

## 📄 License

MIT License
