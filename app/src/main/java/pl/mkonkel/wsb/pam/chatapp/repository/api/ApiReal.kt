package pl.mkonkel.wsb.pam.chatapp.repository.api

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber

class ApiImpl(context: Context) : Api {
    private val requestQueue by lazy {
        Volley.newRequestQueue(context)
    }

    override fun sendPush(
        fcmToken: String,
        title: String,
        message: String,
        callback: Api.Callback<Unit>
    ) {
        val body = notificationBody(fcmToken, title, message)
        val resultListener = Response.Listener<JSONObject> { response ->
            Timber.v(response.toString())

            val isSuccess = response.get(RESPONSE.KEY.SUCCESS) == "1"
            val isFailure = response.get(RESPONSE.KEY.FAIL) == "1"

            if (isSuccess) {
                callback.onSuccess(Unit)
            } else if (isFailure) {
                callback.onError(Throwable("FCM response was FAILURE"))
            }
        }

        val failListener = Response.ErrorListener { error ->
            Timber.e(error)
            callback.onError(error)
        }

        val request = object :
            JsonObjectRequest(API.SEND_URI, body, resultListener, failListener) {
            override fun getHeaders(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["Authorization"] = REQUEST.getKey()
                params["Content-Type"] = REQUEST.CONTENT_TYPE
                return params
            }
        }

        requestQueue.add(request)
    }

    private fun notificationBody(fcmToken: String, title: String, message: String): JSONObject {
        val notification = JSONObject()
        val notifcationBody = JSONObject()

        try {
            notifcationBody.put(REQUEST.KEY.TITLE, title)
            notifcationBody.put(REQUEST.KEY.MESSAGE, message)
            notification.put(REQUEST.KEY.TO, fcmToken)
            notification.put(REQUEST.KEY.DATA, notifcationBody)
        } catch (e: JSONException) {
            Timber.e(e)
        }

        Timber.v(notification.toString())
        return notification
    }


    private companion object {
        const val serviceKey =
            "AAAAOVOdMtc:APA91bHqNkYA7KD7xUq2fOuz2o5Z6qFXRiO6x4aJLsfcNrKgfNMp-5AOYY4QHaj8lhOOCs-y680RpUHTksQoAtqV1jVay_xjYcUl-sWJDUsVpv9bQdxb3n4kNFCS3lECwHJqRt3a0h4x"

        object REQUEST {
            const val CONTENT_TYPE = "application/json"
            fun getKey() = "key=$serviceKey"

            object KEY {
                const val TITLE = "title"
                const val MESSAGE = "message"
                const val TO = "to"
                const val DATA = "data"
            }
        }

        object RESPONSE {
            object KEY {
                const val SUCCESS = "success"
                const val FAIL = "failure"
            }
        }

        object API {
            const val SEND_URI = "https://fcm.googleapis.com/fcm/send"
        }
    }
}