package ai.gobots.zsheridan.frontendapplication.controller

import ai.gobots.zsheridan.frontendapplication.service.MusicSuggestionAPIService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller()
class FrontController(
        @Autowired
        private val musicSuggestionAPIService: MusicSuggestionAPIService
) {

    @GetMapping("/playlist")
    fun playlist(): String {
        return "index"
    }

    @PostMapping("/playlist")
    fun getPlaylist(@RequestParam location: String, model: Model): String {

        model["playlist"] = musicSuggestionAPIService.getPlaylist(location)

        return "list_songs"
    }

}