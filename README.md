# google-cloud-function-hello-world
Google Cloud Function Hello World using Gradle and Kotlin

Inspired by [github.com/mwhyte-dev/kotlin-google-cloud-function](https://github.com/mwhyte-dev/kotlin-google-cloud-function)

Run using `./gradlew runFunction`

Deploy using 
```bash
./gradlew build

gcloud functions deploy helloworld-app \                            
--entry-point=com.carlonzo.Application \
--source=build/deploy --runtime=java11 --trigger-http \
--allow-unauthenticated
```

