package com.example.startappadapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.startapp.sdk.ads.splash.SplashConfig;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AutoInterstitialPreferences;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.StartAppSDK;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.VideoListener;

@SuppressLint("SetTextI18n")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this,"207649439",true);

        StartAppAd.showSplash(this, savedInstanceState,
                new SplashConfig()
                        .setTheme(SplashConfig.Theme.OCEAN)
                        .setAppName("TravelVlog")
                        .setLogo(R.drawable.ad)   // resource ID
                        .setOrientation(SplashConfig.Orientation.PORTRAIT)
        );
        StartAppAd.setAutoInterstitialPreferences(
                new AutoInterstitialPreferences()
                        .setSecondsBetweenAds(60)
        );
        setContentView(R.layout.activity_main);
    }
    public void btnOpenActivity (View view){
        Intent nextActivity = new Intent(this, NextActivity.class);
        startActivity(nextActivity);
        StartAppAd.showAd(this);
    }
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }
    public void showRewardedVideo() {
        final StartAppAd rewardedVideo = new StartAppAd(this);

        rewardedVideo.setVideoListener(new VideoListener() {
            @Override
            public void onVideoCompleted() {
                // Grant the reward to user
            }
        });

        rewardedVideo.loadAd(StartAppAd.AdMode.REWARDED_VIDEO, new AdEventListener() {
            @Override
            public void onReceiveAd(Ad ad) {
                rewardedVideo.showAd();
            }

            @Override
            public void onFailedToReceiveAd(Ad ad) {
                // Can't show rewarded video
            }
        });
    }

}