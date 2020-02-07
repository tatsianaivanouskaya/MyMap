package com.example.mymap.ui;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.mymap.R;
import com.example.mymap.databinding.ActivityMarkerInfoBinding;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.presenter.MarkerInfoActivityPresenter;
import com.example.mymap.viewmodel.MarkerInfoActivityViewModel;
import com.example.mymap.viewmodel.MarkerInfoActivityViewModelInterface;


public class MarkerInfoActivity extends AppCompatActivity /*implements
        SwipeRefreshLayout.OnRefreshListener,
        MarkerInfoActivityViewModelInterface*/ {

    public static final String MARKER_LATLONG = "latlong";
    public MarkerInfo markerInfoWeather;

    //private SwipeRefreshLayout mSwipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);

        String coordinates = getIntent().getStringExtra(MARKER_LATLONG);
        MarkerInfoActivityViewModel viewModel =
                ViewModelProviders.of(this).get(MarkerInfoActivityViewModel.class);
        Log.d("GSON", "2");
        LiveData<MarkerInfo> markerInfoLiveData = viewModel.getData(coordinates);

        /*viewModel.getData(coordinates).observe(this, new Observer<MarkerInfo>() {
            @Override
            public void onChanged(MarkerInfo markerInfo) {
                markerInfoWeather = markerInfo;
            }
        });*/
        /*markerInfoLiveData.observe(this, new Observer<MarkerInfo>() {
            @Override
            public void onChanged(MarkerInfo markerInfo) {
                markerInfoWeather = markerInfo;
            }
        });*/
        ActivityMarkerInfoBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_marker_info);
        binding.setMarkerinfo(markerInfoWeather);


      //binding.setViewmodel(viewModel);



    }

    /*@Override
    public void getMarker(MarkerInfo markerInfo) {
        markInfo = markerInfo;


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


    @Override
    public void getMarkerInfo(LiveData<MarkerInfo> markerInfoLiveData) {
        markerInfoLiveData.observe(this, new Observer<MarkerInfo>() {
            @Override
            public void onChanged(MarkerInfo markerInfo) {
                binding.setMarkerinfo(markerInfo);
            }
        });

    }*/
}
