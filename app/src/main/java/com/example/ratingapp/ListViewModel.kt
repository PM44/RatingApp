package com.example.ratingapp

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ListViewModel(application: Application) : BaseViewModel(application) {
    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("data")
    var ratingList = arrayListOf<Rating>()
    val mutableList = MutableLiveData<List<Rating>>()


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
    }


    private fun addDataToList(dataSnapshot: DataSnapshot) {
        for (postSnapshot in dataSnapshot.children) {
            for (data in postSnapshot.children) {
                ratingList.add(data.getValue(Rating::class.java)!!)
            }
        }
        mutableList.value = ratingList
    }
}