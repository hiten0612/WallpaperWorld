package com.example.wallpaperworld.ui.activities

import android.content.Intent
import android.media.tv.AdRequest
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.example.wallpaperworld.listeners.Listeners
import com.example.wallpaperworld.adapters.WallPaperListAdapter
import com.example.wallpaperworld.databinding.ActivityWallPaperBinding
import com.example.wallpaperworld.models.Photo
import com.example.wallpaperworld.viewmodels.WallPaperViewModel
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WallPaperActivity : AppCompatActivity(), Listeners {

    private lateinit var binding: ActivityWallPaperBinding
    private var mAdapter: WallPaperListAdapter? = null
    private var wList = ArrayList<Photo>()

    private var mInterstitialAd: InterstitialAd? = null
    private val viewModel by viewModels<WallPaperViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityWallPaperBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this

        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
            Animatoo.animateSlideRight(this);

        }
        val imageUrl = intent.extras?.getString("catName")
        viewModel.getImages("563492ad6f91700001000001518da21d798f4f4b86f3b3ebb2e72ebb", imageUrl!!)

        val adRequest= com.google.android.gms.ads.AdRequest.Builder().build()
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712",adRequest, object :InterstitialAdLoadCallback(){

            override fun onAdFailedToLoad(adError: LoadAdError) {
                super.onAdFailedToLoad(adError)
                Log.e("TAG", adError.toString())
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                super.onAdLoaded(interstitialAd)
                Log.e("TAG", "Ad was loaded.")
                mInterstitialAd = interstitialAd
            }
        })



        setObserver()

    }


    override fun onBackPressed() {
        finish()
        Animatoo.animateSlideRight(this)
    }

    override fun onItemClick(position: Int) {

        if (mInterstitialAd != null) {
            mInterstitialAd?.show(this)
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.")
            val passImage = wList[position].src.original
            val intent = Intent(this@WallPaperActivity, FullScreenActivity::class.java)
            intent.putExtra("wallImage", passImage)
            startActivity(intent)
            Animatoo.animateSlideLeft(this@WallPaperActivity);
        }
        mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d("TAG", "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d("TAG", "Ad dismissed fullscreen content.")
                mInterstitialAd = null
                val passImage = wList[position].src.original
                val intent = Intent(this@WallPaperActivity, FullScreenActivity::class.java)
                intent.putExtra("wallImage", passImage)
                startActivity(intent)
                Animatoo.animateSlideLeft(this@WallPaperActivity);
            }

            override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                // Called when ad fails to show.
                Log.e("TAG", "Ad failed to show fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d("TAG", "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d("TAG", "Ad showed fullscreen content.")
            }
        }

    }

    private fun setObserver() {
        viewModel.response.observe(this) {
            wList = it?.data?.photos as ArrayList<Photo>
            mAdapter?.addList(it.data.photos as ArrayList<Photo>)
            mAdapter = WallPaperListAdapter(it.data.photos as ArrayList<Photo>, this)
            binding.rvWallpaper.adapter = mAdapter

        }
    }

}