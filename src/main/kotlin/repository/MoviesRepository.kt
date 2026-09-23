package com.vipin.repository

import com.vipin.models.MoviesModelClass
import com.vipin.network.APIClient

class MoviesRepository {

    val api by lazy { APIClient }


    suspend fun getMovies(): List<MoviesModelClass> {
        val result = api.getMovies()
        if (result.Response != "True") {
            throw Exception("Unable to fetch movies")
        }

        return result.Search.map { movie ->

            MoviesModelClass(
                id = movie.imdbID,
                title = movie.Title,
                year = movie.Year,
                poster = movie.Poster,
                url = "https://archive.org/download/ArcherProductionsInc/DuckandC1951.mp4"
            )
        }


    }


    suspend fun getSearchMovies(id: String): MoviesModelClass? {
        return getMovies().find { it.id == id.lowercase() }
    }


}