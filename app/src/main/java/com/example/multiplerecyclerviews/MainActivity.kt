package com.example.multiplerecyclerviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val list: ArrayList<Model> = ArrayList()
        list.add(
            Model(
                Model.TEXT_TYPE,
                "Hello. This is the Text-only View Type. Nice to meet you",
                0
            )
        )
        list.add(
            Model(
                Model.IMAGE_TYPE,
                "Hi. I display a cool image too besides the omnipresent TextView.",
                R.drawable.wtc
            )
        )
        list.add(
            Model(
                Model.AUDIO_TYPE,
                "Hey. Pressing the FAB button will playback an audio file on loop.",
                R.raw.sound
            )
        )
        list.add(
            Model(
                Model.IMAGE_TYPE,
                "Hi again. Another cool image here. Which one is better?",
                R.drawable.snow
            )
        )
        val adapter = MultiViewTypeAdapter(list, this)
        val linearLayoutManager = LinearLayoutManager(this,  LinearLayoutManager.VERTICAL, false)
        val mRecyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
        mRecyclerView.adapter = adapter
    }
}