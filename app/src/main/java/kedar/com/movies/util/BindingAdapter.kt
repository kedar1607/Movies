package kedar.com.movies.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

/**
 * Android Binding Custom Setters:
 * please follow the post :
 * https://medium.com/androiddevelopers/android-data-binding-custom-setters-55a25a7aea47
 *
 */
class BindingAdapter {

    companion object {
        @JvmStatic
        /**
         * loadImage is the app property that's been used in the movie_item.xml for the
         * poster image view. Make sure that "loadImage" is used in annotation BindingAdapter, as a name of the function
         * and the app property value in the xml file for that UI element which indicates what function will it run to
         * load data (in this case image) in the view.
         */
        @BindingAdapter("loadImage")
        fun loadImage(imageView: ImageView, url: String){
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+ url).into(imageView)
        }

    }
}