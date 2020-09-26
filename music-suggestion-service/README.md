# Backend Service

## Context

This project provides a list of songs through an API and HTTP requests, depending on the weather of a specific location.

## Observations
 - Before running this project, the file domain/config/Config-APIKEY.kt should be properly managed.
 - The API accepts HTTP GET requests, requiring parameters for the location (city name or coordinates).
 - The API returns a List of String containing the songs names for the playlist.
 - It runs on the HTTP PORT 5000 (configured on application.properties)