package com.example.lab12_firebase.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab12_firebase.R
import com.example.lab12_firebase.adapter.StadiumAdapter
import com.example.lab12_firebase.model.StadiumModel
import com.google.firebase.firestore.FirebaseFirestore

class StadiumFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_stadium, container, false)
        val rvStadium: RecyclerView = view.findViewById(R.id.rvStadium)
        var lstStadium: List<StadiumModel>
        val db = FirebaseFirestore.getInstance()

        db.collection("stadiums").addSnapshotListener { snap, error ->
            if (error != null) {
                Log.e("StadiumFragment FIREBASE ERROR", "Detalle del error: ${error.message}")
                return@addSnapshotListener
            }
            lstStadium = snap!!.documents.map{document->
                StadiumModel(
                    document["name"].toString(),
                    document["image"].toString(),
                    document["capacity"].toString(),
                    document["location"].toString()
                )
            }
            rvStadium.adapter = StadiumAdapter(lstStadium)
            rvStadium.layoutManager = LinearLayoutManager(requireContext())
        }

        return view
    }
}