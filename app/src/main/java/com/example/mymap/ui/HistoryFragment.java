package com.example.mymap.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.mymap.R;
import com.example.mymap.model.HistoryFragmentRepository;
import com.example.mymap.presenter.MarkerAdapter;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.viewmodel.HistoryFragmentViewModel;
import java.util.List;

public class HistoryFragment extends Fragment implements MarkerAdapter.ClickListener, SwipeRefreshLayout.OnRefreshListener{

    private static HistoryFragment hfInstance;
    private MarkerAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private View viewInflater;
    private RecyclerView recyclerView;
    private List<MarkerInfo> markerInfoList;
    private HistoryFragmentViewModel viewModel;
    private MarkerInfo markInfo;

    public HistoryFragment() {}

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
        viewModel = ViewModelProviders.of(this).get(HistoryFragmentViewModel.class);
        viewModel.getListMutableLiveData().observe(this, new Observer<List<MarkerInfo>>() {
            @Override
            public void onChanged(List<MarkerInfo> markerInfos) {
                markerInfoList = markerInfos;
                adapter = new MarkerAdapter(markerInfoList,viewInflater.getContext());
                adapter.setOnItemClickListener(HistoryFragment.this);
                recyclerView.setAdapter(adapter);
            }
        });
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
        markInfo = HistoryFragmentRepository.getInstance().getMarker(viewInflater.getContext(), position + 1);
        Intent intent = new Intent(getActivity(), MarkerInfoHistActivity.class);
        intent.putExtra("Editing", markInfo);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                viewModel.getListMutableLiveData().observe(HistoryFragment.this, new Observer<List<MarkerInfo>>() {
                    @Override
                    public void onChanged(List<MarkerInfo> markerInfos) {
                        markerInfoList = markerInfos;
                    }
                });
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
