package com.example.mymap.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.mymap.data.MarkerInfo;
import com.example.mymap.model.HistoryFragmentRepository;
import java.util.List;

public class HistoryFragmentViewModel extends AndroidViewModel {

    private LiveData<List<MarkerInfo>> listLiveData;
    private HistoryFragmentRepository repository;

    public HistoryFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<MarkerInfo>> getListMutableLiveData() {
        if (listLiveData == null){
            repository = HistoryFragmentRepository.getInstance();
            listLiveData = repository.getMarkerInfoList(getApplication().getApplicationContext());
        }
        return listLiveData;
    }


}
