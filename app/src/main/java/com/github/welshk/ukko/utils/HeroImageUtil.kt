package com.github.welshk.ukko.utils

import com.github.welshk.ukko.R
import com.github.welshk.ukko.data.models.HeroImage
import com.github.welshk.ukko.data.models.WeatherDetails

class HeroImageUtil {
    companion object {

        /**
         * https://openweathermap.org/weather-conditions
         */
        fun getHeroImage(weatherDetails: WeatherDetails?): HeroImage {
            if (weatherDetails != null) {
                when (weatherDetails.weather.get(0).id) {
                    200 -> {
                        //Thunderstorm with light rain
                        return getThunderstormLightRain(weatherDetails)
                    }
                    201 -> {
                        //Thunderstorm with rain
                        return getThunderstormRain(weatherDetails)
                    }
                    202 -> {
                        //Thunderstorm with heavy rain
                        return getThunderstormHeavyRain(weatherDetails)
                    }
                    210 -> {
                        //Light thunderstorm
                        return getThunderstormLight(weatherDetails)
                    }
                    211 -> {
                        //Thunderstorm
                        return getThunderstorm(weatherDetails)
                    }
                    212 -> {
                        //Heavy thunderstorm
                        return getThunderstormHeavy(weatherDetails)
                    }
                    221 -> {
                        //Ragged thunderstorm
                        return getThunderstormRagged(weatherDetails)
                    }
                    230 -> {
                        //Thunderstorm with light drizzle
                        return getThunderstormLightDrizzle(weatherDetails)
                    }
                    231 -> {
                        //Thunderstorm with drizzle
                        return getThunderstormDrizzle(weatherDetails)
                    }
                    232 -> {
                        //Thunderstorm with heavy drizzle
                        return getThunderstormHeavyDrizzle(weatherDetails)
                    }

                    300 -> {
                        //Light intensity drizzle
                        return getDrizzleLightIntensity(weatherDetails)
                    }
                    301 -> {
                        //Drizzle
                        return getDrizzle(weatherDetails)
                    }
                    302 -> {
                        //Heavy intensity drizzle
                        return getDrizzleHeavyIntensity(weatherDetails)
                    }
                    310 -> {
                        //Light intensity drizzle rain
                        return getDrizzleLightIntensityRain(weatherDetails)
                    }
                    311 -> {
                        //Drizzle rain
                        return getDrizzleRain(weatherDetails)
                    }
                    312 -> {
                        //Heavy intensity drizzle rain
                        return getDrizzleHeavyIntensityRain(weatherDetails)
                    }
                    313 -> {
                        //Shower rain and drizzle
                        return getDrizzleShowerRain(weatherDetails)
                    }
                    314 -> {
                        //Heavy shower rain and drizzle
                        return getDrizzleHeavyShowerRain(weatherDetails)
                    }
                    321 -> {
                        //Shower drizzle
                        return getDrizzleShower(weatherDetails)
                    }

                    500 -> {
                        //Light rain
                        return getRainLight(weatherDetails)
                    }
                    501 -> {
                        //Moderate rain
                        return getRainModerate(weatherDetails)
                    }
                    502 -> {
                        //Heavy intensity rain
                        return getRainHeavyIntensity(weatherDetails)
                    }
                    503 -> {
                        //Very heavy rain
                        return getRainVeryHeavy(weatherDetails)
                    }
                    504 -> {
                        //Extreme rain
                        return getRainExtreme(weatherDetails)
                    }
                    511 -> {
                        //Freezing rain
                        return getRainFreezing(weatherDetails)
                    }
                    520 -> {
                        //Light intensity shower rain
                        return getRainLightIntensity(weatherDetails)
                    }
                    521 -> {
                        //Shower rain
                        return getRainShower(weatherDetails)
                    }
                    522 -> {
                        //Heavy intensity shower rain
                        return getRainHeavyIntensityShower(weatherDetails)
                    }
                    531 -> {
                        //Ragged shower rain
                        return getRainRaggedShower(weatherDetails)
                    }

                    600 -> {
                        //Light snow
                        return getSnowLight(weatherDetails)
                    }
                    601 -> {
                        //Snow
                        return getSnow(weatherDetails)
                    }
                    602 -> {
                        //Heavy snow
                        return getSnowHeavy(weatherDetails)
                    }
                    611 -> {
                        //Sleet
                        return getSleet(weatherDetails)
                    }
                    612 -> {
                        //Light shower sleet
                        return getLightShowerSleet(weatherDetails)
                    }
                    613 -> {
                        //Shower sleet
                        return getShowerSleet(weatherDetails)
                    }
                    615 -> {
                        //Light rain and snow
                        return getSnowRainLight(weatherDetails)
                    }
                    616 -> {
                        //Rain and snow
                        return getSnowRain(weatherDetails)
                    }
                    620 -> {
                        //Light shower snow
                        return getLightShowerSnow(weatherDetails)
                    }
                    621 -> {
                        //Shower snow
                        return getShowerSnow(weatherDetails)
                    }
                    622 -> {
                        //Heavy shower snow
                        return getHeavyShowerSnow(weatherDetails)
                    }

                    701 -> {
                        //Mist
                        return getMist(weatherDetails)
                    }
                    711 -> {
                        //Smoke
                        return getSmoke(weatherDetails)
                    }
                    721 -> {
                        //Haze
                        return getHaze(weatherDetails)
                    }
                    731 -> {
                        // Sand/dust whirls
                        return getSandDustWhirls(weatherDetails)
                    }
                    741 -> {
                        //Fog
                        return getFog(weatherDetails)
                    }
                    751 -> {
                        //Sand
                        return getSand(weatherDetails)
                    }
                    761 -> {
                        //Dust
                        return getDust(weatherDetails)
                    }
                    762 -> {
                        //Volcanic ash
                        return getVolcanicAsh(weatherDetails)
                    }
                    771 -> {
                        //Squalls
                        return getSqualls(weatherDetails)
                    }
                    781 -> {
                        //Tornado
                        return getTornado(weatherDetails)
                    }

                    800 -> {
                        //Clear
                        return getClearSky(weatherDetails)
                    }

                    801 -> {
                        //Clouds(few)
                        return getCloudsFew(weatherDetails)
                    }
                    802 -> {
                        //Clouds(scattered)
                        return getCloudsScattered(weatherDetails)
                    }
                    803 -> {
                        //Clouds(broken)
                        return getCloudsBroken(weatherDetails)
                    }
                    804 -> {
                        //Clouds(overcast)
                        return getCloudsOvercast(weatherDetails)
                    }

                    else -> {
                        return getStockWeather(weatherDetails)
                    }
                }
            } else {
                //Weather Details is null; show stock picture
                return getStockWeather()
            }
        }


        fun getStockWeather(): HeroImage {
            return HeroImage(R.drawable.hanson_lu_ndd0m3myf4s_unsplash, "Hanson Lu", "unsplash")
        }

        fun getStockWeather(weatherDetails: WeatherDetails?): HeroImage {
            return getStockWeather()
        }

        fun getClearSky(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.peter_hulce_yykvhwigs3g_unsplash, "Peter Hulce", "unsplash")
        }

        fun getCloudsFew(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.safwana_basheer_4pil5m4ra2u_unsplash, "Safwana Basheer", "unsplash")
        }

        fun getCloudsScattered(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.peyman_farmani_uclyywtfk7w_unsplash, "Peyman Farmani", "unsplash")
        }

        fun getCloudsBroken(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.tom_barrett_hggplx3pfbg_unsplash, "Tom Barrett", "unsplash")
        }

        fun getCloudsOvercast(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.alfred_kenneally_le9htzxhsty_unsplash, "Alfred Kenneally", "unsplash")
        }

        fun getMist(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.xianyu_hao_n6pu9zdoan4_unsplash, "Xianyu Hao", "unsplash")
        }

        fun getSmoke(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.corina_rainer_jzc5etxnylu_unsplash, "Corina Rainer", "unsplash")
        }

        fun getHaze(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.wolfgang_hasselmann_7zfahbcmu74_unsplash, "Wolfgang Hasselmann", "unsplash")
        }

        fun getSandDustWhirls(weatherDetails: WeatherDetails?): HeroImage {
            return getStockWeather(weatherDetails)
        }

        fun getFog(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.david_million_5fg8ac1y1ss_unsplash, "David Million", "unsplash")
        }

        fun getSand(weatherDetails: WeatherDetails?): HeroImage {
            return getStockWeather(weatherDetails)
        }

        fun getDust(weatherDetails: WeatherDetails?): HeroImage {
            return getStockWeather(weatherDetails)
        }

        fun getVolcanicAsh(weatherDetails: WeatherDetails?): HeroImage {
            return getStockWeather(weatherDetails)
        }

        fun getSqualls(weatherDetails: WeatherDetails?): HeroImage {
            return getStockWeather(weatherDetails)
        }

        fun getTornado(weatherDetails: WeatherDetails?): HeroImage {
            return getStockWeather(weatherDetails)
        }

        fun getSnowLight(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.hunter_bryant_ozmgy8s1mh8_unsplash, "Hunter Bryant", "unsplash")
        }

        fun getSnow(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.nate_williams_qpsmcnkgyyq_unsplash, "Nate Williams", "unsplash")
        }

        fun getSnowHeavy(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.hayes_potter_5px6khmmopg_unsplash, "Hayes Potter", "unsplash")
        }

        fun getSleet(weatherDetails: WeatherDetails?): HeroImage {
            return getSnow(weatherDetails)
        }

        fun getLightShowerSleet(weatherDetails: WeatherDetails?): HeroImage {
            return getSnow(weatherDetails)
        }

        fun getShowerSleet(weatherDetails: WeatherDetails?): HeroImage {
            return getSnow(weatherDetails)
        }

        fun getSnowRainLight(weatherDetails: WeatherDetails?): HeroImage {
            return getSnow(weatherDetails)
        }

        fun getSnowRain(weatherDetails: WeatherDetails?): HeroImage {
            return getSnow(weatherDetails)
        }

        fun getLightShowerSnow(weatherDetails: WeatherDetails?): HeroImage {
            return getSnow(weatherDetails)
        }

        fun getShowerSnow(weatherDetails: WeatherDetails?): HeroImage {
            return getSnow(weatherDetails)
        }

        fun getHeavyShowerSnow(weatherDetails: WeatherDetails?): HeroImage {
            return getSnow(weatherDetails)
        }

        fun getRainLight(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.milin_john_we01ed9ei6g_unsplash, "Milin John", "unsplash")
        }

        fun getRainModerate(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.ahmed_nishaath_mwvhvglhj5o_unsplash, "Ahmed Nishaath", "unsplash")
        }

        fun getRainHeavyIntensity(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.alejandro_iovdr_sxmj0_unsplash, "Alejandro IovdR", "unsplash")
        }

        fun getRainVeryHeavy(weatherDetails: WeatherDetails?): HeroImage {
            return getRainHeavyIntensity(weatherDetails)
        }

        fun getRainExtreme(weatherDetails: WeatherDetails?): HeroImage {
            return getRainHeavyIntensity(weatherDetails)
        }

        fun getRainFreezing(weatherDetails: WeatherDetails?): HeroImage {
            return getRainHeavyIntensity(weatherDetails)
        }

        fun getRainLightIntensity(weatherDetails: WeatherDetails?): HeroImage {
            return getRainLight(weatherDetails)
        }

        fun getRainShower(weatherDetails: WeatherDetails?): HeroImage {
            return getRainLight(weatherDetails)
        }

        fun getRainHeavyIntensityShower(weatherDetails: WeatherDetails?): HeroImage {
            return getRainHeavyIntensity(weatherDetails)
        }

        fun getRainRaggedShower(weatherDetails: WeatherDetails?): HeroImage {
            return getStockWeather(weatherDetails)
        }

        fun getDrizzleLightIntensity(weatherDetails: WeatherDetails?): HeroImage {
            return getDrizzle(weatherDetails)
        }

        fun getDrizzle(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.max_bender_1yhxfeoypn0_unsplash, "Max Bender", "unsplash")
        }

        fun getDrizzleHeavyIntensity(weatherDetails: WeatherDetails?): HeroImage {
            return getDrizzle(weatherDetails)
        }

        fun getDrizzleLightIntensityRain(weatherDetails: WeatherDetails?): HeroImage {
            return getDrizzle(weatherDetails)
        }

        fun getDrizzleRain(weatherDetails: WeatherDetails?): HeroImage {
            return getDrizzle(weatherDetails)
        }

        fun getDrizzleHeavyIntensityRain(weatherDetails: WeatherDetails?): HeroImage {
            return getDrizzle(weatherDetails)
        }

        fun getDrizzleShowerRain(weatherDetails: WeatherDetails?): HeroImage {
            return getDrizzle(weatherDetails)
        }

        fun getDrizzleHeavyShowerRain(weatherDetails: WeatherDetails?): HeroImage {
            return getDrizzle(weatherDetails)
        }

        fun getDrizzleShower(weatherDetails: WeatherDetails?): HeroImage {
            return getDrizzle(weatherDetails)
        }

        fun getThunderstormLightRain(weatherDetails: WeatherDetails?): HeroImage {
            return getThunderstorm(weatherDetails)
        }

        fun getThunderstormRain(weatherDetails: WeatherDetails?): HeroImage {
            return getThunderstorm(weatherDetails)
        }

        fun getThunderstormHeavyRain(weatherDetails: WeatherDetails?): HeroImage {
            return getThunderstorm(weatherDetails)
        }

        fun getThunderstormLight(weatherDetails: WeatherDetails?): HeroImage {
            return getThunderstorm(weatherDetails)
        }

        fun getThunderstorm(weatherDetails: WeatherDetails?): HeroImage {
            return HeroImage(R.drawable.michelle_mcewen_scrqmg2f6qo_unsplash, "Michelle Mcewen", "unsplash")
        }

        fun getThunderstormHeavy(weatherDetails: WeatherDetails?): HeroImage {
            return getThunderstorm(weatherDetails)
        }

        fun getThunderstormRagged(weatherDetails: WeatherDetails?): HeroImage {
            return getThunderstorm(weatherDetails)
        }

        fun getThunderstormLightDrizzle(weatherDetails: WeatherDetails?): HeroImage {
            return getThunderstorm(weatherDetails)
        }

        fun getThunderstormDrizzle(weatherDetails: WeatherDetails?): HeroImage {
            return getThunderstorm(weatherDetails)
        }

        fun getThunderstormHeavyDrizzle(weatherDetails: WeatherDetails?): HeroImage {
            return getThunderstorm(weatherDetails)
        }
    }
}
