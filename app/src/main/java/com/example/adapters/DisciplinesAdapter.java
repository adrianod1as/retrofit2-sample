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

import java.util.ArrayList;

/**
 * Created by Filipi Andrade on 15-Jun-16.
 */
public class DisciplinesAdapter extends RecyclerView.Adapter<DisciplinesAdapter.DisciplinesViewHolder> {

    private ArrayList<Classroom> mClassrooms;
    private Context mContext;

    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public DisciplinesAdapter(ArrayList<Classroom> l, Context c){
        this.mClassrooms = l;
        this.mContext = c;
    }

    @Override
    public DisciplinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disciplines_card, parent, false);
        return new DisciplinesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DisciplinesViewHolder holder, int position) {
        String nameDiscipline;
        if (mClassrooms.get(position).getDiscipline_mathematics().equals("Não")) {
            holder.tvNameDiscipline.setText("Nao");
        } else {
            nameDiscipline = "Matematica";
            holder.tvNameDiscipline.setText(nameDiscipline);
        }
    }

    @Override
    public int getItemCount() {
        return mClassrooms.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack) {
        this.mRecyclerViewOnClickListenerHack = mRecyclerViewOnClickListenerHack;
    }

    public class DisciplinesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvNameDiscipline;

        public DisciplinesViewHolder(View itemView) {
            super(itemView);

            tvNameDiscipline = (TextView) itemView.findViewById(R.id.tvNameDiscipline);

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
