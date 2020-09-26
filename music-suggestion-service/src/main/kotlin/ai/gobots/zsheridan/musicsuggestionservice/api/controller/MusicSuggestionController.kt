package ai.gobots.zsheridan.musicsuggestionservice.api.controller

import ai.gobots.zsheridan.musicsuggestionservice.api.model.LocationNameInput
import ai.gobots.zsheridan.musicsuggestionservice.domain.service.MusicSuggestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@Validated
class MusicSuggestionController(
        @Autowired
        private val musicSuggestionService: MusicSuggestionService
) {

    @GetMapping("/cityName")
    fun getPlaylistByCity(@Valid @RequestParam city: LocationNameInput): ResponseEntity<List<String>> {
        return ResponseEntity.ok(musicSuggestionService.getPlaylistByCity(city.locationName!!))
    }

    @GetMapping("/cityCoordinate")
    fun getPlaylistByCoordinate(@RequestParam lat: Double,
                                @RequestParam lon: Double): ResponseEntity<List<String>> {
        return ResponseEntity.ok(musicSuggestionService.getPlaylistByCoordinate(lat, lon))
    }

}