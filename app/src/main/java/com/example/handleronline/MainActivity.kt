package com.example.handleronline

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.handleronline.adapter.UserAdapter
import com.example.handleronline.databinding.ActivityMainBinding
import com.example.handleronline.db.AppDatabase
import com.example.handleronline.entity.User
import java.lang.Exception
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var userAdapter: UserAdapter
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDatabase = AppDatabase.getInstance(this)

        initViews()
    }

    private fun initViews() {
        MyAsyncTask().execute()
        RvTask().execute()

        binding.button.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {
        val user = User()
        user.username = binding.etUsername.text.toString().trim()
        user.password = binding.etUserPassword.text.toString().trim()

        MyAsyncTask().execute(user)
    }

    inner class MyAsyncTask : AsyncTask<User, Void, Void>(){
        //Beginning
        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute: ")

        }

        override fun doInBackground(vararg params: User): Void? {
            //Running
            appDatabase.userDao().addUser(params[0])
            Log.d(TAG, "doInBackground: ")
            return null
        }

        override fun onPostExecute(result: Void?) {
            //Ending
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute: ")
            Toast.makeText(this@MainActivity, "Successfully added", Toast.LENGTH_SHORT).show()

        }
    }

    //This task prints user's info
    inner class RvTask : AsyncTask<Void, Void, List<User>>(){

        override fun onPreExecute() {
            super.onPreExecute()
            binding.progressBar.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg p0: Void?): List<User> {
            try {
                TimeUnit.SECONDS.sleep(3)
            }catch (e : Exception){
                e.printStackTrace()
            }
            return  appDatabase.userDao().getAllUsers()
        }

        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            refreshAdapter(result!!)
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun refreshAdapter(list: List<User>) {
        userAdapter = UserAdapter(list)
        binding.rvMain.adapter = userAdapter
    }
}