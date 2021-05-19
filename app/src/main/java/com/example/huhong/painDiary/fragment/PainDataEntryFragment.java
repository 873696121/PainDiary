package com.example.huhong.painDiary.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.huhong.R;
import com.example.huhong.databinding.FragmentPainDataEntryFragmentBinding;
import com.example.huhong.painDiary.room.PainRecordDatabase;
import com.example.huhong.painDiary.room.dao.PainRecordDao;
import com.example.huhong.painDiary.utils.DatetimeUtil;
import com.example.huhong.painDiary.room.entity.PainRecord;
import com.example.huhong.painDiary.room.entity.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 用户输入数据
public class PainDataEntryFragment extends Fragment {

    private FragmentPainDataEntryFragmentBinding addBinding;
    private PainRecordDatabase painRecordDatabase;
    private PainRecordDao painRecordDao;
    private PainRecord painRecord;
    private Record record;
    private String currentDay;
    private String username;
    private List<String> list = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    public PainDataEntryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        addBinding = FragmentPainDataEntryFragmentBinding.inflate(inflater, container, false);
        View view = addBinding.getRoot();

        getActivity().findViewById(R.id.radio);

        username = getActivity().getIntent().getStringExtra("username");
        painRecordDatabase = Room.databaseBuilder(getActivity(), PainRecordDatabase.class, "pain_record").allowMainThreadQueries().build();
        painRecordDao = painRecordDatabase.getPainRecordDao();
        painRecord = painRecordDao.getPainRecord(username);
        currentDay = DatetimeUtil.getNowDate();
        record = painRecord.getMap().get(currentDay);

        // 输入疼痛等级
        addBinding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                addBinding.textView.setText(String.valueOf(progress));
                record.setPainIntensityLevel(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // 输入疼痛部位
        list.add("back");
        list.add("neck");
        list.add("head");
        list.add("knees");
        list.add("hips");
        list.add("abdomen");
        list.add("elbows");
        list.add("shoulders");
        list.add("shins");
        list.add("jaw");
        list.add("facial");

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addBinding.spinner.setAdapter(adapter);
        addBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
                record.setPainLocation(adapter.getItem(position));
                record.setSpinnerPosition(position);
                Log.d("TAG", "onItemClick: " + adapter.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // 输入用户心情
        addBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = getActivity().findViewById(checkedId);
                if(radioButton == addBinding.radioButton1){
                    record.setMoodLevel(1);
                }
                else if(radioButton == addBinding.radioButton2){
                    record.setMoodLevel(2);
                }
                else if(radioButton == addBinding.radioButton3){
                    record.setMoodLevel(3);
                }
                else if(radioButton == addBinding.radioButton4){
                    record.setMoodLevel(4);
                }
                else{
                    record.setMoodLevel(5);
                }
            }
        });

        addBinding.entryEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlock();
            }
        });

        addBinding.entrySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lock();
                save();
            }
        });
        init();
        lock();
        return view;

    }

    // 将用户输入的数据保存到数据库
    void save(){
        record.setLastModified(new Date());
        record.setStepsTaken(Integer.valueOf(addBinding.editCurrentSteps.getText().toString()));
        record.setGoalStep(Integer.valueOf(addBinding.editGoal.getText().toString()));
        painRecord.getMap().put(currentDay, record);
        painRecordDao.updateRecords(painRecord);
    }


    // 当第一次进入到该页面时会查询数据库将数据显示在页面上，即还原用户上一次的操作， 此功能也可不做
    void init(){
        painRecord = painRecordDao.getPainRecord(username);
        Record record = painRecord.getMap().get(currentDay);
        if(record.getMoodLevel() != null){
            updateView();
        }
    }


    // 将数据库数据显示在页面上， 此功能也可不做
    void updateView(){
        painRecord = painRecordDao.getPainRecord(username);
        Record record = painRecord.getMap().get(currentDay);
        addBinding.seekBar.setProgress(record.getPainIntensityLevel());
        addBinding.spinner.setSelection(record.getSpinnerPosition());
        addBinding.editCurrentSteps.setText(String.valueOf(record.getStepsTaken()));
        Integer moodLevel = record.getMoodLevel();
        RadioButton radioButton;
        if(moodLevel == 1){
            radioButton = addBinding.radioButton1;
        }
        else if(moodLevel == 2){
            radioButton = addBinding.radioButton2;
        }
        else if(moodLevel == 3){
            radioButton = addBinding.radioButton3;
        }
        else if(moodLevel == 4){
            radioButton = addBinding.radioButton4;
        }
        else radioButton = addBinding.radioButton5;
        radioButton.setChecked(true);
        addBinding.editGoal.setText(String.valueOf(record.getGoalStep()));
        addBinding.editCurrentSteps.setText(String.valueOf(record.getStepsTaken()));
    }

    // 锁定所有输入项
    void lock(){
        addBinding.spinner.setEnabled(false);
        addBinding.editCurrentSteps.setEnabled(false);
        addBinding.seekBar.setEnabled(false);
        addBinding.editGoal.setEnabled(false);
        RadioGroup radioGroup = addBinding.radioGroup;
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(false);
        }
    }


    // 解锁所有输入项
    void unlock(){
        addBinding.spinner.setEnabled(true);
        addBinding.editCurrentSteps.setEnabled(true);
        addBinding.seekBar.setEnabled(true);
        addBinding.editGoal.setEnabled(true);
        RadioGroup radioGroup = addBinding.radioGroup;
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            radioGroup.getChildAt(i).setEnabled(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
    }
}
