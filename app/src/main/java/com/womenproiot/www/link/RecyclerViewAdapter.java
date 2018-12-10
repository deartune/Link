package com.womenproiot.www.link;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<String> items;
    private int itemLayout;
    String members;
    ArrayList<AttendeeDto> arrayList = null;
    Integer count=1;

    public RecyclerViewAdapter(ArrayList<AttendeeDto> list) {
        this.arrayList = list;
        notifyDataSetChanged();
        Log.e("김정아","리사이클러 생성자");
    }



    public RecyclerViewAdapter(Context context, List<String> items, int itemLayout) {
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("김정아","리사이클러 onCreateViewHolder");

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("김정아","리사이클러 onBindViewHolder");

        holder.textViewName.setText(arrayList.get(position).name);
        holder.textViewAddr.setText(arrayList.get(position).roadAddress);
        holder.textViewFrSeq.setText(arrayList.get(position).frSeq);
        holder.textViewNo.setText("회원"+String.valueOf(count));
        count++;

        }


    @Override
    public int getItemCount() {
        Log.e("김정아","리사이클러 getItemCount");

        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFrSeq,textViewName,textViewAddr,textViewNo;
        Button deleteButton,modifyButton;

       public ViewHolder(View itemView) {
            super(itemView);
           Log.e("김정아","리사이클러 ViewHolder 생성자");
           textViewFrSeq = itemView.findViewById(R.id.textViewFrSeq);
           textViewName = itemView.findViewById(R.id.textViewName);
           textViewAddr = itemView.findViewById(R.id.textViewAddr);
           textViewNo=itemView.findViewById(R.id.textViewNo);
           deleteButton=itemView.findViewById(R.id.deleteButton);
           modifyButton=itemView.findViewById(R.id.modifyButton);
        }
    }
}
