package com.example.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.R;
import com.example.interfaces.RecyclerViewOnClickListenerHack;
import com.example.model.School;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filipi Andrade on 14-Jun-16.
 */
public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder> {

    private ArrayList<School> mSchoolList;
    private Context mContext;

    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public SchoolAdapter(ArrayList<School> l, Context c) {
        this.mContext = c;
        this.mSchoolList = l;
    }

    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_school_card, parent, false);
        return new SchoolViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SchoolViewHolder holder, int position) {
        String nameSchool = mSchoolList.get(position).getName();
        holder.tvNameSchool.setText(nameSchool);
    }

    @Override
    public int getItemCount() {
        return mSchoolList.size();
    }

    public String returnSchoolInepID(int position) {
        return mSchoolList.get(position).getInep_id();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public class SchoolViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNameSchool;

        public SchoolViewHolder(View itemView) {
            super(itemView);

            tvNameSchool = (TextView) itemView.findViewById(R.id.tvNameSchool);

            tvNameSchool.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getAdapterPosition());
            }
        }
    }
}
