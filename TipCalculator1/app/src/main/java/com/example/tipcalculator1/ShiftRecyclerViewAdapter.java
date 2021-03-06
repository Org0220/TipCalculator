package com.example.tipcalculator1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ShiftRecyclerViewAdapter extends RecyclerView.Adapter<ShiftRecyclerViewAdapter.ViewHolder>{
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> workedHours = new ArrayList<>();
    ArrayList<String> tipss = new ArrayList<>();
    ArrayList<String> user_id = new ArrayList();
    ArrayList<String> users;
    Context mContext;
    LayoutInflater minflator;
    String editId = "";

    public ShiftRecyclerViewAdapter(ArrayList<String> date, ArrayList<String> workedHours, ArrayList<String> tipss, ArrayList<String> user_id, Context mContext) {
        this.date = date;
        this.workedHours = workedHours;
        this.tipss = tipss;
        this.user_id = user_id;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ShiftRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = minflator.from(parent.getContext()).inflate(R.layout.shift_child_row, parent, false);

        return new ShiftRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(mContext);
        holder.date.setText(date.get(position) );
        holder.workedHour.setText("Worked Hours: " + workedHours.get(position));
        holder.tips.setText("Tips :" + tipss.get(position));
        System.out.println(tipss.get(position));
        System.out.println(workedHours.get(position));
        System.out.println(date.get(position));
        double sl = (double)(Double.parseDouble(tipss.get(position)) + 10.80) * Double.parseDouble(workedHours.get(position)); // calculates the salary based on the (tips + 10.80) * hours

         holder.salary.setText(String.format("Salary: %.2f",sl));
    }

    @Override
    public int getItemCount() {
        return user_id.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        TextView date, workedHour, tips, salary;
        DataBaseHelperShift myDb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myDb = new DataBaseHelperShift(mContext);
            tips = itemView.findViewById(R.id.tips);
            date = itemView.findViewById(R.id.Date);
            salary = itemView.findViewById(R.id.amountEarned);
            workedHour = itemView.findViewById(R.id.hoursWorked);
        }
    }
}
