package com.allsociety.mobilkiwsb.view;

import android.os.Bundle;
import android.widget.Toast;

import com.allsociety.mobilkiwsb.base.DraverBaseActivity;
import com.allsociety.mobilkiwsb.databinding.ActivityPromotionBinding;
import com.allsociety.mobilkiwsb.view.listadapter.PromotionUILIstAdapter;
import com.allsociety.mobilkiwsb.viewmodel.PromotionViewModel;

import java.util.List;

import API.ResponseObject.DiscountedAssortment;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class PromotionActivity extends DraverBaseActivity {

    ActivityPromotionBinding _activityPromotionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _activityPromotionBinding = ActivityPromotionBinding.inflate(getLayoutInflater());
        setContentView(_activityPromotionBinding.getRoot());

        _activityPromotionBinding.setViewModel(new PromotionViewModel(this));
        _activityPromotionBinding.executePendingBindings();

    }
}

