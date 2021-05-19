package com.example.huhong.painDiary.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.huhong.R;
import com.example.huhong.databinding.FragmentDailyRecordBinding;

import com.example.huhong.painDiary.room.PainRecordDatabase;
import com.example.huhong.painDiary.room.dao.PainRecordDao;
import com.example.huhong.painDiary.room.entity.PainRecord;
import com.example.huhong.painDiary.room.entity.Record;
import com.example.huhong.painDiary.utils.MyAdapter;
import com.example.huhong.painDiary.viewmodel.SharedViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DailyRecordFragment extends Fragment {
    private FragmentDailyRecordBinding binding;
    private MyAdapter myAdapter;
    private PainRecordDatabase painRecordDatabase;
    private PainRecordDao painRecordDao;
    private PainRecord painRecord;
    private String username;

    public DailyRecordFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the View for this fragment using the binding
        binding = FragmentDailyRecordBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        SharedViewModel model = new
                ViewModelProvider(requireActivity()).get(SharedViewModel.class);



        RecyclerView recycleView = binding.recycleView;
        myAdapter = new MyAdapter();
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.setAdapter(myAdapter);
        HashMap<String, Record> map = painRecord.getMap();
        ArrayList<Record> records = new ArrayList<>();
        for (Map.Entry<String, Record> stringRecordEntry : map.entrySet()) {
            Record value = stringRecordEntry.getValue();
            records.add(value);
        }
        myAdapter.setList(records);
        myAdapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        painRecordDatabase = Room.databaseBuilder(getActivity(), PainRecordDatabase.class, "pain_record").allowMainThreadQueries().build();
        painRecordDao = painRecordDatabase.getPainRecordDao();
        username = getActivity().getIntent().getStringExtra("username");
        painRecord = painRecordDao.getPainRecord(username);


    }
}
