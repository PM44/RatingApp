package com.example.ratingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_rating.*


class RatingFragment : Fragment(), RatingBar.OnRatingBarChangeListener {
    private lateinit var viewModel: RatingViewModel
    var rating:String="1"


    override fun onRatingChanged(p0: RatingBar?, p1: Float, p2: Boolean) {
        displayRating(p1.toInt())
        seekBar.progress = p1.toInt()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ratingbar.setOnRatingBarChangeListener(this);
        ratingbar.rating=1.0f
        viewModel = ViewModelProviders.of(this).get(RatingViewModel::class.java)

        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                internal var progress = 0

                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progressValue: Int, fromUser: Boolean
                ) {
                    progress = progressValue
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // Display the value in textview
                    progress.toString() + "/" + seekBar.max
                    ratingbar.rating = progress.toFloat()
                    displayRating(progress)
                }
            })

        submit.setOnClickListener {
            viewModel.storeData(rating)
            Navigation.findNavController(it).navigate(RatingFragmentDirections.actionRatingFragmentToListFragment())
        }

    }

    fun displayRating(x: Int) {
        textView.text = x.toString()
        rating=x.toString()
    }

}
