package com.example.ratingapp

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Rating(
    var date:String,
    var rating: String

)

{
   constructor():this("","")
}