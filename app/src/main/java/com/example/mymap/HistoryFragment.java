package com.example.mymap;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mymap.model.MarkerInfoModel;
import com.example.mymap.presenter.MarkerAdapter;
import com.example.mymap.presenter.MarkerInfo;
import java.util.List;


public class HistoryFragment extends Fragment implements MarkerAdapter.ClickListener{

    private static HistoryFragment hfInstance;
    private MarkerAdapter adapter;

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
        View viewInflater = inflater.inflate(R.layout.fragment_history, container, false);

        RecyclerView recyclerView = viewInflater.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewInflater.getContext(), LinearLayoutManager.VERTICAL,false));

        List<MarkerInfo> markerInfoList = new MarkerInfoModel().getMarkerInfoList(viewInflater.getContext());
        adapter = new MarkerAdapter(markerInfoList,viewInflater.getContext());
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        return viewInflater;
    }


    @Override
    public void onItemClick(int position, View view) {
        MarkerInfo markerInfo = new MarkerInfoModel().getMarker(view.getContext(), position + 1);
        Intent intent = new Intent(getActivity(), MarkerInfoHistActivity.class);
        intent.putExtra("Editing", markerInfo);
        startActivity(intent);

    }
}
