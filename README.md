# google-cloud-function-hello-world
Google Cloud Function Hello World using Gradle and Kotlin

run using `./gradlew runFunction`

deploy using 
```bash
./gradlew build

gcloud functions deploy helloworld-app \                            
--entry-point=com.carlonzo.Application \
--source=build/deploy --runtime=java11 --trigger-http \
--allow-unauthenticated
```
