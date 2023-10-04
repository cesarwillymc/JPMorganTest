# JPMorganTest
## CESAR CANAZA

Mobile developer willing to learn old and new technologies, I like the team work and communication.
Thank you for check this project.

## Architecture MVVM with clean

### data (Repositories - Framework)
 - di: Contains the dependency injection with hilt
 - settings: Contains all the settings local `(Convertes, SharedEncription)` and network `(Interceptor, Cookie, more)`
 - sources: Contains all the content of each feature `For example: Search` like `framework database or retrofit` the connection is through repository
   - di: Contains the dependency injection of the Feature like `Search`
   - entities: Contains all the entities of the feature like `Search`
   - mapper: Contains all the mapper of the feature
   - local: Contains the framework with the local storage
   - remote: Contains the framework to connect the data using `voley, retrofit or other`
   - repository files: Contains the interface and the implementation of the connections with the local and remote data
### domain (Model - Use Case)
 - usecase:
   - entities: Contains all the entities in domain with the logic if they have
   - usecase Files:  Contains each use case of the app where we can sort, filter  and others
### presentation (ViewModel - Screen UI)
 - Main Activity: Contains the main activity in jetpack compose
 - Screen Directory: Contains all the resources that manage a screen like **View Model, CustomComposable, State, Screen**
 - delegate: Contains Singleton classes that work when we need to share contents between ViewModel where we **delegate the action to do something** or **save something light**
### Additional comments
  - ui: Package that contains all the layers like **Permission, General Composables, NavigationComponent, Theme, Services**
  - util: Contains global Constants and Extension Functions 
## Configuration:

### Local.properties
Add  this line
```
WEATHER_KEY=751d80f6c314139192ffcb62c107e654
SHARED_PREFERENCES_NAME=dbappjpmorgan
BASE_URL=https://api.openweathermap.org/
```
### Gradle Java

Go to **Gradle JDK** and change to **Java17**

![Java](image/gradle_j17.png)  