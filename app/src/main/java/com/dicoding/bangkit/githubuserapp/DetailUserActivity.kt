package com.dicoding.bangkit.githubuserapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.bangkit.githubuserapp.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User

        Glide.with(this)
            .load(user.avatar)
            .circleCrop()
            .into(binding.imgAvatar)
        binding.apply {
            tvName.text = user.name
            tvUsername.text = user.username
            tvLocation.text = user.location
            tvFollowers.text = resources.getString(R.string.follower, user.followers)
            tvFollowing.text = resources.getString(R.string.following, user.following)
            tvCompany.text = user.company
            tvRepo.text = resources.getString(R.string.repository, user.repository)
        }
    }
}