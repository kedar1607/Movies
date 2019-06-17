package kedar.com.movies.network

import kedar.com.movies.models.Movies
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MoviesApiClient {

    /**
     * This module is responsible for getting movies asynchronously from the network
     * Deferred response means the calling component to call await until getting the response from the
     * network.
     */
    @GET(POPULAR) fun getMoviesAsync(): Deferred<Response<Movies>>

    companion object{
       const val BASE_URL = "https://api.themoviedb.org/3/movie/"
       const val POPULAR = "popular?api_key=4047e082a6737f1d8c5075c989f18a17&language=en-US&page=1"
    }

}