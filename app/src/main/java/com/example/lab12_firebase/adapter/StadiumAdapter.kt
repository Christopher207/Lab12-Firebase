package com.example.lab12_firebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab12_firebase.R
import com.example.lab12_firebase.model.StadiumModel
import com.squareup.picasso.Picasso

class StadiumAdapter (private var lstStadiums: List<StadiumModel>):
    RecyclerView.Adapter<StadiumAdapter.StadiumViewHolder>(){
    class StadiumViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val ivStadium: ImageView = itemView.findViewById(R.id.ivStadium)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvLocation: TextView = itemView.findViewById(R.id.tvLocation)
        val tvCapacity: TextView = itemView.findViewById(R.id.tvCapacity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StadiumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StadiumViewHolder(layoutInflater.inflate(R.layout.item_stadium, parent, false))
    }

    override fun getItemCount(): Int {
        return lstStadiums.size
    }

    override fun onBindViewHolder(holder: StadiumViewHolder, position: Int) {
        val itemStadium = lstStadiums[position]
        holder.tvName.text = itemStadium.name.toString()
        holder.tvLocation.text = itemStadium.location.toString()
        holder.tvCapacity.text = itemStadium.capacity.toString()
        Picasso.get().load(itemStadium.image.toString()).into(holder.ivStadium)
    }

}
