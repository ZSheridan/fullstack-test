package ai.gobots.zsheridan.musicsuggestionservice.domain.service

import ai.gobots.zsheridan.musicsuggestionservice.config.APIKEY
import ai.gobots.zsheridan.musicsuggestionservice.domain.exception.BusinessException
import com.google.gson.JsonParser
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class OpenWeatherMapApiService {

    private val openWeatherAppId = APIKEY.openWeatherMap
    private val baseURL = "http://api.openweathermap.org/data/2.5/weather?"
    private val sufix = "&units=metric&appid=$openWeatherAppId"

    fun getLocationTemperature(location: String): Double {

        val local = location.replace(" ", "%20")
        val client = HttpClients.createDefault()
        val httpGet = HttpGet("$baseURL$local$sufix")

        val locationWeather: String
        try {
            locationWeather = EntityUtils.toString(client.execute(httpGet).entity, "UTF-8")
            client.close()
        } catch (e: Exception) {
            throw BusinessException("Something went wrong. Verify if the location name or coordinate is correct.")
        }

        return try {
            JsonParser()
                    .parse(locationWeather).asJsonObject
                    .get("main").asJsonObject
                    .get("temp").asDouble
        } catch (e: Exception) {
            throw BusinessException("Something went wrong with a third party API. Try again later.")
        }

    }

}