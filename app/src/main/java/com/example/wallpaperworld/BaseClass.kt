package com.example.wallpaperworld

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.*
import androidx.multidex.MultiDexApplication
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseClass : MultiDexApplication() , Application.ActivityLifecycleCallbacks, LifecycleObserver{
    private lateinit var appOpenAdManager: AppOpenAdManager
    private var currentActivity: Activity? = null


    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
        MobileAds.initialize(this
        ) { }
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
            appOpenAdManager = AppOpenAdManager()
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        // Show the ad (if available) when the app moves to foreground.
        currentActivity?.let {
            showAdIfAvailable(it)
        }
    }

    /** Show the ad if one isn't already showing. */
    fun showAdIfAvailable(activity: Activity) {
        appOpenAdManager.showAdIfAvailable(
            activity,
            object : AppOpenAdManager.OnShowAdCompleteListener {
                override fun onShowAdComplete() {
                    // Empty because the user will go back to the activity that shows the ad.
                }
            })
    }



    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
       
    }

    override fun onActivityStarted(activity: Activity) {
        // Updating the currentActivity only when an ad is not showing.
        if (!appOpenAdManager.isShowingAd) {
            currentActivity = activity
        }
    }

    override fun onActivityResumed(activity: Activity) {
       
    }

    override fun onActivityPaused(activity: Activity) {
       
    }

    override fun onActivityStopped(activity: Activity) {
       
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
       
    }

    override fun onActivityDestroyed(activity: Activity) {
       
    }
}