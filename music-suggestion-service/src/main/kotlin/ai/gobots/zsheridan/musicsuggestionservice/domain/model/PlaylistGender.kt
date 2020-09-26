package ai.gobots.zsheridan.musicsuggestionservice.domain.model

import kotlin.math.roundToInt

enum class PlaylistGender {

    PARTY, POP, ROCK, CLASSICAL;

    companion object {
        fun gender(temperature: Double): PlaylistGender {
            return when {
                temperature.roundToInt() > 30 -> {
                    PARTY
                }
                temperature.roundToInt() > 14 -> {
                    POP
                }
                temperature.roundToInt() >= 10 -> {
                    ROCK
                }
                else -> {
                    CLASSICAL
                }
            }
        }
    }

}