package com.example.ratingapp

import android.app.Application
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random


class RatingViewModel(application: Application):BaseViewModel(application) {
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("data")
    var ratingList = arrayListOf<Rating>()

    fun storeData(rating: String) {
        val sdf = SimpleDateFormat("dd MMM yyyy hh:mm:ss")
        val userId = myRef.push().getKey()
        val currentDate = sdf.format(Date())
        val rating = Rating(rating, currentDate)
        myRef.child(userId!!).setValue(rating)
        getData()
    }

    fun getData() {
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                addDataToList(dataSnapshot)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        myRef.parent?.addListenerForSingleValueEvent(menuListener)
       // myRef.child("28").addListenerForSingleValueEvent(menuListener)
    }


    private fun addDataToList(dataSnapshot: DataSnapshot) {
        dataSnapshot.children.forEach {
            ratingList.add(it.getValue(Rating::class.java)!!)
        }
    }

}