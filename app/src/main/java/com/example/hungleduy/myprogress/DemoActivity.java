package com.example.hungleduy.myprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import leduyhung.view.myprogress.loading.circle.LoadingCircleView;
import leduyhung.view.myprogress.loading.dot.LoadingDotView;
import leduyhung.view.myprogress.progress.circle.ProgressCircleView;

public class DemoActivity extends AppCompatActivity {

    private LoadingCircleView loadingCircleView;
    private LoadingDotView loadingDotView;
    private ProgressCircleView progressCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initLoadingCircle();
        initLoadingDotView();
        initProgressCircle();
    }

    private void initLoadingCircle() {

        loadingCircleView = findViewById(R.id.loading_circle);
        loadingCircleView.showLoading(true);
        loadingCircleView.setBorderSize(4);
        loadingCircleView.setRadius(60);
        loadingCircleView.setLoadingColor(getResources().getColor(R.color.colorPrimary));
        loadingCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingCircleView.showLoading(!loadingCircleView.isShow());
            }
        });
    }

    private void initLoadingDotView() {

        loadingDotView = findViewById(R.id.loading_dot);
        loadingDotView.showLoading(true);
        loadingDotView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingDotView.showLoading(!loadingDotView.isShow());
            }
        });
    }

    private void initProgressCircle() {

        progressCircleView = findViewById(R.id.progress_circle);
        progressCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressCircleView.setPercent(100);
            }
        });
    }
}