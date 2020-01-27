package com.example.ratingapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ratingapp.databinding.ItemListBinding

class RatingListAdapter(val ratingList: ArrayList<Rating>) :
    RecyclerView.Adapter<RatingListAdapter.RatingViewHolder>() {

    fun updateRatingList(newRatingList: List<Rating>) {
        ratingList.clear()
        ratingList.addAll(newRatingList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RatingViewHolder, position: Int) {
        holder.view.setRating(ratingList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingListAdapter.RatingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemListBinding>(layoutInflater, R.layout.item_list, parent, false)
        return RatingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ratingList.size
    }


    class RatingViewHolder(var view: ItemListBinding) : RecyclerView.ViewHolder(view.root) {

    }

}