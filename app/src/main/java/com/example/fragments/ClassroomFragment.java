package com.example.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.R;
import com.example.activities.SeeClassPerSchoolActivity;
import com.example.activities.SeeClassroomInfoActivity;
import com.example.adapters.ClassroomAdapter;
import com.example.adapters.SchoolAdapter;
import com.example.api.RestClient;
import com.example.interfaces.RecyclerViewOnClickListenerHack;
import com.example.model.Classroom;
import com.example.model.School;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassroomFragment extends Fragment implements RecyclerViewOnClickListenerHack {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<Classroom> mClassrooms;
    private ClassroomAdapter mClassroomAdapter;

    public ClassroomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_classroom, container, false);
        try {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.rvClassroomList);
            mLinearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setHasFixedSize(true);

            mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);

            Intent nameIntent = getActivity().getIntent();
            String schoolInepID = nameIntent.getStringExtra("schoolInepID");

            RestClient.TAGApiInterface tagApiInterface = RestClient.getClient();
            Call<ArrayList<Classroom>> mListCall = tagApiInterface.getClassroomsBySchoolInep(schoolInepID);
            mListCall.enqueue(new Callback<ArrayList<Classroom>>() {
                @Override
                public void onResponse(Call<ArrayList<Classroom>> call, Response<ArrayList<Classroom>> response) {
                    mClassrooms = response.body();

                    mClassroomAdapter = new ClassroomAdapter(mClassrooms, getContext());
                    mClassroomAdapter.setRecyclerViewOnClickListenerHack(ClassroomFragment.this);
                    mRecyclerView.setAdapter(mClassroomAdapter);
                }

                @Override
                public void onFailure(Call<ArrayList<Classroom>> call, Throwable t) {
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
        String classID = mClassroomAdapter.returnID(position);

        getActivity().startActivity(new Intent(getActivity(), SeeClassroomInfoActivity.class)
                    .putExtra("classID", classID));
    }

}