package com.example.campusapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class communityFragment : Fragment() {

    private lateinit var rvData: RecyclerView
    private val listCommunity = ArrayList<community>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_community, container, false)

        rvData = rootView.findViewById(R.id.community_rc)
        rvData.setHasFixedSize(true)
        rvData.layoutManager = LinearLayoutManager(requireContext())

        val url = "https://831zix.000webhostapp.com/community.php"
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            { response ->

                for (i in 0 until response.length()) {
                    val jsonObject: JSONObject = response.getJSONObject(i)
                    val Community = community(
                        jsonObject.getString("recruitment_position"),
                        jsonObject.getString("organisasi"),
                        jsonObject.getString("slot_posisi"),
                        jsonObject.getString("tanggal"),
                        jsonObject.getString("tanggal_akhir"),
                        jsonObject.getString("syarat"),
                        jsonObject.getString("url")
                    )
                    listCommunity.add(Community)
                }


                val communityAdapter = CommunityAdapter(listCommunity)
                rvData.adapter = communityAdapter
            },
            { error ->

            }
        )


        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(jsonArrayRequest)

        return rootView
    }
}
