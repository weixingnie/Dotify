package edu.uw.wn5.dotify.Data


import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/*
    Data class to represent a User of the App
*/

@Parcelize
data class User(
        val username:String,
        val firstName: String,
        val lastName: String,
        val hasNose: Boolean,
        val platform: Double,
        val profilePicURL: String
) : Parcelable