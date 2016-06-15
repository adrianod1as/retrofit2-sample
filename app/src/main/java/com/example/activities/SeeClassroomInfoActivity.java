package com.example.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.R;
import com.example.adapters.ClassroomTabAdapter;
import com.example.extra.SlidingTabLayout;

public class SeeClassroomInfoActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_classroom_info);

        //TOOLBAR
        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        mToolbar.setTitle(R.string.app_name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //VIEWPAGER
        mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(new ClassroomTabAdapter(getSupportFragmentManager(), SeeClassroomInfoActivity.this));

        //TABS
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.white));
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setSelected(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
