# Fullstack Test

## Before Execution

 - Before running this project, the file Config-APIKEY.kt inside music-suggestion-service/src/main/kotlin/ai/gobots/zsheridan/musicsuggestionservice/config should be properly managed, according to its instructions.
 - After that, run the script run.sh inside this folder.
 - The application should be available at http://localhost:5001/home

## Context

Gobots wants to create a new service to suggest music to its users. The suggestions will depend on the city and the climate where the user is.

## Task

Create a micro-service able to accept RESTful requests receiving as parameter a location and returns a playlist suggestion according to the current temperature. Use any language and framework you want (Kotlin/Java + Spring, Python + Django/Flask etc), however Kotlin + Spring is recommended.

Also create a simple frontend which sends the location to the microservice and shows the music suggestions. Use any language and framework you want (JQuery, ReactJS, Angular etc).

### Observations
 - Location can be represented as city name or lat long coordinates.
 - A playlist can be represented as a list of music names.

## Business rules
* If temperature (celcius) is above 30 degrees, suggest tracks for party
* In case temperature is between 15 and 30 degrees, suggest pop music tracks
* If it's a bit chilly (between 10 and 14 degrees), suggest rock music tracks
* Otherwise, if it's freezing outside, suggests classical music tracks

## Hints
You can make usage of OpenWeatherMaps API (https://openweathermap.org) to fetch temperature data and Spotify (https://developer.spotify.com) to suggest the tracks as part of the playlist.

## Submission
Fork this project and share it with your interviwer
