<h1 align="center">Ukko</h1>
<h4 align="center">Simple Weather App</h4>

<p align="center">A weather app that fetches current weather

## Description
This app started as a basic coding assessment for a client. Barebones functionality, but organized & clean.

Written in [Kotlin](https://kotlinlang.org/) & [Compose](https://developer.android.com/compose), this app uses the standard pillars of Android development: [Ktor](https://ktor.io/) for networking, [Koin](https://insert-koin.io/) for Dependency Injection, [Flows](https://developer.android.com/kotlin/flow), and [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel) in an [MVI](https://medium.com/swlh/mvi-architecture-with-android-fcde123e3c4a) pattern. [Mockk](https://mockk.io/) is used for unit testing.

An older version, found [here](https://github.com/welshk91/Ukko/tree/746cf840c5c5c17ca70e8cea149ed50bd703ebd7), was written in [Kotlin](https://kotlinlang.org/) with [Fragments](https://developer.android.com/guide/fragments). It used [Retrofit](https://square.github.io/retrofit/) for networking, [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency Injection, [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel), and [Databinding](https://developer.android.com/topic/libraries/data-binding) in an [MVVM](https://www.journaldev.com/20292/android-mvvm-design-pattern) pattern.

## Screen Shots
![San Diego - Clear](Screenshots/san_diego_clear_framed.png?raw=true)
![Tampa - Thunderstorm](Screenshots/tampa_thunderstorm_framed.png?raw=true)
![New York - Clouds](Screenshots/new_york_clouds_framed.png?raw=true)
![Seattle - Rain](Screenshots/seattle_rain_framed.png?raw=true)
![Grand Rapids - Snow](Screenshots/grand_rapids_snow_framed.png?raw=true)


## Improvements
* Move project over to [Kotlin Multiplatform](https://www.jetbrains.com/kotlin-multiplatform/)
* Clean up the [side sheet](https://m2.material.io/components/sheets-side) that contains the five day [forecast](https://openweathermap.org/forecast5)
* Add Loading and Error screens
* Add alternative Weather APIs such as [Open-Meteo](https://open-meteo.com/) and [Weather.gov](https://www.weather.gov/documentation/services-web-api)
* Weather [live notification](https://developer.android.com/about/versions/16/features/progress-centric-notifications) 
* Add more background images depicting the weather status
* Add more Unit Testing

## Sources
* [OpenWeatherMap API](https://openweathermap.org/api)
* [Unsplash](https://unsplash.com/s/photos/weather)
* [MockuPhone](https://mockuphone.com/)

## License
	Copyright 2022 Kevin Welsh
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	   http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
