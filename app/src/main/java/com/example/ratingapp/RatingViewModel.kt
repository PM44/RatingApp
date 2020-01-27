package com.example.ratingapp

import android.app.Application
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*


class RatingViewModel(application: Application) : BaseViewModel(application) {
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("data")

    fun storeData(rating: String) {
        val sdf = SimpleDateFormat("dd MMM yyyy hh:mm a")
        val userId = myRef.push().getKey()
        val currentDate = sdf.format(Date())
        val rating = Rating(currentDate, rating)
        myRef.child(userId!!).setValue(rating)
    }


}