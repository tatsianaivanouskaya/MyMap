package com.example.mymap.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.mymap.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private boolean isMapFragment = false;
    private boolean isHistoryFragment = false;
    private static final String MAP_FRAGMENT = "map_fragment";


    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_map:
                    loadFragment(MapFragment.newInstance());
                    isMapFragment = true;
                    isHistoryFragment = false;
                    return true;
                case R.id.nav_hist:
                    loadFragment(HistoryFragment.newInstance());
                    isHistoryFragment = true;
                    isMapFragment = false;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null){
            loadFragment(MapFragment.newInstance());
            isMapFragment = true;
        } else if(savedInstanceState.getBoolean(MAP_FRAGMENT) == true){
            loadFragment(MapFragment.newInstance());
            isMapFragment = true;
            isHistoryFragment = false;
        } else {
            loadFragment(HistoryFragment.newInstance());
            isHistoryFragment = true;
            isMapFragment = false;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(MAP_FRAGMENT, isMapFragment);
    }
}
