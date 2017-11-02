package com.veryworks.iyeongjun.shakehere;


import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.igalata.bubblepicker.BubblePickerListener;
import com.igalata.bubblepicker.adapter.BubblePickerAdapter;
import com.igalata.bubblepicker.model.BubbleGradient;
import com.igalata.bubblepicker.model.PickerItem;
import com.igalata.bubblepicker.rendering.BubblePicker;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.veryworks.iyeongjun.shakehere.domain.StaticData.datas;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.drawables;


/**
 * A simple {@link Fragment} subclass.
 */
public class BubbleFragment extends Fragment {

    TypedArray colors;
    @BindView(R.id.bubble)
    BubblePicker bubblePicker;
    Unbinder unbinder;
    Bitmap bitmap;

    public BubbleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bubble, container, false);
        unbinder = ButterKnife.bind(this, view);
        colors = getResources().obtainTypedArray(R.array.colors);
        setBubble();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setBubble() {
        bubblePicker.setBubbleSize(3);
        bubblePicker.setCenterImmediately(true);
        bubblePicker.setAdapter(new BubblePickerAdapter() {
            @Override
            public int getTotalCount() {
                return 20;
            }

            @NotNull
            @Override
            public PickerItem getItem(int position) {
                PickerItem item = new PickerItem();
                item.setTitle(datas.get(position).getTitle());
                item.setBackgroundImage(drawables.get(position));
                Log.d("Bubble","makeItem"+drawables.get(position)+"/"+datas.get(position).getFirstimage());
                item.setGradient(new BubbleGradient(colors.getColor((position * 2) % 8, 0),
                        colors.getColor((position * 2) % 8 + 1, 0), BubbleGradient.VERTICAL));
                item.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
                return item;
            }
        });
        bubblePicker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(@NotNull PickerItem pickerItem) {

            }

            @Override
            public void onBubbleDeselected(@NotNull PickerItem pickerItem) {
            }
        });
    }
}
