package id.aibangstudio.moviedb.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.aibangstudio.moviedb.databinding.ItemRowBannerBinding
import id.aibangstudio.moviedb.domain.entity.MovieEntity

class CarouselBannerAdapter(private var mList: List<MovieEntity>): RecyclerView.Adapter<CarouselBannerAdapter.BannerViewHolder>() {

    inner class BannerViewHolder(private val binding: ItemRowBannerBinding): RecyclerView.ViewHolder(binding.root){
        fun bindItem(movie: MovieEntity) = with(binding){
            Glide.with(root.context)
                .load(movie.backdropUrl)
                .into(imgBanner)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            ItemRowBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bindItem(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setList(data: List<MovieEntity>){
        mList = data
        notifyDataSetChanged()
    }
}