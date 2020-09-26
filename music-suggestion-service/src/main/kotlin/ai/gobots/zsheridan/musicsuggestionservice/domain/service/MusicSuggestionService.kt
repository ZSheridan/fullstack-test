package ai.gobots.zsheridan.musicsuggestionservice.domain.service

import ai.gobots.zsheridan.musicsuggestionservice.domain.model.PlaylistGender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MusicSuggestionService(
        @Autowired
        private val openWeatherMapApiService: OpenWeatherMapApiService,
        @Autowired
        private val spotifyTracksApiService: SpotifyTracksApiService
) {

    fun getPlaylistByCity(city: String): List<String> =
            getPlaylist("q=$city")

    fun getPlaylistByCoordinate(lat: Double, lon: Double): List<String> =
            getPlaylist("lat=$lat&lon=$lon")

    private fun getPlaylist(location: String): List<String> {
        val gender = PlaylistGender.gender(cityTemperature(location))
        return spotifyTracksApiService.getPlaylist(gender)
    }

    private fun cityTemperature(location: String): Double {
        return openWeatherMapApiService.getLocationTemperature(location)
    }

}