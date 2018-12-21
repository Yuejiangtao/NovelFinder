package com.example.administrator.novelfinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecViewHolder> {
    //    private List<Date> dates =new ArrayList<>();
    private List<Date> mDateList;

    //    public RecyclerAdapter(Date dates,Context context) {
//        this.dates = dates;
//        mContext=context;
//    }
    public RecyclerAdapter(List<Date> dateList, MianActivitty2 mainActivity) {
        mDateList = dateList;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itm, viewGroup, false);
        return new RecViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder recViewHolder, int i) {
        Date date = mDateList.get(i);
        recViewHolder.title.setText(date.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDateList.size();
    }

    class RecViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public RecViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
        }
    }

}
