package com.example.campusapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class eventFragment : Fragment() {

    private lateinit var rvData: RecyclerView
    private val listEvent = ArrayList<event>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_event, container, false)

        rvData = rootView.findViewById(R.id.event_rc)
        rvData.setHasFixedSize(true)
        rvData.layoutManager = LinearLayoutManager(requireContext())

        // Initialize Volley RequestQueue
        val queue: RequestQueue = Volley.newRequestQueue(requireContext())

        // Replace "your_api_url" with the actual URL of your API
        val url = "https://831zix.000webhostapp.com/event.php"

        // Fetch data using Volley
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                // Process the JSON response
                parseJson(response)
                // Create and set up the RecyclerView adapter
                val eventAdapter = EventAdapter(listEvent)
                rvData.adapter = eventAdapter
            },
            { error ->
                // Handle errors
                error.printStackTrace()
            })

        // Add the request to the RequestQueue
        queue.add(jsonArrayRequest)

        return rootView
    }

    private fun parseJson(response: JSONArray) {
        for (i in 0 until response.length()) {
            val jsonObject: JSONObject = response.getJSONObject(i)
            val Event = event(
                jsonObject.getString("eventname"),
                jsonObject.getString("tanggalevent"),
                jsonObject.getString("waktuevent"),
                jsonObject.getString("eventfee")
            )
            listEvent.add(Event)
        }
    }
}
