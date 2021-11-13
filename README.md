# App-Review
This library lets you prompt users to submit Play Store ratings and reviews without the inconvenience of leaving your app or game.

![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)
[![](https://jitpack.io/v/Kakyire/app-review.svg)](https://jitpack.io/#Kakyire/app-review)


<img width="250"  src="https://user-images.githubusercontent.com/47930771/108591259-b58df980-735f-11eb-8200-881674efede8.jpg" />

[App that uses this library](https://play.google.com/store/apps/details?id=com.churchpal)

<!-- ![](<img width="50" height="100" src="https://upload.wikimedia.org/wikipedia/commons/7/78/Google_Play_Store_badge_EN.svg">) -->
 

 <!-- [![](https://upload.wikimedia.org/wikipedia/commons/7/78/Google_Play_Store_badge_EN.svg)](https://play.google.com/store/apps/details?id=com.churchpal) -->





## Dependency
Add the dependency to your app level `build.gradle`


### Gradle Groovy DSL
```gradle
dependencies {
	implementation 'io.github.kakyire:app-review:2.0.1'
}
  ``` 


### Gradle Kotlin DSL
```kotlin
dependencies{
implementation("io.github.kakyire:app-review:2.0.1")
}
```
  
## Implementation
In your `activity` or `fragment` add this single line of code

### Kotlin Activity
```kotlin
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
        

        //pass an Int argument to the method 
        //that is number of times user needs to open the app to trigger In-app review
        //the default value is 5
        reviewApp()


    }
```
### Kotlin Fragment
```kotlin
 override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        
       //pass an Int argument to the method 
        //that is number of times user needs to open the app to trigger In-app review
        //the default value is 5
        reviewApp()

    }

```

### Java Activity
```java
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pass a second Int argument to the method 
        //that is number of times user needs to open the app to trigger In-app review
        //the default value is 5
        InAppReviewKt.reviewApp(this);

    }
  ```

### Java Fragment
```java

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //pass a second Int argument to the method 
        //that is number of times user needs to open the app to trigger In-app review
        //the default value is 5
        InAppReviewKt.reviewApp(this);
    }
```

## Device requirements
In-app reviews only work on the following devices:


* Android devices (phones and tablets) running Android 5.0 (API level 21) or higher that have the Google Play Store installed.
* Chrome OS devices that have the Google Play Store installed.

**PS:** Your app needs to be on Play Store

Learn more about In-app review [here](https://developer.android.com/guide/playcore/in-app-review)


## License
```
Copyright (C) 2021 Kakyire (Daniel Frimpong)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
   ```
