package com.vipin

import com.vipin.network.APIClient
import com.vipin.repository.MoviesRepository
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.moviesRouting(moviesRepository: MoviesRepository){

    routing {

        route("/api") {


            get("/movies"){
                val movies = APIClient.getMovies()
                call.respond(movies)
            }













        }










    }





}