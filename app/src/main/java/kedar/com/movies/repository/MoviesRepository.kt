package kedar.com.movies.repository

import android.util.Log
import kedar.com.movies.models.Movies
import kedar.com.movies.network.WebAccess
import kedar.com.movies.ui.MainActivity
import java.io.IOException


/**
 This repository is responsible for making the async calls to network. View model holds the instance of the repository
 Instead, our ViewModel delegates the data-fetching process to a new module, a repository.
Repository modules handle data operations. They provide a clean API so that the rest of the app can retrieve this data easily.
They know where to get the data from and what API calls to make when data is updated. You can consider
repositories to be mediators between different data sources, such as persistent models, web services, and caches.
 */
class MoviesRepository {
    /**
     * This this a suspend fuction that must be called from another co-routine / suspend function
     * This gives the freedom of not having call backs or changing the coroutines' scope
     */
    suspend fun loadMoviesAsync() : Movies? {
        try {
            /**
             * Execute web request through coroutine call adapter & retrofit
              */
            val webResponse = WebAccess.moviesApi.getMoviesAsync().await()


            if (webResponse.isSuccessful) {
                /**
                 * Get the returned & parsed JSON from the web response.
                 * Type specified explicitly here to make it clear that we already
                 * get parsed contents.
                 *
                 * This is where the co-routine switches the dispatcher. But it just looks like synchronous call.
                 */
                val movies: Movies? = webResponse.body()

                Log.d(MainActivity.tag, movies?.toString())

                return movies

            } else {
                Log.d(MainActivity.tag, "Error ${webResponse.code()}")
                return null
            }
        }catch (e : IOException){
            Log.e(MainActivity.tag, "Exception " + e.printStackTrace())
            return null
        }
    }
}