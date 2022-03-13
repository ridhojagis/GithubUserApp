package com.dicoding.bangkit.githubuserapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.bangkit.githubuserapp.databinding.ActivityMainBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvUsers.setHasFixedSize(true)

        list.addAll(listUsers)
        showRecyclerList()
    }

    private val listUsers: ArrayList<User>
        @SuppressLint("Recycle")
        get() {
            val dataName = resources.getStringArray(R.array.name)
            val dataUsername = resources.getStringArray(R.array.username)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataAvatar = resources.obtainTypedArray(R.array.avatar)
            val dataFollowers = resources.getIntArray(R.array.followers)
            val dataFollowing = resources.getIntArray(R.array.following)
            val dataRepository = resources.getIntArray(R.array.repository)
            val dataCompany = resources.getStringArray(R.array.company)
            val listUser = ArrayList<User>()
            for (i in dataName.indices) {
                val user = User(dataName[i], dataUsername[i], dataLocation[i], dataAvatar.getResourceId(i, -1),  dataFollowers[i], dataFollowing[i],
                    dataRepository[i], dataCompany[i])
                listUser.add(user)
            }
            return listUser
        }

    private fun showRecyclerList() {
        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvUsers.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
        }
        val listUserAdapter = ListUserAdapter(list)
        binding.rvUsers.adapter = listUserAdapter

        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User) {
        val detailUserIntent = Intent(this@MainActivity, DetailUserActivity::class.java)
        detailUserIntent.putExtra(DetailUserActivity.EXTRA_USER, user)
        startActivity(detailUserIntent)
    }
}