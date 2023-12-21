package com.example.campusapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class SignupActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        usernameEditText = findViewById(R.id.regusername)
        passwordEditText = findViewById(R.id.regpassword)
        registerButton = findViewById(R.id.btnregister)

        registerButton.setOnClickListener {
            // Perform registration
            register()
        }
    }

    private fun register() {
        val url = "https://831zix.000webhostapp.com/signup.php" // Replace with your PHP register endpoint

        val username = usernameEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val nama = findViewById<EditText>(R.id.regnama).text.toString().trim()
        val nomorhp = findViewById<EditText>(R.id.regnohp).text.toString().trim()

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                // Handle the response from the server
                Toast.makeText(this, response, Toast.LENGTH_SHORT).show()

                try {
                    val jsonObject = JSONObject(response)
                    val message = jsonObject.getString("message")
                    val success = jsonObject.getBoolean("success")

                    if (success) {
                        // Registration successful, navigate to LoginActivity
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                        finish() // Close the current activity
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "JSON parsing error: " + e.message, Toast.LENGTH_SHORT)
                        .show()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Volley error: ${error.message}", Toast.LENGTH_SHORT).show()
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                params["nama"] = nama
                params["nomorhp"] = nomorhp
                return params
            }
        }

        Volley.newRequestQueue(this).add(stringRequest)
    }

}
