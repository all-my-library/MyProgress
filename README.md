# VERSION JAR
Link file JAR: <a href="https://github.com/all-my-library/MyProgress/tree/master/library/myjar_v1" title="">ProgressLibrary v1</a>
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
<p><b>1: Declare in xml</b></p>
<pre>
com.leduyhung.progresslibrary.loading.dot.LoadingDotView
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
