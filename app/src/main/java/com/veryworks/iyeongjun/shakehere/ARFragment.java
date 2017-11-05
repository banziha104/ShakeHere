package com.veryworks.iyeongjun.shakehere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;
import butterknife.Unbinder;

public class ARFragment extends Fragment {

    @BindView(R.id.btnAR)
    ImageView btnAR;
    Unbinder unbinder;

    public ARFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ar, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnTouch(R.id.btnAR)
    public boolean onClickedCardView(MotionEvent e, View v) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            btnAR.setImageResource(R.drawable.touched_screen_2);
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            btnAR.setImageResource(R.drawable.touch_screen_2);
            Intent intent = new Intent(getActivity(),ARActivity.class);
            startActivity(intent);
        }
        return true;
    }

}
