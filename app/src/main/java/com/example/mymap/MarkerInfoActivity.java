package com.example.mymap;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.mymap.databinding.ActivityMarkerInfoBinding;
import com.example.mymap.presenter.MarkerInfo;
import com.example.mymap.presenter.MarkerInfoActivityPresenter;


public class MarkerInfoActivity extends AppCompatActivity implements MarkerInfoActivityPresenter.UserMarkerActivity, SwipeRefreshLayout.OnRefreshListener {

    public static final String MARKER_LATITUDE = "latitude";
    public static final String MARKER_LONGITUDE = "longitude";

    private Double latitude;
    private Double longitude;

    MarkerInfo markInfo;
    MarkerInfoActivityPresenter markerInfoActivityPresenter;
    private SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);

        latitude = getIntent().getDoubleExtra(MARKER_LATITUDE, 0.0);
        longitude = getIntent().getDoubleExtra(MARKER_LONGITUDE, 0.0);

        markerInfoActivityPresenter = new MarkerInfoActivityPresenter(this);
        markerInfoActivityPresenter.attachView(this);
        markerInfoActivityPresenter.getMarkerInfo(latitude, longitude);
    }

    @Override
    public void getMarker(MarkerInfo markerInfo) {
        markInfo = markerInfo;
        ActivityMarkerInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_marker_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout = findViewById(R.id.swipe_container_marker);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_purple,
                android.R.color.holo_orange_light);

        binding.setMarkerinfo(markInfo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        markerInfoActivityPresenter.detachView();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                markerInfoActivityPresenter.getMarkerInfo(latitude, longitude);
                if (mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                Toast.makeText(MarkerInfoActivity.this, getResources().getString(R.string.downloaded), Toast.LENGTH_SHORT).show();

            }
        }, 4000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.share_provider);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, markInfo.toString());
        shareActionProvider.setShareIntent(intent);

        return super.onCreateOptionsMenu(menu);
    }


}
