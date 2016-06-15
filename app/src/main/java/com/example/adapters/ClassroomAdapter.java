package com.example.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.R;
import com.example.interfaces.RecyclerViewOnClickListenerHack;
import com.example.model.Classroom;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Filipi Andrade on 14-Jun-16.
 */
public class ClassroomAdapter extends RecyclerView.Adapter<ClassroomAdapter.ClassroomViewHolder> {

    private ArrayList<Classroom> mClassroomArrayList;

    private Context mContext;

    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public ClassroomAdapter(ArrayList<Classroom> l, Context c) {
        mClassroomArrayList = l;
        mContext = c;
    }

    @Override
    public ClassroomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cassroom_card, parent, false);
        return new ClassroomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ClassroomViewHolder holder, int position) {
        String nameClassroom = mClassroomArrayList.get(position).getName();
        holder.tvNameClassroom.setText(nameClassroom);
    }

    @Override
    public int getItemCount() {
        return mClassroomArrayList.size();
    }

    public String returnID(int position) {
        return mClassroomArrayList.get(position).getId();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public class ClassroomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNameClassroom;

        public ClassroomViewHolder(View itemView) {
            super(itemView);

            tvNameClassroom = (TextView) itemView.findViewById(R.id.tvNameClassroom);

            tvNameClassroom.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getAdapterPosition());
            }
        }
    }
}
