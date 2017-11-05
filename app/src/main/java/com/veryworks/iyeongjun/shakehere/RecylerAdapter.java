package com.veryworks.iyeongjun.shakehere;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.veryworks.iyeongjun.shakehere.domain.ARPoint;
import com.veryworks.iyeongjun.shakehere.domain.Item;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.veryworks.iyeongjun.shakehere.Util.MyUtil.convertIcon;
import static com.veryworks.iyeongjun.shakehere.Util.MyUtil.convertType;
import static com.veryworks.iyeongjun.shakehere.Util.UserLocation.currentUserLocation;

/**
 * Created by iyeongjun on 2017. 11. 3..
 */

public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder> {
    ArrayList<Item> data = new ArrayList<>();
    Context context;
    int pos;
    public RecylerAdapter(Context context,ArrayList<Item> data) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        Item item = data.get(i);
        holder.reTxtTitle.setText(item.getTitle());
        holder.txtAdress.setText(item.getAddr1());
        holder.txtCall.setText(item.getTel());
        holder.txtDistance.setText(getDistance(item));
        holder.txtType.setText(convertType(item.getContenttypeid()));
        holder.setImage(item);
        holder.setIcon(item);
        holder.setPosition(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.reTxtTitle) TextView reTxtTitle;
        @BindView(R.id.imgType) ImageView imgType;
        @BindView(R.id.roundedImageView) RoundedImageView roundedImageView;
        @BindView(R.id.txtAdress) TextView txtAdress;
        @BindView(R.id.txtCall) TextView txtCall;
        @BindView(R.id.txtType) TextView txtType;
        @BindView(R.id.txtDistance) TextView txtDistance;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            setTxtSetting();
        }
        public void setImage(Item item) {
            if (item.getFirstimage() == null) {
                Picasso.with(context).load(R.drawable.default_image).into(roundedImageView);
            } else {
                Picasso.with(context).load(item.getFirstimage()).into(roundedImageView);
            }
            Log.d("image",convertType(item.getContenttypeid())+"");
        }
        public void setIcon(Item item){
            Picasso.with(context).load(convertIcon(item.getContenttypeid())).into(imgType);
        }
        public int dp2px(int dp) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics displaymetrics = new DisplayMetrics();
            display.getMetrics(displaymetrics);
            return (int) (dp * displaymetrics.density + 0.5f);
        }
        private void setTxtSetting(){
            reTxtTitle.setTextColor(Color.WHITE);
            reTxtTitle.setTextSize(10.0f);
//            txtAdress.setTextSize(9.0f);
//            txtType.setTextSize(10.0f);
//            txtCall.setTextSize(10.0f);
//            txtDistance.setTextSize(10.0f);
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @OnClick(R.id.cardView)
        public void cardviewClicked(){
            Intent intent = new Intent(context,DetailActivity.class);
            intent.putExtra("pos",position);
            context.startActivity(intent);
        }
    }

    private String getDistance(Item item){
        Random random = new Random();
        ARPoint arPoint = new ARPoint(
                item.getTitle(),
                Double.parseDouble(item.getMapy()),
                Double.parseDouble(item.getMapx()),
                ((double)(random.nextInt()%150))
        );
        return (((int)(currentUserLocation.distanceTo(arPoint.getLocation())))/(1000.0))+"km";
    }
}
