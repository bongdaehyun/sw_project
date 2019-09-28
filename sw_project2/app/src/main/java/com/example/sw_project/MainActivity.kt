package com.example.sw_project

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.net.CookieHandler
import java.net.CookieManager

class MainActivity : AppCompatActivity() {

    companion object{
        const val QUEUE_TAG = "VolleyRequest"
    }

    lateinit var mQueue: RequestQueue
    var mResult: JSONObject? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CookieHandler.setDefault(CookieManager())

        mQueue = Volley.newRequestQueue(this) //초기화

        requestText()
    }

    override fun onStop() {
        super.onStop()
        mQueue.cancelAll(QUEUE_TAG)
    }
    fun requestText()
    {
        val url = "http://202.31.200.140/test.php"

        val requestVal = JsonObjectRequest(Request.Method.GET,
            url, null,
            Response.Listener {
                response -> textView.text =response.toString()
            },
            Response.ErrorListener {
                error ->  Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            }
        )
        //변수만 만들어논다고 실행되는 것이 아님!
        requestVal.tag = QUEUE_TAG
        mQueue.add(requestVal)
    }
}
