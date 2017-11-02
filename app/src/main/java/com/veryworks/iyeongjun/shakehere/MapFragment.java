package com.veryworks.iyeongjun.shakehere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.skp.Tmap.TMapView;
import com.veryworks.iyeongjun.shakehere.domain.Const;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MapFragment extends Fragment {
    Unbinder unbinder;
    @BindView(R.id.tmap)
    FrameLayout frameLayout;
    TMapView tmap;
    public MapFragment() {
        frameLayout = new FrameLayout(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        frameLayout = (FrameLayout) view.findViewById(R.id.tmap);

        tmap = new TMapView(getActivity());
        tmap.setSKPMapApiKey(Const.Auth.TMAP_KEY);
        tmap.setLanguage(TMapView.LANGUAGE_KOREAN);
        tmap.setIconVisibility(true);
        tmap.setZoomLevel(10);
        tmap.setMapType(TMapView.MAPTYPE_STANDARD);
        tmap.setCompassMode(true);
        tmap.setTrackingMode(true);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
