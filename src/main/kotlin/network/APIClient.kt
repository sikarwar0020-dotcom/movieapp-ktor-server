package com.vipin.network

import com.google.gson.GsonBuilder
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import models.GetRemoteMoviesModelClass

object APIClient {


    private val gson = GsonBuilder().setPrettyPrinting().create()
    val client by lazy { HttpClient() }

    private val apiKey = System.getenv("OMDB_API_KEY")




    suspend fun getMovies(): GetRemoteMoviesModelClass {
        val result = client.get("https://www.omdbapi.com/") {

            parameter("apikey", apiKey)
            parameter("type", "movie")
            parameter("s", "avenger")
            parameter("page", "1")


        }

        val json = result.bodyAsText()
        return gson.fromJson(json, GetRemoteMoviesModelClass::class.java)

    }


}