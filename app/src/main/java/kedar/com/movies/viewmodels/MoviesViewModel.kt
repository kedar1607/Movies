package kedar.com.movies.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kedar.com.movies.repository.MoviesRepository
import kedar.com.movies.models.Movies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * A ViewModel object provides the data for a specific UI component, such as a fragment or activity,
 * and contains data-handling business logic to communicate with the model.
 * For example, the ViewModel can call other components to load the data, and it can forward user requests to modify the data.
 * The ViewModel doesn't know about UI components, so it isn't affected by configuration changes, such as recreating an activity when rotating the device.
 */

class MoviesViewModel : ViewModel(){

    /**
     * In Kotlin, all coroutines run inside a CoroutineScope.
     * A scope controls the lifetime of coroutines through its job.
     * When you cancel the job of a scope, it cancels all coroutines started in that scope.
     * On Android, you can use a scope to cancel all running coroutines when,
     * for example, the user navigates away from an Activity or Fragment.
     * Scopes also allow you to specify a default dispatcher.
     * A dispatcher controls which thread runs a coroutine.
     */
    private val viewModelJob = Job()

    /**
     * In our example, viewModelScope will start coroutines in Dispatchers.Main which is the main thread on Android.
     * A coroutine started on the main won't block the main thread while suspended.
     * Since a ViewModel coroutine almost always updates the UI on the main thread,
     * starting coroutines on the main thread is a reasonable default. As we'll see later in this code,
     * a coroutine can switch dispatchers any time after it's started.
     * For example, a coroutine can start on the main dispatcher then use another dispatcher to parse a large JSON result off the main thread.
     */
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val moviesRepository = MoviesRepository()

    val moviesInScope = MutableLiveData<Movies>()

    init {
        loadMoviesFromNetwork()
    }

    private fun loadMoviesFromNetwork(){
        /**
         * This launch will start the process of loading movies from repository in this viewModelScope
         * The dispatcher will  change later when the results from this calls are returned from network
         * At that time we do not need to change the dispatcher because viewModelScope is made of IO and Main dispatchers
         *
         */
        viewModelScope.launch {
            moviesInScope.value = moviesRepository.loadMoviesAsync()
        }
    }


    /**
     * onCleared is called when the ViewModel is no longer used and will be destroyed.
     * This typically happens when the user navigates away from the Activity or Fragment that was using the ViewModel.
     * If we wanted to cancel the scope that we saw in the previous section, you'd have to include the following code.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}