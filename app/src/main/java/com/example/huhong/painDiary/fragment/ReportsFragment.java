package com.example.huhong.painDiary.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.huhong.R;
import com.example.huhong.databinding.FragmentReportsBinding;
import com.example.huhong.painDiary.room.PainRecordDatabase;
import com.example.huhong.painDiary.room.dao.PainRecordDao;
import com.example.huhong.painDiary.utils.DatetimeUtil;
import com.example.huhong.painDiary.room.entity.PainRecord;
import com.example.huhong.painDiary.room.entity.Record;
import com.example.huhong.painDiary.utils.PieChartManagger;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// 饼图，找示例代码修改数据
public class ReportsFragment extends Fragment {

    private FragmentReportsBinding binding;
    private PieChart pieChart,pie_chat2;
    private PainRecordDatabase painRecordDatabase;
    private PainRecordDao painRecordDao;
    private PainRecord painRecord;
    private String username;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        painRecordDatabase = Room.databaseBuilder(getActivity(), PainRecordDatabase.class, "pain_record").allowMainThreadQueries().build();
        painRecordDao = painRecordDatabase.getPainRecordDao();
        username = getActivity().getIntent().getStringExtra("username");
        painRecord = painRecordDao.getPainRecord(username);

        getActivity().setContentView(R.layout.fragment_reports);

        initView();

    }

    private void initView() {
        pieChart = (PieChart) getActivity().findViewById(R.id.pie_chat1);
        pie_chat2= (PieChart) getActivity().findViewById(R.id.pie_chat2);
        showhodlePieChart();

        showRingPieChart();
    }

    private void showRingPieChart() {

//设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        Record record = painRecord.getMap().get(DatetimeUtil.getNowDate());
        yvals.add(new PieEntry(record.getStepsTaken(), "StepsTaken"));
        yvals.add(new PieEntry(record.getGoalStep() - record.getStepsTaken(), "StepsLeft"));
//        yvals.add(new PieEntry(4.0f, "博士"));
//        yvals.add(new PieEntry(5.0f, "大专"));
//        yvals.add(new PieEntry(1.0f, "其他"));
// 设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#6785f2"));
        colors.add(Color.parseColor("#675cf2"));
        colors.add(Color.parseColor("#496cef"));
        colors.add(Color.parseColor("#aa63fa"));
        colors.add(Color.parseColor("#f5a658"));
        PieChartManagger pieChartManagger=new PieChartManagger(pie_chat2);
        pieChartManagger.showRingPieChart(yvals,colors);
    }

    private void showhodlePieChart() {
        // 设置每份所占数量
        List<PieEntry> yvals = new ArrayList<>();
        HashMap<String, Integer> cnt = new HashMap<>();
        HashMap<String, Record> map = painRecord.getMap();
        for (Map.Entry<String, Record> stringRecordEntry : map.entrySet()) {
            String painLocation = stringRecordEntry.getValue().getPainLocation();
            if(!cnt.containsKey(painLocation)) cnt.put(painLocation, 1);
            else cnt.put(painLocation, cnt.get(painLocation) + 1);
        }

        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            if(entry.getKey() != null) {
                yvals.add(new PieEntry(entry.getValue(), entry.getKey()));
            }
        }

//        yvals.add(new PieEntry(2.0f, "汉族"));
//        yvals.add(new PieEntry(3.0f, "回族"));
//        yvals.add(new PieEntry(4.0f, "壮族"));
//        yvals.add(new PieEntry(5.0f, "维吾尔族"));
//        yvals.add(new PieEntry(6.0f, "土家族"));
        //设置每份的颜色
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#6785f2"));
        colors.add(Color.parseColor("#675cf2"));
        colors.add(Color.parseColor("#496cef"));
        colors.add(Color.parseColor("#aa63fa"));
        colors.add(Color.parseColor("#58a9f5"));
        colors.add(Color.parseColor("#58a9f5"));
        colors.add(Color.parseColor("#58a9f5"));

        PieChartManagger pieChartManagger=new PieChartManagger(pieChart);
        pieChartManagger.showSolidPieChart(yvals,colors);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReportsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}