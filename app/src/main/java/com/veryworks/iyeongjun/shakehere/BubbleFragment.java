package com.veryworks.iyeongjun.shakehere;


import android.content.Intent;
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

import static com.veryworks.iyeongjun.shakehere.Util.MyUtil.convertColor;
import static com.veryworks.iyeongjun.shakehere.Util.MyUtil.convertGra;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.datas;
import static com.veryworks.iyeongjun.shakehere.domain.StaticData.drawables;


/**
 * A simple {@link Fragment} subclass.
 */
public class BubbleFragment extends Fragment implements PagerActivity.PickerControl{

    int page;
    TypedArray colors;
    static BubblePicker bubblePicker;
    Unbinder unbinder;


    public BubbleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bubble, container, false);
        unbinder = ButterKnife.bind(this, view);
        bubblePicker = view.findViewById(R.id.bubble);
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
        bubblePicker.setVisibility(View.VISIBLE);
        bubblePicker.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        bubblePicker.setVisibility(View.INVISIBLE);
        bubblePicker.onPause();
    }

    private void setBubble() {
        if(datas.size() > 20) page = 20;
        else page = datas.size();
        bubblePicker.setBubbleSize(3);
        bubblePicker.setCenterImmediately(true);
        bubblePicker.setAdapter(new BubblePickerAdapter() {
            @Override
            public int getTotalCount() {
                return page;
            }

            @NotNull
            @Override
            public PickerItem getItem(int position) {
                PickerItem item = new PickerItem();
                item.setTitle(datas.get(position).getTitle());
                Log.d("Color",datas.get(position).getTitle()+
                        getMyColor(convertGra(datas.get(position).getContenttypeid()))+
                        getMyColor(convertColor(datas.get(position).getContenttypeid()))+"");
                item.setGradient(new BubbleGradient(
                        getMyColor(convertGra(datas.get(position).getContenttypeid())),
                        getMyColor(convertColor(datas.get(position).getContenttypeid())),
                        BubbleGradient.VERTICAL));
                item.setTextColor(ContextCompat.getColor(getActivity(), android.R.color.white));
//                Log.d("colorsss",(colors.getColor((position * 2) % 8,0))+
//                        ((colors.getColor((position * 2) % 8 ,0)) )+"");
//                Log.d("colorsss",position+
//                        datas.get(position).getContenttypeid()+"/"+
//                        datas.get(position).getContenttypeid()+"/"+
//                        convertColor(datas.get(position).getContenttypeid())+"/"+
//                        convertGra(datas.get(position).getContenttypeid()));
                return item;
            }
        });
        bubblePicker.setListener(new BubblePickerListener() {
            @Override
            public void onBubbleSelected(@NotNull PickerItem pickerItem) {
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("pos2",pickerItem.getTitle());
                startActivity(intent);
            }

            @Override
            public void onBubbleDeselected(@NotNull PickerItem pickerItem) {
            }
        });
    }
    @Override
    public void bubblePause() {
        bubblePicker.onPause();
    }

    @Override
    public void bubbleResume() {
        bubblePicker.onResume();
    }
    private int getMyColor(int i){
        return getResources().getColor(i);
    }
}
