package com.example.mymap.ui;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymap.R;
import com.example.mymap.model.MarkerInfoModel;
import com.example.mymap.presenter.MarkerAdapter;
import com.example.mymap.data.MarkerInfo;

import java.util.List;


public class HistoryFragment extends Fragment implements MarkerAdapter.ClickListener, SwipeRefreshLayout.OnRefreshListener{

    private static HistoryFragment hfInstance;
    private MarkerAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View viewInflater;
    private RecyclerView recyclerView;
    private List<MarkerInfo> markerInfoList;

    public HistoryFragment() {

    }
    static HistoryFragment newInstance(){
        if (hfInstance == null){
            hfInstance = new HistoryFragment();
        }
        return hfInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewInflater = inflater.inflate(R.layout.fragment_history, container, false);


        recyclerView = viewInflater.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewInflater.getContext(), LinearLayoutManager.VERTICAL,false));

        markerInfoList = new MarkerInfoModel().getMarkerInfoList(viewInflater.getContext());
        adapter = new MarkerAdapter(markerInfoList,viewInflater.getContext());
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout = viewInflater.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_purple,
                android.R.color.holo_orange_light);

        return viewInflater;
    }


    @Override
    public void onItemClick(int position, View view) {
        MarkerInfo markerInfo = new MarkerInfoModel().getMarker(view.getContext(), position + 1);
        Intent intent = new Intent(getActivity(), MarkerInfoHistActivity.class);
        intent.putExtra("Editing", markerInfo);
        startActivity(intent);

    }

    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                
                markerInfoList = new MarkerInfoModel().getMarkerInfoList(viewInflater.getContext());
                adapter = new MarkerAdapter(markerInfoList,viewInflater.getContext());
                recyclerView.setAdapter(adapter);

                if (mSwipeRefreshLayout.isRefreshing()){
                    mSwipeRefreshLayout.setRefreshing(false);
                }


                Toast.makeText(viewInflater.getContext(), getResources().getString(R.string.downloaded), Toast.LENGTH_SHORT).show();

            }
        }, 4000);

    }

}
