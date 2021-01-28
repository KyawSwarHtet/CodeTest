package com.example.retrofitexercise1.ui.home.NowPlaying

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexercise1.R
import com.example.retrofitexercise1.ui.home.NowPlaying.Model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_now_playing.view.*

class NowPlayingAdapter(var nowPlayingList : List<Result> = ArrayList()): RecyclerView.Adapter<NowPlayingAdapter.NowPlayingViewHolder>()
{
    var mClickListener : ClickListener? = null

    interface ClickListener{
        fun onClick(result: Result)
    }
    fun setOnClickListener(clickListener :ClickListener)
    {
        this.mClickListener=clickListener
    }
    inner class NowPlayingViewHolder(itemView : View):RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        lateinit var result: Result
        init {
            itemView.setOnClickListener(this)
        }
        fun bindNowPlaying(result: Result){
            this.result = result
            var imgUrl :String = "https://image.tmdb.org/t/p/w500/"
            Picasso.get().load(imgUrl + result.poster_path).placeholder(R.drawable.movies).into(itemView.posterImage)
        }

        override fun onClick(p0: View?) {
            mClickListener?.onClick(result)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingViewHolder {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.item_now_playing,parent,false)
        return NowPlayingViewHolder(view)
    }

    override fun onBindViewHolder(holder: NowPlayingViewHolder, position: Int) {
        holder.bindNowPlaying(nowPlayingList[position])
    }

    override fun getItemCount(): Int {
        return nowPlayingList.size
    }
    fun updateResult(result : List<Result>){
        nowPlayingList = result
        notifyDataSetChanged()
    }
}