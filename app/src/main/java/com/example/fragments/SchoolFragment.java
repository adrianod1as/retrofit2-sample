package com.example.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.R;
import com.example.activities.SeeClassPerSchoolActivity;
import com.example.adapters.SchoolAdapter;
import com.example.api.RestClient;
import com.example.interfaces.RecyclerViewOnClickListenerHack;
import com.example.model.School;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private SchoolAdapter mSchoolAdapter;

    private ArrayList<School> mSchoolList;


    public SchoolFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_school, container, false);

        try {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.rvSchoolList);
            mLinearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setHasFixedSize(true);

            mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);

            RestClient.TAGApiInterface tagApiInterface = RestClient.getClient();
            Call<ArrayList<School>> mListCall = tagApiInterface.getSchools();
            mListCall.enqueue(new Callback<ArrayList<School>>() {
                @Override
                public void onResponse(Call<ArrayList<School>> call, Response<ArrayList<School>> response) {
                    mSchoolList = response.body();

                    mSchoolAdapter = new SchoolAdapter(mSchoolList, getContext());
                    mRecyclerView.setAdapter(mSchoolAdapter);
                }

                @Override
                public void onFailure(Call<ArrayList<School>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } catch (Exception err) {
            err.printStackTrace();
        }

        return view;
    }

    @Override
    public void onClickListener(View v, int position) {
        String schoolInepID = mSchoolAdapter.returnSchoolInepID(position);

        startActivity(new Intent(getActivity(), SeeClassPerSchoolActivity.class)
                    .putExtra("schoolInepID", schoolInepID));
    }
}
