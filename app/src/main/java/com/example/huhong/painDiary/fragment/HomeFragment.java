package com.example.huhong.painDiary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.huhong.databinding.HomeFragmentBinding;

import com.example.huhong.painDiary.retrofit.RetrofitUtil;
import com.example.huhong.painDiary.room.PainRecordDatabase;
import com.example.huhong.painDiary.room.dao.PainRecordDao;
import com.example.huhong.painDiary.utils.DatetimeUtil;
import com.example.huhong.painDiary.room.entity.PainRecord;
import com.example.huhong.painDiary.room.entity.Record;
import com.example.huhong.painDiary.room.entity.javaBeanTemp.Weather;
import com.example.huhong.painDiary.viewmodel.SharedViewModel;

import java.util.Date;

// 主页界面
public class HomeFragment extends Fragment {
    private SharedViewModel model;
    private HomeFragmentBinding addBinding;
    private PainRecordDatabase painRecordDatabase;
    private PainRecordDao painRecordDao;
    private String username;
    private SharedViewModel sharedViewModel;

    public HomeFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment
        addBinding = HomeFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();
        username = getActivity().getIntent().getStringExtra("username");
        sharedViewModel = ViewModelProviders.of(this).get(SharedViewModel.class);
        MutableLiveData<String> liveData = sharedViewModel.getPainRecordMutableLiveData();
        liveData.setValue(username);
        sharedViewModel.setPainRecordMutableLiveData(liveData);
        // 调用获取天气数据的接口
        Weather weather = new RetrofitUtil().getWeather();
        // 显示天气数据
        addBinding.currentWeather.setText(weather.toString());
        String username = getActivity().getIntent().getStringExtra("username");
        // 将天气数据存入数据库
        painRecordDatabase = Room.databaseBuilder(getActivity(), PainRecordDatabase.class, "pain_record").allowMainThreadQueries().build();
        painRecordDao = painRecordDatabase.getPainRecordDao();
        PainRecord painRecord = painRecordDao.getPainRecord(username);
        String currentDay = DatetimeUtil.getNowDate();
        if(!painRecord.getMap().containsKey(currentDay)){
            painRecord.getMap().put(currentDay, new Record(new Date()));
        }
        Record record = painRecord.getMap().get(currentDay);
        record.setLastModified(new Date());
        record.setWeather(weather);
        painRecord.getMap().put(currentDay, record);
        painRecordDao.updateRecords(painRecord);
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
