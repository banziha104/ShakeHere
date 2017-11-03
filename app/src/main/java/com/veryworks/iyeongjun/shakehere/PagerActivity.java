package com.veryworks.iyeongjun.shakehere;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.veryworks.iyeongjun.shakehere.Util.PermissionControl;
import com.veryworks.iyeongjun.shakehere.domain.Const;
import com.veryworks.iyeongjun.shakehere.domain.DataReceiver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.veryworks.iyeongjun.shakehere.BubbleFragment.bubblePicker;
import static com.veryworks.iyeongjun.shakehere.Util.PermissionControl.checkVersion;
import static com.veryworks.iyeongjun.shakehere.Util.UserLocation.currentUserLocation;

public class PagerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.tabLayout)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager pager;
    BubbleFragment bubbleFragment;

    boolean isBubbleVisible = true;
    private int[] tabIcons = {
            R.drawable.list_icon,
            R.drawable.tag_icon,
            R.drawable.map_icon,
            R.drawable.ar_icon
    };
    private int[] tabClickedIcons = {
            R.drawable.list_clicked,
            R.drawable.tag_clicked,
            R.drawable.map_clicked,
            R.drawable.ar_clicked
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);
        startShakeDetect();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ShakeHere");



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        ){
            public void onDrawerClosed(View view) {
                bubblePicker.setVisibility(View.VISIBLE);
                isBubbleVisible = true;
                invalidateOptionsMenu();
            }
            public void onDrawerOpened(View drawerView) {
                bubblePicker.setVisibility(View.INVISIBLE);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if(isBubbleVisible){
                    bubblePicker.setVisibility(View.INVISIBLE);
                    isBubbleVisible = false;
                }
                Log.d("slide",slideOffset+"");
            }

            @Override
            public void onConfigurationChanged(Configuration newConfig) {
                super.onConfigurationChanged(newConfig);
                Toast.makeText(PagerActivity.this, "change", Toast.LENGTH_SHORT).show();
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setView();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            bubbleFragment.onPause();
        } else {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.d
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setView() {
//        DataReceiver dataReceiver = new DataReceiver(this);
//        dataReceiver.getTourDataDefault(Const.Lang.KOREAN, 37.515359, 126.907623);
        setPager();
    }


    public void setPager() {

        Log.d("ARPOINT",currentUserLocation.getLatitude()+"/"+currentUserLocation.getLongitude());

        tab.addTab(tab.newTab().setIcon(tabIcons[0]));
        tab.addTab(tab.newTab().setIcon(tabIcons[1]));
        tab.addTab(tab.newTab().setIcon(tabIcons[2]));
        tab.addTab(tab.newTab().setIcon(tabIcons[3]));
        List<Fragment> datas = new ArrayList<>();

        ListFragment listFragment = new ListFragment();
        bubbleFragment = new BubbleFragment();
        MapFragment mapFragment = new MapFragment();
        ARFragment arFragment = new ARFragment();

        datas.add(listFragment);
        datas.add(bubbleFragment);
        datas.add(mapFragment);
        datas.add(arFragment);

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), datas);
        // 5. 아답터를 페이저 위젯에 연결
        pager.setAdapter(adapter);
        // 6. 페이저가 변경되었을 때 탭을 변경해주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        // 7. 탭이 변경되었때 페이저를 변경해주는 리스너
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabs) {

                tabs.setIcon(tabClickedIcons[tabs.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tabs) {
                tabs.setIcon(tabIcons[tabs.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tabs) {

            }
        });
    }

    private void startShakeDetect() {
        Intent intent = new Intent(PagerActivity.this, ShakeDetectService.class);
        Log.d("Service","start");
        startService(intent);
    }

    interface PickerControl{
        void bubblePause();
        void bubbleResume();
    }

}
