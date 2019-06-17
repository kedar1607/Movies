package kedar.com.movies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kedar.com.movies.viewmodels.MoviesViewModel
import kedar.com.movies.R
import kedar.com.movies.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Movies View Model that is responsible for passing data from the network call to the UI components
    //Activity observes on the Livedata from view model
    lateinit var moviesViewModel: MoviesViewModel

    //Data Binding : This allows us to bind data to the UI  without having to find IDs during at runtime
    private lateinit var binding: ActivityMainBinding

    //Movies Adapter
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * The expression language allows you to write expressions that connect variables to the views in the layout.
         * The Data Binding Library automatically generates the classes required to bind the views in the layout with your data objects.
         * The library provides features such as imports, variables, and includes that you can use in your layouts.

        These features of the library coexist seamlessly with your existing layouts.
        For example, the binding variables that can be used in expressions are defined inside a data element that is a sibling of the UI layout's
        root element.
        Both elements are wrapped in a layout tag, as shown in the following example:
         */
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        /**
         *Get an instance of View Model from View Model Providers
         */
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)

        /**
         * Set the view model responsible for data binding
         * (movieActivity is the name given to the data variables in the activity_main).
         */
        binding.movieActivity = moviesViewModel

        /**
         * Set up other ui components
         */
        setupRecyclerView()

        /**
         * Observe the changes dispatched by the view models and pass them on to adapters
         */
        observeMoviesDownload()
    }

    /**
     * This method observes on to the LiveData instance in the ViewModel and any changes result sets are passed
     * on to the adapter.
     */
    private fun observeMoviesDownload(){
        moviesViewModel.moviesInScope.observe(this, Observer { movies ->
            val moviesResults =  movies.results

            Log.d(tag, moviesResults.toString())

            adapter.setData(moviesResults)

            //Todo This part could be replaced by DiffUtils and Pagination
            adapter.notifyDataSetChanged()
            Log.d(tag, moviesResults.toString())
        })
    }


    /**
     * Set up the UI components.
     */
    private fun setupRecyclerView(){
        val layoutManager = LinearLayoutManager(this)
        // Set the view model responsible for data binding
        // (mainRecyclerView is the name given to the data variables in the activity_main).
        binding.mainRecyclerView.layoutManager = layoutManager
        adapter = MoviesAdapter(this)
        binding.mainRecyclerView.adapter = adapter

    }

    companion object{
        const val tag = "LOADING_MOVIES"
    }
}
