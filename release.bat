REM combine username and password (on maven) : https://mixedanalytics.com/tools/basic-authentication-generator/
call ./gradlew jreleaserConfig -x test
call ./gradlew clean
call ./gradlew publish -x test
call ./gradlew jreleaserFullRelease -x test
