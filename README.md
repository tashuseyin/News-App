<h1 align="center">News App</h1>

<p align="center">
News App is a small demo application based on modern Android application tech-stacks and MVVM architecture.<br>Care about was placed on the use of new technologies in this project.<br>
Also fetching data from the network and integrating persisted data in the database via repository pattern.
</p>

<p align="center">
<img src="/images/news.png"/>
</p>

## Tech Stack & Open-Source Library
- Kotlin
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs network data.
- [Glide](https://github.com/bumptech/glide)
- Jetpack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room - construct a database using the abstract layer.
 - Architecture
  -  MVVM Architecture (View - ViewModel - Model)
  -  Repository pattern
  - [Material-Components](https://github.com/material-components/material-components-android) - Material design components like cardView.
