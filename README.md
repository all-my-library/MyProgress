# COMPILE
compile 'leduyhung.my.view:my-progress:0.0.3'
# HOW TO USE
<h3>LoadingCircleView</h3>
<img src="https://github.com/all-my-library/MyProgress/blob/master/art/loading_circle.gif"></a>
<p><b>1: Declare in xml</b></p>
<pre> com.leduyhung.progresslibrary.loading.circle.LoadingCircleView
        android:id="@+id/loading_circle"
        android:layout_width="wrap_content" // don't care
        android:layout_height="wrap_content" // don't care
        custom:loading_circle_radius="20dp" // size of circle
        custom:loading_circle_border_size="2dp" // size of border loading
        custom:loading_circle_border_color="@color/colorAccent" // color border
</pre>
<br/>
<p><b>2: Call to show loading circle view</b></p>
<pre>
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
</pre>
<br/>
<h3>LoadingDotView</h3>
<img src="https://github.com/all-my-library/MyProgress/blob/master/art/loading_dot.gif" style="max-width:100%"></a>
<p><b>1: Declare in xml</b></p>
<pre>
leduyhung.view.myprogress.loading.dot.LoadingDotView
        android:id="@+id/loading_dot"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        custom:loading_dot_radius="7dp"
        custom:loading_dot_distance="7dp"
custom:loading_dot_color="@color/colorAccent"
</pre>
<br/>
<p><b>2: Call to show loading dot view</b></p>
<pre>
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
</pre>
<h3>ProgressView</h3>
<img src="https://github.com/all-my-library/MyProgress/blob/master/art/progress_view.gif"></a>
<img src="https://github.com/all-my-library/MyProgress/blob/master/art/progress_view_no_title.gif"></a>
<p><b>1: Declare in xml</b></p>

<p><b>Progress with title</b></p>
<pre> 
com.leduyhung.progresslibrary.progress.circle.ProgressCircleView
        android:id="@+id/progress_circle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:progress_circle_percent="90"
        custom:progress_circle_radius="60dp"
        custom:progress_circle_size_progress="4dp"
        custom:progress_circle_color_background="@color/transparent"
        custom:progress_circle_color_progress="@color/colorAccent"
        custom:progress_circle_color_background_progress="@color/blue"
        custom:progress_circle_color_value="@color/blue"
        custom:progress_circle_color_name="@color/blue"
        custom:progress_circle_text_name="PROGRESS"
        custom:progress_circle_has_name="true"
        custom:progress_circle_name_size="12dp"
        custom:progress_circle_value_size="40dp"
</pre>

<p><b>Progress no title</b></p>
<pre id = 1> 
com.leduyhung.progresslibrary.progress.circle.ProgressCircleView
        android:id="@+id/progress_circle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:progress_circle_percent="0"
        custom:progress_circle_radius="60dp"
        custom:progress_circle_size_progress="4dp"
        custom:progress_circle_color_background="@color/transparent"
        custom:progress_circle_color_progress="@color/colorAccent"
        custom:progress_circle_color_background_progress="@color/blue"
        custom:progress_circle_color_value="@color/blue"
        custom:progress_circle_has_name="false"
        custom:progress_circle_value_size="40dp"
</pre>
<p><b>2: Call to set percent</b></p>
<pre> 
private void initProgressCircle() {

        progressCircleView = findViewById(R.id.progress_circle);
        progressCircleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressCircleView.setPercent(20);
            }
        });
    }
</pre>
