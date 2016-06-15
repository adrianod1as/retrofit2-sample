package com.example.adapters;

import android.content.Context;
import android.content.pm.LabeledIntent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.R;
import com.example.interfaces.RecyclerViewOnClickListenerHack;
import com.example.model.Student;

import java.util.ArrayList;

/**
 * Created by Filipi Andrade on 14-Jun-16.
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private ArrayList<Student> mStudentArrayList;
    private Context mContext;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public StudentAdapter(ArrayList<Student> l, Context c) {
        this.mStudentArrayList = l;
        this.mContext = c;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_card, parent, false);
        return new StudentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        String nameStudent = mStudentArrayList.get(position).getName();
        holder.tvNameStudent.setText(nameStudent);
    }

    @Override
    public int getItemCount() {
        return mStudentArrayList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        this.mRecyclerViewOnClickListenerHack = r;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvNameStudent;

        public StudentViewHolder(View itemView) {
            super(itemView);

            tvNameStudent = (TextView) itemView.findViewById(R.id.tvNameStudent);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getAdapterPosition());
            }
        }
    }
}
