package kedar.com.movies.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kedar.com.movies.models.Result



/**
 * A ViewModel object provides the data for a specific UI component, such as a fragment or activity,
 * and contains data-handling business logic to communicate with the model.
 * For example, the ViewModel can call other components to load the data, and it can forward user requests to modify the data.
 * The ViewModel doesn't know about UI components, so it isn't affected by configuration changes, such as recreating an activity when rotating the device.
 */

/**
 * This view model is used to submit the data for the adapter items.
 */
class ItemMovieViewModel(model: Result): ViewModel() {

    /**
     * Observable field classes may be used instead of creating an Observable object. It can also
     * create a calculated field, depending on other fields:
     * Fields of this type should be declared final (in kotlin val) because bindings only detect changes in the
     * field's value, not of the field itself.
     */
    val title: ObservableField<String> = ObservableField()
    val overview: ObservableField<String> = ObservableField()
    val poster: ObservableField<String> = ObservableField()
    val date: ObservableField<String> = ObservableField()


    init {
        title.set(model.title)
        overview.set(model.overview)
        poster.set(model.posterPath)
        date.set(model.releaseDate)

    }
}