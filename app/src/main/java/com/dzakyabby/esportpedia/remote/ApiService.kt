package com.dzakyabby.esportpedia.remote

import retrofit2.http.GET

interface ApiService {
    @GET("https://api.opendota.com/api/heroStats")
    suspend fun getHeroes(): List<HeroResponseItem>
}