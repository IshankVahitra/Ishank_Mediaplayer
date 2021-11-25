package com.example.ishank_mediaplayer.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.ishank_mediaplayer.databinding.ItemTrackBinding
import com.example.ishank_mediaplayer.model.AudioTrackData

class AudioTackAdapter(private val onItemClick: (audioData: AudioTrackData) -> Unit) :
    RecyclerView.Adapter<AudioViewHolder>() {

    var audioList = mutableListOf<AudioTrackData>()

    fun setList(audioList: List<AudioTrackData>) {
        this.audioList = audioList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemTrackBinding.inflate(inflater, parent, false)
        return AudioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audiodata = audioList[position]
        holder.binding.tvAudioName.text = audiodata.title
        holder.binding.tvAudioDesc.text = audiodata.description
        Glide.with(holder.itemView.context).load(audiodata.catImage)
            .transform(
                CenterCrop(), RoundedCorners(10)
            )
            .into(holder.binding.audioImage)

        holder.binding.root.setOnClickListener { onItemClick(audiodata) }


    }

    override fun getItemCount(): Int {
        return audioList.size
    }
}

class AudioViewHolder(val binding: ItemTrackBinding) : RecyclerView.ViewHolder(binding.root) {

}