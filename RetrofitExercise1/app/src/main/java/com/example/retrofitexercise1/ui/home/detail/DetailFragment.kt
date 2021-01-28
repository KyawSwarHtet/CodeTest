package com.example.retrofitexercise1.ui.home.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitexercise1.R
import com.example.retrofitexercise1.ui.home.api.ApiClient
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    lateinit var detailViewModel : DetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var item = arguments?.let {
            DetailFragmentArgs.fromBundle(it)
        }
            var id = item?.movieId
            detailViewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
            detailViewModel.loadDetails(id!!)

            observerViewModel()
        }
        fun observerViewModel()
        {
            detailViewModel.getResult().observe(viewLifecycleOwner, Observer { details ->
                Picasso.get().load(ApiClient.Image_Patch + details.backdrop_path).placeholder(R.drawable.movies).into(imgMovieDetails)
                textMovieName.text =details.title

                var date : String = details.release_date.substring(0,4)

                textReleaseDate.text = "(" +date +")"
                textTagLine.text=details.tagline
                textOverView.text = details.overview
                textVote.text = details.vote_average.toString()
                textType.text=details.genres[0].name
            })

        }
    }




