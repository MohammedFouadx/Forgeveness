package sim.coder.forgeveness

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd

    private  var shouldLoadAds :Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)







        MobileAds.initialize(this,
            "ca-app-pub-5329195808649014~1776286378")


        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-5329195808649014/8901959748"
        mInterstitialAd.loadAd(AdRequest.Builder().build())


        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                if(shouldLoadAds == true){
                    mInterstitialAd.loadAd(AdRequest.Builder().build())
                }


            }
        }


        loadAds()


        readBook.setOnClickListener {
            val toBook = Intent(Intent(this, BookPage::class.java))
            startActivity(toBook)

            loadAds()
        }


    }

    fun loadAds(){
        if (mInterstitialAd.isLoaded){
            mInterstitialAd.show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        loadAds()
    }

    override fun onStop() {

        shouldLoadAds=false
        super.onStop()
    }
}
