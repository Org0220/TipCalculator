package com.example.tipcalculator1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> ids = new ArrayList<>();
    ArrayList<String> firstNames = new ArrayList<>();
    ArrayList<String> lastNames = new ArrayList<>();
    ArrayList<String> positions = new ArrayList();
    ArrayList<String> users;
    Context mContext;
    LayoutInflater minflator;
    String editId = "";

    public UserRecyclerViewAdapter(ArrayList<String> ids, ArrayList<String> firstNames, ArrayList<String> lastNames, ArrayList<String> positions, ArrayList<String> users, Context mContext) {
        this.ids = ids;
        this.firstNames = firstNames;
        this.users = users;
        this.lastNames = lastNames;
        this.positions = positions;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = minflator.from(parent.getContext()).inflate(R.layout.users_child_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(mContext);
        holder.name.setText(firstNames.get(position) + " " + lastNames.get(position));
        holder.position.setText(positions.get(position));
        holder.username.setText(users.get(position));
        holder.shifts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ShiftInformation.class);
                i.putExtra("id", ids.get(position));
                mContext.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return firstNames.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, position, username;
        Button shifts;
        DataBaseHelperShift myDb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myDb = new DataBaseHelperShift(mContext);
            username = itemView.findViewById(R.id.tips);
            name = itemView.findViewById(R.id.name);
            position = itemView.findViewById(R.id.position);
            shifts = itemView.findViewById(R.id.shifts);
        }
    }
}
