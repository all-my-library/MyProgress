# VERSION JAR
Link file JAR: <a href="https://github.com/all-my-library/MyProgress/tree/master/library/myjar_v1" title="">ProgressLibrary v1</a>
# HOW TO USE
<h3>LoadingCircleView</h3>
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
<pre>private void initLoadingCircle() {

        loadingCircleView = findViewById(R.id.loading_circle);
        loadingCircleView.showLoading(true);
        loadingCircleView.setBorderSize(4);
        loadingCircleView.setRadius(60);
        loadingCircleView.setLoadingColor(getResources().getColor(R.color.colorPrimary));
}
</pre>
