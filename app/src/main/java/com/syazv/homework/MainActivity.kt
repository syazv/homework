package com.syazv.homework

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.syazv.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.sendBtn.setOnClickListener {
            Snackbar.make(
                findViewById(R.id.sendBtn),
                "Your email have been send successfully", Snackbar.LENGTH_LONG).show()
        }

        binding.saveBtn.setOnClickListener {
            val saveAlert = AlertDialog.Builder(this)
            saveAlert.setTitle("Save")
            saveAlert.setMessage("Are you sure you want to save your changes?")
            saveAlert.setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
                Snackbar.make(findViewById(R.id.saveBtn), "Saved", Snackbar.LENGTH_LONG).show()
            }
            saveAlert.show()

        binding.notificationBtn.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create the Notification Channel
                val channel_id = "channel_01"
                val channel_Name = "notification"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val mChannel = NotificationChannel(channel_id, channel_Name, importance)
                mChannel.description = "test description"
                mChannel.enableLights(true)
                mChannel.lightColor = Color.RED
                mChannel.enableVibration(true)

                // Use Notification.Builder to add the notification objects
                val notification: Notification = Notification.Builder(this, channel_id)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Android ATC Notification")
                    .setContentText("Check Android ATC New Course !!")
                    .build()

                // Register or add the channel with your Android system
                val mNotificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager

                if (mNotificationManager != null) {
                    mNotificationManager.createNotificationChannel(mChannel)
                    //Show the notification
                    mNotificationManager.notify(1, notification)
                }

            }

        }



            val myBrowser: WebView = findViewById(R.id.myWeb)
            myBrowser.webViewClient = WebViewClient()

            //Shows the URLmyBrowser.loadUrl("https://www.androidatc.com")
            // Set the Web View to have a transparent border
            myBrowser.setBackgroundColor(Color.TRANSPARENT)

            //To enable JavaScript for the web browser
            myBrowser.settings.javaScriptEnabled = true

            //to load images automatically
            myBrowser.settings.loadsImagesAutomatically = true

            //Enable Scrolling
            myBrowser.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY

        }
    }
}
