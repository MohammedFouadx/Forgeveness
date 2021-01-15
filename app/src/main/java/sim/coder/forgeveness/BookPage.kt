package sim.coder.forgeveness

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_book_page.*

class BookPage : AppCompatActivity() {

    private lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_page)


        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        val shared=getSharedPreferences("forgive", Context.MODE_PRIVATE)

        val edit= shared.edit()
        val number =shared.getInt("numPage",0)




        pdf.fromAsset("forgiveness.pdf")
            .defaultPage(number)
            .password(null)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableDoubletap(true)
            .enableAnnotationRendering(true)
            .onPageChange { page, pageCount ->
                edit.putInt("numPage",page)
                edit.commit()
            }
            .load()


    }
}

