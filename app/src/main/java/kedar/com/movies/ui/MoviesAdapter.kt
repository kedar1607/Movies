package kedar.com.movies.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import kedar.com.movies.R
import kedar.com.movies.databinding.MovieItemBinding
import kedar.com.movies.models.Result
import kedar.com.movies.viewmodels.ItemMovieViewModel

class MoviesAdapter(val context: Context): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    /**
     * Data to be submitted.
     */
    protected var movies: List<Result> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /**
         * This is the way to inflate each item in the recycler view using data binding concept
         * (This name comes from the name given to the xml layout file e.g. in this case movie_item.xml )
         */
        val binding: MovieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.movie_item, parent, false);
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /**
         * Unlike traditional view holder binding of view data to views, we bind the data to the view holder
         * Internal implementations take care of setting the data to the views.
         */
        holder.bind(movies[holder.adapterPosition])
    }


    fun setData(movie: List<Result>){
        this.movies = movie
    }

    class ViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(movieModel: Result){
            val viewModel = ItemMovieViewModel(movieModel)
            /**
             * itemMovie is the name given to the data variable in the movie_item.xml file
             * In data binding we pass the view model instead of the setting views to the item views.
             * ViewHolders in this case take binding in it's constructor.
             */
            binding.itemMovie = viewModel
        }
    }
}