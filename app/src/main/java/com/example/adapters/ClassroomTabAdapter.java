package com.example.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.fragments.DisciplinesFragment;
import com.example.fragments.StudentFragment;

/**
 * Created by Filipi Andrade on 15-Jun-16.
 */
public class ClassroomTabAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] titles = {"ALUNOS", "DISCIPLINAS"};
    Fragment fragment = null;

    public ClassroomTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(final int position) {
        if (position == 0) { // ALUNOS
            fragment = new StudentFragment();
        } else if (position == 1) { // DISCIPLINAS
            fragment = new DisciplinesFragment();
        }

        Bundle iBundle = new Bundle();
        iBundle.putInt("position", position);

        fragment.setArguments(iBundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (titles[position]);
    }
}
