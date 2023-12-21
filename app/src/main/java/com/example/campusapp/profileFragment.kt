package com.example.campusapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class profileFragment : Fragment() {

    private lateinit var usernameTextView: TextView
    private lateinit var nameProfileTextView: TextView
    private lateinit var nomorHpTextView: TextView

    private var loggedInUserId: String? = null // Store the user_id of the logged-in user

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        usernameTextView = rootView.findViewById(R.id.username)
        nameProfileTextView = rootView.findViewById(R.id.nameprofile)
        nomorHpTextView = rootView.findViewById(R.id.nomorhp)

        // Assume you have stored the logged-in user_id in a variable (loggedInUserId)
        loggedInUserId = "1" // Replace with the actual user_id

        // Initialize Volley RequestQueue
        val queue: RequestQueue = Volley.newRequestQueue(requireContext())

        // Replace "your_api_url" with the actual URL of your profile API
        val url = "https://831zix.000webhostapp.com/fetchlogin.php?user_id=$loggedInUserId"

        // Fetch data using Volley
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                // Process the JSON response
                parseJson(response)
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
        // Assume you have only one user in the API response
        val jsonObject: JSONObject = response.getJSONObject(0)

        // Extract data from JSON and update UI
        val username = jsonObject.getString("username")
        val nama = jsonObject.getString("nama")
        val nomorhp = jsonObject.getString("nomorhp")

        usernameTextView.text = username
        nameProfileTextView.text = nama
        nomorHpTextView.text = nomorhp
    }
}


