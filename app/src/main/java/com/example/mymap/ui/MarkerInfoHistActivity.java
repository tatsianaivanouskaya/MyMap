package com.example.mymap.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.mymap.R;
import com.example.mymap.databinding.ActivityMarkerInfoBinding;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.viewmodel.MarkerInfoHistActivityViewModel;

public class MarkerInfoHistActivity extends AppCompatActivity {

    private ActivityMarkerInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getIntent().getIntExtra("Editing", 0);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_marker_info);
        MarkerInfoHistActivityViewModel viewModel = ViewModelProviders.of(this).get(MarkerInfoHistActivityViewModel.class);
        viewModel.getMarkerInfoMutableLiveData(position).observe(this, new Observer<MarkerInfo>() {
            @Override
            public void onChanged(MarkerInfo markerInfo) {
                binding.setMarkerinfo(markerInfo);
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

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
