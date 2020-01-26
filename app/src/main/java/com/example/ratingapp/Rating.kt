package com.example.ratingapp

data class Rating(
    var date:String,
    var rating: String

)

{
   constructor():this("","")
}