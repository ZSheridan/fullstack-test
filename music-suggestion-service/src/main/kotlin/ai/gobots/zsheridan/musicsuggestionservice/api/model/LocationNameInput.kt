package ai.gobots.zsheridan.musicsuggestionservice.api.model

import javax.validation.constraints.NotBlank

class LocationNameInput (
        @get:NotBlank
        var locationName: String? = null
)