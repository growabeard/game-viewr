# GameViewr 🎮

GameViewr is a modern Android application built to help users find the best deals on PC games. It leverages the [CheapShark API](https://apidocs.cheapshark.com/) to provide real-time pricing and deal information.

## ✨ Features
- **Search**: Find games by title across multiple stores.
- **Deal Tracking**: View sale prices, retail prices, and total savings.
- **Infinite Scrolling**: Smooth list browsing powered by Jetpack Paging 3.
- **Game Details**: Detailed information including Metacritic scores and Steam ratings.
- **Modern UI**: Fully built with Jetpack Compose and Material 3.

## 🛠 Tech Stack
- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Dependency Injection**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/)
- **Pagination**: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)
- **Serialization**: [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
- **Concurrency**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)

## 🚀 How to Run

### Prerequisites
- **Android Studio Ladybug** (or newer)
- **JDK 17**
- An Android device or emulator running **API 24+**

### Steps
1. **Clone the repository**:
   ```bash
   git clone https://github.com/growabeard/game-viewr.git
   ```
2. **Open in Android Studio**:
   - File > Open > Select the `GameViewr` folder.
3. **Build the project**:
   - Click the **Hammer icon** in the toolbar or run `./gradlew assembleDebug` in the terminal.
4. **Run the app**:
   - Select your device and click the **Play icon** (Run).

## 🧪 Testing
The project includes unit tests for the ViewModel and Repository layers.
To run all tests:
```bash
./gradlew test
```

## 📄 License
Please use responsibly in accordance with the [CheapShark API Terms](https://www.cheapshark.com/about).
