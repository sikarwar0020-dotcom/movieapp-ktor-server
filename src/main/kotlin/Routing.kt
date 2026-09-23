package com.vipin

import com.vipin.repository.MoviesRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    val moviesRepository = MoviesRepository()



    routing {
        route("/api/movies") {


            get {
                val movies = moviesRepository.getMovies()
                call.respond(movies)
            }


            get("/{id}") {
                val id = call.parameters["id"] ?: throw BadRequestException("Movie ID is required")

                val movie = moviesRepository.getSearchMovies(id)

                if (movie == null) {
                    call.respond(
                        HttpStatusCode.NotFound, "Movie with ID $id not found"
                    )
                    return@get
                }

                call.respond(movie)
            }

        }
    }
}