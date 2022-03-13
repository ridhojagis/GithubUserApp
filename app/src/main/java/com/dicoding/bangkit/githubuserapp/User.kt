package com.dicoding.bangkit.githubuserapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String,
    val username: String,
    val location: String,
    val avatar: Int,
    val followers: Int,
    val following: Int,
    val repository: Int,
    val company: String,
) : Parcelable
