package ar.com.damianfrutos.detectarconexioninternet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnTestearConexion: Button = findViewById(R.id.btnTestearConexion)
        btnTestearConexion.setOnClickListener {
            val status = internetCheck(this@MainActivity)
            Toast.makeText(this, "Status Internet = $status", Toast.LENGTH_LONG).show()
        }
    }
}

fun internetCheck(c: Context): Boolean {
    val cmg = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        // Android 10+
        cmg.getNetworkCapabilities(cmg.activeNetwork)?.let { networkCapabilities ->
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    } else {
        return cmg.activeNetworkInfo?.isConnectedOrConnecting == true
    }

    return false
}