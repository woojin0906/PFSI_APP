package kr.co.company.pfsi_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SupportInfoAdapter extends RecyclerView.Adapter<SupportInfoAdapter.CustomViewHolder> {

    private ArrayList<SupportInfoData> arrayList;

    public SupportInfoAdapter(ArrayList<SupportInfoData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_supportprogram, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.programTitle.setText(arrayList.get(position).getProgramTitle());
        holder.time.setText(arrayList.get(position).getTime());
        holder.phone.setText(arrayList.get(position).getTime());
        holder.group.setText(arrayList.get(position).getGroup());
        holder.city.setText(arrayList.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        //삼합 연산자 (arrayList가 널이 아니면 참인 경우 arrayList.size(), 거짓인 경우 0)
        return (arrayList != null ? arrayList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView programTitle, time, phone, group, city;

        public CustomViewHolder(@NonNull View view) {
            super(view);
            this.programTitle=view.findViewById(R.id.programTitle);
            this.time=view.findViewById(R.id.time);
            this.phone=view.findViewById(R.id.phone);
            this.group=view.findViewById(R.id.group);
            this.city=view.findViewById(R.id.city);
        }
    }

}
