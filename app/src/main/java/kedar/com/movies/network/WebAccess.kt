package kedar.com.movies.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Singleton pattern in Kotlin:
 * https://kotlinlang.org/docs/reference/object-declarations.html#object-declarations

 */
object WebAccess {
    /**
     * lazy() is a function that takes a lambda and returns an instance of Lazy<T>
     * which can serve as a delegate for implementing a lazy property: the first call to get()
     * executes the lambda passed to lazy() and remembers the result, subsequent calls to get()
     * simply return the remembered result.
     */
    val moviesApi: MoviesApiClient by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(MoviesApiClient.BASE_URL)
            /**
             * Moshi maps JSON to classes
             */
            .addConverterFactory(MoshiConverterFactory.create())
            /**
             * The call adapter handles threads.
             * It came from com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
              */
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return@lazy retrofit.create(MoviesApiClient::class.java)
    }
}