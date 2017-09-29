package com.example.hungleduy.myprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leduyhung.progresslibrary.loading.circle.LoadingCircleView;

public class DemoActivity extends AppCompatActivity {

    private LoadingCircleView loadingCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initLoadingCircle();
    }

    private void initLoadingCircle() {

        loadingCircleView = findViewById(R.id.loading_circle);
        loadingCircleView.showLoading(true);
    }
}