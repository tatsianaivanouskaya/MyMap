package com.example.mymap.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.mymap.R;
import com.example.mymap.databinding.ActivityMarkerInfoBinding;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.viewmodel.MarkerInfoActivityViewModel;

public class MarkerInfoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    public static final String MARKER_LATLONG = "latlong";
    private MarkerInfoActivityViewModel viewModel;
    private ActivityMarkerInfoBinding binding;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private String coordinates;
    private MarkerInfo markInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);

        coordinates = getIntent().getStringExtra(MARKER_LATLONG);
        viewModel = ViewModelProviders.of(this).get(MarkerInfoActivityViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_marker_info);
        viewModel.getMarkerInfo(coordinates).observe(this, new Observer<MarkerInfo>() {
            @Override
            public void onChanged(MarkerInfo markerInfo) {
               binding.setMarkerinfo(markerInfo);
               markInfo = markerInfo;
               viewModel.setMarker(markInfo);
            }
        });

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
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewModel.getMarkerInfo(coordinates).observe(MarkerInfoActivity.this, new Observer<MarkerInfo>() {
                    @Override
                    public void onChanged(MarkerInfo markerInfo) {
                       binding.setMarkerinfo(markerInfo);
                       markInfo = markerInfo;
                    }
                });
                if (mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
                Toast.makeText(MarkerInfoActivity.this, getResources().getString(R.string.downloaded), Toast.LENGTH_SHORT).show();

            }
        }, 4000);
        viewModel.setMarker(markInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.share_provider);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, binding.toString());
        shareActionProvider.setShareIntent(intent);

        return super.onCreateOptionsMenu(menu);
    }
}
