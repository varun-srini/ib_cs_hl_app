package com.example.ibcshlapplication.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Please input the severity of hallway congestion (1 is lowest, 5 is highest)");
    }

    public LiveData<String> getText() {
        return mText;
    }
}