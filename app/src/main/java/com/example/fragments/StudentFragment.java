package com.example.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.R;
import com.example.adapters.ClassroomAdapter;
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
public class StudentFragment extends Fragment implements RecyclerViewOnClickListenerHack{

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<Student> mStudents;
    private StudentAdapter mStudentAdapter;

    public StudentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_student, container, false);

        try {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.rvStudentList);
            mLinearLayoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setHasFixedSize(true);

            mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(mLinearLayoutManager);

            Intent idIntent = getActivity().getIntent();
            String classID = idIntent.getStringExtra("classID");

            RestClient.TAGApiInterface tagApiInterface = RestClient.getClient();
            Call<ArrayList<Student>> mListCall = tagApiInterface.getStudentsPerClassroom(classID);
            mListCall.enqueue(new Callback<ArrayList<Student>>() {
                @Override
                public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                    mStudents = response.body();

                    mStudentAdapter = new StudentAdapter(mStudents, getContext());
                    mStudentAdapter.setRecyclerViewOnClickListenerHack(StudentFragment.this);
                    mRecyclerView.setAdapter(mStudentAdapter);
                }

                @Override
                public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
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

    }
}
