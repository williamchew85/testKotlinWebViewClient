package com.example.testkotlinwebviewclient

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.webkit.CookieManager

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var editText: EditText
    private lateinit var evaluateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        editText = findViewById(R.id.editText)
        evaluateButton = findViewById(R.id.evaluateButton)

        // Enable JavaScript
        webView.settings.javaScriptEnabled = true
        webView.settings.userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36"

        //webView.settings.thirdPartyCookiesEnabled = true
        webView.settings.domStorageEnabled = true
        //webView.settings.injectedJavaScript: PropTypes.string,
        //webView.settings.scalesPageToFit: PropTypes.bool,
        //userAgent: PropTypes.string,
        //testID: PropTypes.string,
        //webView.settings.mediaPlaybackRequiresUserAction: PropTypes.bool,
        webView.settings.allowUniversalAccessFromFileURLs = true

        // Enable third-party cookies
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptThirdPartyCookies(webView, true)

        // WebViewClient to handle events
        webView.webViewClient = object : WebViewClient() {
            private var initialLoad = true

            override fun onPageFinished(view: WebView?, url: String?) {
                if (initialLoad) {
                    Toast.makeText(this@MainActivity, "Page loaded complete", Toast.LENGTH_SHORT).show()
                    executeJavaScript()
                    initialLoad = false  // Subsequent loads are likely redirects
                }
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                Toast.makeText(this@MainActivity, "Blocked redirection", Toast.LENGTH_SHORT).show()
                return !initialLoad  // Block all navigation after the initial load
            }
        }



        // Load the URL
        //webView.loadUrl("https://dropload.io/fcpbthw1gbq1.html")
        webView.loadUrl("https://dropload.io/6jjothdy2mdg.html")


        // Button click listener to evaluate JavaScript
        evaluateButton.setOnClickListener {
            webView.evaluateJavascript(editText.text.toString()) { result ->
                Toast.makeText(this@MainActivity, result, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun executeJavaScript() {
//        val script =
//            """(function() {
//                eval(function(p,a,c,k,e,d){while(c--)if(k[c])p=p.replace(new RegExp('\\b'+c.toString(a)+'\\b','g'),k[c]);return p}('1f("6s").6r({6q:{2o:"/6p/2s/2r-k.2s?v=3",w:"2r-k"},6o:[{2q:"u://6n.k.l/6m/1n/1m/6l/6k.6j?t=6i-6h-6g&s=29&e=6f&f=2a&6e=r&i=0.3&6d=0&6c=r&6b=r"}],6a:"1p%",69:"1p%",68:"67",66:"2p.11",65:\'64\',63:"13",a:[{2q:"/2c?2b=62&m=2p&2o=u://r.k.l/i/1n/1m/61.2l",5z:"5y"}],1y:{5x:1,2k:\'#5w\',5v:\'#5u\',5t:"5s",5r:30,5q:\'1p\',},"5p":{5o:"%5n 5m%5l%5k%5j%5i%5h.l%5g%2n%5f.k.l%5e%5d%5c%2n%22 5b%1o 5a%1o 59%1o 58%57 56%55 54%53 52%2m%51%50%2m",4z:"u://k.l/1i.28",4y:[]},\'4x\':{"4w":"4v"},4u:"4t",4s:"u://k.l/",4r:{},4q:13,1x:[1,1.25,1.5,2],4p:\'u://r.k.l/i/1n/1m/1i.2l\'}).g(\'1z\',8(){d p=2i.4o("15");p.4n="p";p.2j(\'4m\',\'4-1l 4-1l-4l 4-2h-2k 4-4k 4-1l-4j\');p.2j(\'4i\',\'6.1e(6.4h()+10)\');2i.4g(\'.4-2h-4f\').4e(p)});d 1j,1k;d 4d=0,4c=0;d 6=1f();d 2g=0,4b=0,4a=0,j=0;${'$'}.49({48:{\'47-46\':\'45-44\'}});6.g(\'43\',8(x){9(5>0&&x.q>=5&&1k!=1){1k=1;${'$'}(\'15.42\').41(\'40\')}9(x.q>=j+5||x.q<j){j=x.q;1h.3z(\'1g\',3y.3x(j),{3w:60*60*24*7})}});6.g(\'1e\',8(x){2g=x.q});6.g(\'3v\',8(x){2f(x)});6.g(\'3u\',8(){${'$'}(\'15.2e\').3t();1h.3s(\'1g\')});6.g(\'3r\',8(x){});8 2f(x){${'$'}(\'15.2e\').2d();${'$'}(\'#3q\').2d();9(1j)17;1j=1;14=0;9(3p.3o===3n){14=1}${'$'}.26(\'/2c?2b=3m&3l=1i&3k=2a-3j-3i-29-3h&3g=&14=\'+14,8(27){${'$'}(\'#3f\').28(27)});d j=1h.26(\'1g\');9(j>0){1f().1e(j)}}8 3e(){d a=6.18(23);21.20(a);9(a.m>1){1s(i=0;i<a.m;i++){9(a[i].w==23){21.20(\'!!=\'+i);6.1q(i)}}}}6.g(\'1z\',8(){});6.g("h",8(y){d a=6.18();9(a.m<2)17;${'$'}(\'.4-c-3d-3c\').3b(8(){${'$'}(\'#4-c-b-h\').1b(\'4-c-b-z\');${'$'}(\'.4-b-h\').o(\'n-1c\',\'12\')});6.3a("/39/38.37","36 35",8(){${'$'}(\'.4-1w\').34(\'4-c-1v\');${'$'}(\'.4-c-1y, .4-c-1x\').o(\'n-1d\',\'12\');9(${'$'}(\'.4-1w\').33(\'4-c-1v\')){${'$'}(\'.4-b-h\').o(\'n-1d\',\'13\');${'$'}(\'.4-b-h\').o(\'n-1c\',\'13\');${'$'}(\'.4-c-b-32\').1b(\'4-c-b-z\');${'$'}(\'.4-c-b-h\').31(\'4-c-b-z\')}2z{${'$'}(\'.4-b-h\').o(\'n-1d\',\'12\');${'$'}(\'.4-b-h\').o(\'n-1c\',\'12\');${'$'}(\'.4-c-b-h\').1b(\'4-c-b-z\')}},"2y");6.g("2x",8(y){1a.2w(\'19\',y.a[y.2v].w)});9(1a.1u(\'19\')){2u("1t(1a.1u(\'19\'));",2t)}});d 16;8 1t(1r){d a=6.18();9(a.m>1){1s(i=0;i<a.m;i++){9(a[i].w==1r){9(i==16){17}16=i;6.1q(i)}}}}',36,245,'||||jw||player||function|if|tracks|submenu|settings|var|||on|audioTracks||lastt|dropload|io|length|aria|attr|newButton|position|srv07|||https||name||event|active|||false|true|adb|div|current_audio|return|getAudioTracks|default_audio|localStorage|removeClass|expanded|checked|seek|jwplayer|ttfcpbthw1gbq1|ls|fcpbthw1gbq1|vvplay|vvad|icon|00106|01|3D0|100|setCurrentAudioTrack|audio_name|for|audio_set|getItem|open|controls|playbackRates|captions|ready|log|console||track_name|||get|data|html|1715522609|534460|op|dl|hide|video_ad|doPlay|prevt|button|document|setAttribute|color|jpg|3E|2Ffcpbthw1gbq1|url|9533|file|jw8|css|300|setTimeout|currentTrack|setItem|audioTrackChanged|dualSound|else||addClass|quality|hasClass|toggleClass|Track|Audio|svg|dualy|images|addButton|mousedown|buttons|topbar|set_audio_track|fviews|embed|57a6b146449f876c3ffad80d22db5443|156|175|hash|file_code|view|undefined|cRAds|window|over_player_msg|pause|remove|show|complete|play|ttl|round|Math|set|slow|fadeIn|video_ad_fadein|time|cache|no|Cache|Content|headers|ajaxSetup|v2done|tott|vastdone2|vastdone1|appendChild|container|querySelector|getPosition|onclick|forward|reset|inline|class|id|createElement|image|playbackRateControls|cast|aboutlink|DropLoad|abouttext|HD|1252|qualityLabels|sites|link|2FIFRAME|3C|allowfullscreen|3D360|HEIGHT|3D640|WIDTH|3DNO|SCROLLING|MARGINHEIGHT|MARGINWIDTH|FRAMEBORDER|2F00106|2F01|2Fi|3Fsrv07|2Fe|2Fdropload|2F|3A|22https|3D|SRC|3CIFRAME|code|sharing|fontOpacity|backgroundOpacity|Tahoma|fontFamily|303030|backgroundColor|FFFFFF|userFontScale|thumbnails|kind||fcpbthw1gbq10000|get_slides|androidhls|metadata|preload|duration|uniform|stretching|height|width|p2|p1|sp|srv|21600|lYeJRqqV73gU|awItSpCRLVpjK|PepAgfepf_FAFPru|m3u8|master|fcpbthw1gbq1_h|hls2|srv14|sources|assets2|skin|setup|vplayer'.split('|')))
//                })();
//                """
//        webView.evaluateJavascript(script) { result ->
//            Toast.makeText(this@MainActivity, "JavaScript Evaluated", Toast.LENGTH_SHORT).show()
//        }

        webView.postDelayed({
            webView.evaluateJavascript(
                """
                        (function() {
                            // Wait for the JWPlayer instance to be ready
                            var attemptCount = 0;
                            function checkJWPlayerReady() {
                                attemptCount++;
                                if (window.jwplayer && jwplayer('vplayer') && jwplayer('vplayer').hls) {
                                    return jwplayer('vplayer').hls.url;
                                } else if (attemptCount < 20) {  // Try up to 20 times
                                    setTimeout(checkJWPlayerReady, 500);  // Check every 500ms
                                } else {
                                    return null;  // Return null after all attempts
                                }
                            }
                            return checkJWPlayerReady();
                        })();
                        """
            ) { jwPlayerUrl ->
                if (jwPlayerUrl != null && jwPlayerUrl != "null") {
                    Toast.makeText(this@MainActivity, "URL:" + jwPlayerUrl, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@MainActivity, "URL NOT FOUND", Toast.LENGTH_SHORT).show()
                }
                //webView.destroy()  // Ensure the WebView is destroyed after retrieval
            }
        }, 1000)  // Initial delay of 1 second before starting the script evaluation
    }
}