package com.termyter.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.termyter.lab2.databinding.ActivityMainBinding
import android.util.Log
import androidx.constraintlayout.widget.StateSet.TAG
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {

    val URL = "https://anime-facts-rest-api.herokuapp.com/api/v1/"
    var FACT = ""
    var okHttpClient: OkHttpClient = OkHttpClient()

    private val adapter = AnimesAdapter()
    private var pictureGallery = arrayListOf<Anime>()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadRandomFact()

    }

    private fun init(){
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerView.adapter = adapter
        }
        adapter.addPicture(pictureGallery)
    }

    private fun loadRandomFact() {


        val request: Request = Request.Builder().url(URL).build()

        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()!!.string()
                try {
                    val jsonResponse = JSONObject(responseBody)
                    val jsonQuestions = jsonResponse.getJSONArray("data")
                    for (i in 0 until jsonQuestions.length()) {
                        val jsonQuestion = jsonQuestions.getJSONObject(i)
                        val text = jsonQuestion.get("anime_name").toString()
                        val img = jsonQuestion.get("anime_img").toString()
                        val fact = desc(URL + text,i)
                        Log.d(TAG, fact)
                        pictureGallery.add(Anime( img,text,FACT))
                        Log.d(TAG, FACT)
                    }
                    runOnUiThread {
                        init()

                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

        })

    }

    private fun desc(url:String, i:Int):String {
        var fact:String = ""

        val request: Request = Request.Builder().url(url).build()

        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()!!.string()
                try {
                    val jsonResponse = JSONObject(responseBody)
                    val jsonQuestions = jsonResponse.getJSONArray("data")
                    for (j in 0 until jsonQuestions.length()) {
                        val jsonQuestion = jsonQuestions.getJSONObject(j)
                        val text1 = jsonQuestion.get("fact").toString()
                        pictureGallery.get(i).desc += (j+1).toString() + ". " + text1 + "\n\n"
                        Log.d(TAG, FACT)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }

        })
        Log.d(TAG, fact)
        return fact
    }
}
