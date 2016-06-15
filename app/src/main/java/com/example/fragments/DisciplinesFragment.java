package com.example.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.R;
import com.example.activities.SeeClassroomInfoActivity;
import com.example.adapters.DisciplinesAdapter;
import com.example.adapters.StudentAdapter;
import com.example.api.RestClient;
import com.example.interfaces.RecyclerViewOnClickListenerHack;
import com.example.model.Classroom;
import com.example.model.Student;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DisciplinesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<Classroom> mClassrooms;
    private DisciplinesAdapter mDisciplinesAdapter;


    public DisciplinesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_disciplines, container, false);

        try {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.rvDisciplinesList);
            mLinearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setHasFixedSize(true);

            mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);

            Intent idIntent = getActivity().getIntent();
            String classID = idIntent.getStringExtra("classID");

            RestClient.TAGApiInterface tagApiInterface = RestClient.getClient();
            Call<ArrayList<Classroom>> mListCall = tagApiInterface.getDisciplinesByClassID(classID);
            mListCall.enqueue(new Callback<ArrayList<Classroom>>() {
                @Override
                public void onResponse(Call<ArrayList<Classroom>> call, Response<ArrayList<Classroom>> response) {
                    mClassrooms = response.body();

                    mDisciplinesAdapter = new DisciplinesAdapter(mClassrooms, getContext());
                    mRecyclerView.setAdapter(mDisciplinesAdapter);
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
}
