package com.termyter.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.termyter.lab2.databinding.ActivityInfoPictureBinding

class InfoAnimeActivity : AppCompatActivity() {

    private var item = Anime()
    lateinit var binding: ActivityInfoPictureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoPictureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arguments = intent.extras
        if (arguments != null) {
            item = arguments.getSerializable("item") as Anime
        }
        binding.apply {
            textViewAuthorPicture.text = item.desc
            textViewTitlePicture.text = item.name
            imageView.setImageResource(item.imageId)
        }
    }
}