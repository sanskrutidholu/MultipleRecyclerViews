package com.example.multiplerecyclerviews

import android.content.Context
import android.media.MediaPlayer
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.TextView

class MultiViewTypeAdapter(private val dataSet: ArrayList<Model>, context: Context) :

    RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    var mContext: Context = context
    var mPlayer: MediaPlayer? = null
    private var fabStateVolume = false

    class TextTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtType: TextView = itemView.findViewById(R.id.type)
    }

    class ImageTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtType: TextView = itemView.findViewById(R.id.type)
        var image: ImageView = itemView.findViewById(R.id.background) as ImageView

    }

    class AudioTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtType: TextView = itemView.findViewById(R.id.type)
        var fab: FloatingActionButton = itemView.findViewById(R.id.fab)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {
            Model.TEXT_TYPE -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.text_type, parent, false)
                return TextTypeViewHolder(view)
            }
            Model.IMAGE_TYPE -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.image_type, parent, false)
                return ImageTypeViewHolder(view)
            }
            Model.AUDIO_TYPE -> {
                view =
                    LayoutInflater.from(parent.context).inflate(R.layout.audio_type, parent, false)
                return AudioTypeViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataSet[position].type) {
            0 -> Model.TEXT_TYPE
            1 -> Model.IMAGE_TYPE
            2 -> Model.AUDIO_TYPE
            else -> -1
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, listPosition: Int) {
        val `object` = dataSet[listPosition]
        when (`object`.type) {
            Model.TEXT_TYPE -> (holder as TextTypeViewHolder?)!!.txtType.text =
                `object`.text
            Model.IMAGE_TYPE -> {
                (holder as ImageTypeViewHolder?)!!.txtType.text = `object`.text
                holder.image.setImageResource(`object`.data)
            }
            Model.AUDIO_TYPE -> {
                (holder as AudioTypeViewHolder?)!!.txtType.text = `object`.text
                holder.fab.setOnClickListener {
                    if (fabStateVolume) {
                        if (mPlayer!!.isPlaying) {
                            mPlayer!!.stop()
                        }
                        holder.fab.setImageResource(R.drawable.volume)
                        fabStateVolume = false
                    } else {
                        mPlayer = MediaPlayer.create(mContext, R.raw.sound)
                        mPlayer!!.isLooping = true
                        mPlayer!!.start()
                        holder.fab.setImageResource(R.drawable.mute)
                        fabStateVolume = true
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}