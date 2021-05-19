package com.example.huhong.painDiary.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.huhong.painDiary.room.entity.PainRecord;


public class SharedViewModel extends ViewModel {
    private MutableLiveData<String> painRecordMutableLiveData;

    public MutableLiveData<String> getPainRecordMutableLiveData() {
        if(painRecordMutableLiveData == null) {
            painRecordMutableLiveData = new MutableLiveData<>();
        }
        return painRecordMutableLiveData;
    }

    public void setPainRecordMutableLiveData(MutableLiveData<String> painRecordMutableLiveData) {
        this.painRecordMutableLiveData = painRecordMutableLiveData;
    }
}
