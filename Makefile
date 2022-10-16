build:
	./gradlew build

clean:
	./gradlew clean

install:
	./gradlew installDebug
	./gradlew installDebugAndroidTest

itest:
	/home/samaritan/Android/platform-tools/adb shell am instrument -w androidsamples.java.dicegames.test/androidx.test.runner.AndroidJUnitRunner

utest:
	./gradlew testDebugUnitTest

tests:
	/home/samaritan/Android/platform-tools/adb shell am instrument -w androidsamples.java.dicegames.test/androidx.test.runner.AndroidJUnitRunner
	./gradlew testDebugUnitTest