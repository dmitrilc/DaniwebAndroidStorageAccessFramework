package com.example.daniwebandroidstorageaccessframework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts.OpenDocument

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openPdfLauncher = registerForActivityResult(OpenDocument()) { uri ->


            //Creates the Intent object, specifying the uri and mime type
            val openPdfIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                type = "application/pdf"
                data = uri
                //grant the handler permission to read the Uri.
                //Should sanitize to avoid leaking sensitive user information.
                flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            }

            //Asks the system to start another activity that can handle Intent
            startActivity(openPdfIntent)
        }

        //Gets a reference to the Button object
        val button = findViewById<Button>(R.id.button)

        //Binds button to start the file Picker
        button.setOnClickListener {
            //val input = emptyArray<String>() //this will match nothing.
            //val input = arrayOf("text/plain") //if you want to filter .txt files
            val input = arrayOf("application/pdf")
            openPdfLauncher.launch(input)
        }

    }

}