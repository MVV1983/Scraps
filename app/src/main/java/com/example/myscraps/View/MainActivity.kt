package com.example.myscraps.View

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.myscraps.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupActionBarWithNavController(findNavController(R.id.fragment))
    }


    override fun onSupportNavigateUp(): Boolean {
        val navcontroller = findNavController(R.id.fragment)
        return navcontroller.navigateUp() || super.onSupportNavigateUp()
    }


    fun exitDialog() {
        AlertDialog.Builder(this,R.style.AlertDialogStyle).apply {
            setTitle("Please confirm.")
            setMessage("Are you want to exit the app?")

            setPositiveButton("Yes") { _, _ ->
                // if user press yes, then finish the current activity
                finishAffinity()
                finish()
            }

            setNegativeButton("No") { _, _ ->
                // if user press no, then return the activity
                Toast.makeText(
                    this@MainActivity, "Thank you!",
                    Toast.LENGTH_LONG
                ).show()
            }

            setCancelable(true)
        }.create().show()
    }

    override fun onBackPressed() {
        exitDialog()
    }
}
