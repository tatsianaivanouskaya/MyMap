package com.example.mymap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mymap.databinding.ActivityMarkerInfoBinding;
import com.example.mymap.presenter.MarkerInfo;

public class MarkerInfoHistActivity extends AppCompatActivity {

    private MarkerInfo markerInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        markerInfo = (MarkerInfo)getIntent().getSerializableExtra("Editing");
        ActivityMarkerInfoBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_marker_info);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        binding.setMarkerinfo(markerInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.share_provider);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, markerInfo.toString());
        shareActionProvider.setShareIntent(intent);
        return super.onCreateOptionsMenu(menu);
    }

}
