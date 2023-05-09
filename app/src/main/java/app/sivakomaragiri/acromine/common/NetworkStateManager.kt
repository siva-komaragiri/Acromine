package app.sivakomaragiri.acromine.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkStateManager(private val context: Context) {

    val isConnected: Boolean
        get() {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork
            if (network != null) {
                val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
                if (networkCapabilities != null) {
                    if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true
                    }
                }
            } else {
                return false
            }
            return false
        }
}