<h1 align="center">Ukko</h1>
<h4 align="center">Simple Weather App</h4>

<p align="center">A weather app that fetches current weather

## Description
This app started as a basic coding assessment for a client. Barebones functionality, but organized & clean.

Written in Kotlin leveraging the [OpenWeatherMap API](https://openweathermap.org/api), this app uses the standard pillars of Android development: [Retrofit](https://square.github.io/retrofit/) for networking, [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency Injection, [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [ViewModels](https://developer.android.com/topic/libraries/architecture/viewmodel), and [Databinding](https://developer.android.com/topic/libraries/data-binding) in an [MVVM](https://www.journaldev.com/20292/android-mvvm-design-pattern) pattern. 

## Screen Shots
![San Diego - Clear](Screenshots/san_diego_clear_framed.png?raw=true)
![Tampa - Thunderstorm](Screenshots/tampa_thunderstorm_framed.png?raw=true)
![New York - Clouds](Screenshots/new_york_clouds_framed.png?raw=true)
![Seattle - Rain](Screenshots/seattle_rain_framed.png?raw=true)
![Grand Rapids - Snow](Screenshots/grand_rapids_snow_framed.png?raw=true)


## Improvements
* Add more background images depiciting the weather status
* Improve fonts
* Add a [side sheet](https://m2.material.io/components/sheets-side) with more details and five day [forecast](https://openweathermap.org/forecast5)

## Sources
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
