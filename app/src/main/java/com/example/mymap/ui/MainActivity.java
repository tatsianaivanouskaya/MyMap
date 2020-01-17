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
    private String fragment;
    private final String FRAGMENT_NAME = "fragment";

     BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.nav_map:
                    loadFragment(MapFragment.newInstance());
                    fragment = "map";
                    return true;
                case R.id.nav_hist:
                    loadFragment(HistoryFragment.newInstance());
                    fragment = "history";
                    return true;
            }

            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState != null){
            String fragmentName = savedInstanceState.getString(FRAGMENT_NAME);

            if(fragmentName.equals("map")){
                loadFragment(MapFragment.newInstance());
                fragment = "map";
            }else{
                loadFragment(HistoryFragment.newInstance());
                fragment = "history";
            }
        }else {
            loadFragment(MapFragment.newInstance());
            fragment = "map";
        }


        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(FRAGMENT_NAME, fragment);
    }
}
