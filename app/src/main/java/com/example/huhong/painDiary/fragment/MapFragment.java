package com.example.huhong.painDiary.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.huhong.R;
import com.example.huhong.databinding.FragmentDailyRecordBinding;
import com.example.huhong.databinding.FragmentMapBinding;

// 地图功能，找示例代码修改

public class MapFragment extends Fragment {
    private FragmentMapBinding binding;
    private MapView mMapView = null;
    private GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
    private double latitude;
    private double longitude;
    private String address;
    private String provinceName;
    private BaiduMap mBaiduMap;


    void setPosition(){
        LatLng cenpt = new LatLng(latitude, longitude);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMapBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.fragment_map);
        //获取地图控件引用
        mMapView = (MapView) getActivity().findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    //没有检索到结果
                    Log.d("TAG", "onGetGeoCodeResult: 抱歉，未能找到结果");
                    return;
                }
                //获取地理编码结果
                latitude = geoCodeResult.getLocation().latitude;
                longitude = geoCodeResult.getLocation().longitude;
                Log.e("地址转经纬度", "latitude：" + latitude + "====longitude:" + longitude);
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

            }
        });

        Button finish = getActivity().findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = getActivity().findViewById(R.id.address);
                address = editText.getText().toString();
//                address = "江苏省扬州中学";
                provinceName = address.substring(0, 3);
                if (!TextUtils.isEmpty(provinceName)) {
                    mSearch.geocode(new GeoCodeOption()
                            .city(provinceName)
                            .address(address));
                }
            }
        });

        Button locate = getActivity().findViewById(R.id.locate);
        locate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                String lon = String.format("%.2f", longitude);
                String lat = String.format("%.2f", latitude);
                TextView textView = getActivity().findViewById(R.id.longitude);
                textView.setText("longitude: " + lon);
                TextView textView1 = getActivity().findViewById(R.id.latitude);
                textView1.setText("latitude: " + lat);
                setPosition();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时必须调用mMapView. onResume ()
        mMapView.onResume();
    }
    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时必须调用mMapView. onPause ()
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时必须调用mMapView.onDestroy()
        mMapView.onDestroy();
    }
}
