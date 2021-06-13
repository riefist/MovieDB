package id.aibangstudio.moviedb.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.aibangstudio.moviedb.databinding.ItemRowHorizontalMovieBinding
import id.aibangstudio.moviedb.domain.entity.MovieEntity

class ListMovieHorizontalAdapter: RecyclerView.Adapter<ListMovieHorizontalAdapter.HorizontalMovieVH>() {

    private var mList = listOf<MovieEntity>()
    private var mListener : OnMovieListener? = null

    inner class HorizontalMovieVH(val binding: ItemRowHorizontalMovieBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(movie: MovieEntity) = with(binding){
            Glide.with(root.context)
                .load(movie.posterUrl)
                .into(imgPosterMovie)

            root.setOnClickListener {
                mListener?.onClicked(movie)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMovieVH {
        return HorizontalMovieVH(
            ItemRowHorizontalMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HorizontalMovieVH, position: Int) {
        holder.bindItem(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setList(data: List<MovieEntity>){
        mList = data
        notifyDataSetChanged()
    }

    fun setListener(listener: OnMovieListener){
        mListener = listener
    }

    interface OnMovieListener {
        fun onClicked(movie: MovieEntity)
    }

}