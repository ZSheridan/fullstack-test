package ai.gobots.zsheridan.musicsuggestionservice.domain.service

import ai.gobots.zsheridan.musicsuggestionservice.domain.config.APIKEY
import ai.gobots.zsheridan.musicsuggestionservice.domain.exception.BusinessException
import ai.gobots.zsheridan.musicsuggestionservice.domain.model.PlaylistGender
import com.google.gson.JsonParser
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class SpotifyTracksApiService {

    private val spotifyToken = APIKEY.spotify
    private val baseURL1 = "https://api.spotify.com/v1/browse/categories/"
    private val urlSufix1 = "/playlists?country=BR&limit=1"
    private val baseURL2 = "https://api.spotify.com/v1/playlists/"
    private val numOfSongs = "40"
    private val market = "BR"
    private val urlSufix2 = "/tracks?market=$market&fields=items(track(name))&limit=$numOfSongs&offset=0"

    fun getPlaylist(gender: PlaylistGender): List<String> {
        val response = getHttpResponse(baseURL1, gender.toString().toLowerCase(), urlSufix1)
        val playlist = getHttpResponse(baseURL2, getPlaylistId(response), urlSufix2)
        return jsonStringToList(playlist)
    }

    private fun getHttpResponse(base: String, string: String, sufix: String): String {

        val client = HttpClients.createDefault()
        val httpGet = HttpGet("$base$string$sufix")
        httpGet.setHeader("Authorization", "Bearer $spotifyToken")

        var response = ""
        try {
            response = EntityUtils.toString(client.execute(httpGet).entity, "UTF-8")
            client.close()
        } catch (e: Exception) {
            throw BusinessException("We are having problems communicating with a third party API. Try again later.")
        }
        return response
    }

    private fun getPlaylistId(response: String): String {
        return try {
            JsonParser()
                    .parse(response).asJsonObject
                    .get("playlists").asJsonObject
                    .get("items").asJsonArray
                    .get(0).asJsonObject
                    .get("id").asString
        } catch (e: Exception) {
            throw BusinessException("Something went wrong with a third party API. Try again later.")
        }
    }

    private fun jsonStringToList(playlist: String): List<String> {
        val listOfSongs = mutableListOf<String>()
        JsonParser()
                .parse(playlist).asJsonObject
                .get("items").asJsonArray
                .forEach { track ->
                    listOfSongs.add(track.asJsonObject
                            .get("track").asJsonObject
                            .get("name").asString
                    )
                }
        return listOfSongs
    }

}