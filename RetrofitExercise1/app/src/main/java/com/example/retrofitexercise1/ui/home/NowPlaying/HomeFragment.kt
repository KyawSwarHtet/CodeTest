package com.example.retrofitexercise1.ui.home.NowPlaying

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofitexercise1.R
import com.example.retrofitexercise1.ui.home.NowPlaying.Model.Result
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() ,NowPlayingAdapter.ClickListener {

  private lateinit var nowPlayngViewModel: NowPlayingViewModel
  lateinit var nowPlayingAdapter: NowPlayingAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val root = inflater.inflate(R.layout.fragment_home, container, false)

    return root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    nowPlayngViewModel= ViewModelProvider(this).get(NowPlayingViewModel::class.java)
    nowPlayingAdapter = NowPlayingAdapter()

    recyclerNowPlaying.apply {
      layoutManager = GridLayoutManager(context,3)
      adapter = nowPlayingAdapter
    }

      nowPlayingAdapter.setOnClickListener(this)
    observeViewModel()
    }
  private fun observeViewModel(){
    nowPlayngViewModel.getResult().observe(
      viewLifecycleOwner, Observer { new ->
        Log.d("error",new.toString())

        nowPlayingAdapter.updateResult(new.results)
      }
    )
    }

  override fun onResume() {
    super.onResume()
    nowPlayngViewModel.loadNowPlaying()
  }

  override fun onClick(result: Result) {
    var action = HomeFragmentDirections.actionNavHomeToDetailFragment2(result.id)
    findNavController().navigate(action)
  }

}