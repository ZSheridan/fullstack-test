package ai.gobots.zsheridan.frontendapplication.service

import ai.gobots.zsheridan.frontendapplication.exception.BusinessException
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class MusicSuggestionAPIService {

    private val baseUrl = "http://localhost:5000/"

    fun getPlaylist(location: String): JsonArray {
        val listLocation = location.replace("\\s+".toRegex(), " ").split(",")
        return when (listLocation.size) {
            1 -> {
                playlistByCityName(listLocation[0])
            }
            2 -> {
                playlistByCoordinate(listLocation[0], listLocation[1])
            }
            else -> {
                throw BusinessException("Number of attributes invalid. Only one city name OR coordinates as 'lat,lon'.")
            }
        }
    }

    private fun playlistByCityName(cityName: String): JsonArray {
        return httpRequest("cityName?city=$cityName")
    }

    private fun playlistByCoordinate(lat: String, lon: String): JsonArray {
        return httpRequest("cityCoordinate?lat=${lat.toDouble()}&lon=${lon.toDouble()}")
    }

    private fun httpRequest(modeLocation: String): JsonArray {

        val location = modeLocation.replace(" ", "%20")
        val client = HttpClients.createDefault()
        val httpGet = HttpGet("$baseUrl$location")

        var playlist = ""
        return try {
            playlist = EntityUtils.toString(client.execute(httpGet).entity, "UTF-8")
            client.close()
            JsonParser().parse(playlist).asJsonArray
        } catch (e: Exception) {
            throw BusinessException("Something went wrong. Verify if the city or coordinates really exist.")
        }
    }

}