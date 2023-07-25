package com.azismihsan.scholarshipinformation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;

public class ListScholarshipAdapter extends RecyclerView.Adapter<ListScholarshipAdapter.ListViewHolder> {

    private ArrayList<scholarship> listscholarship;

    public ListScholarshipAdapter(ArrayList<scholarship> list){
        this.listscholarship = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_scholarship,parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        scholarship Scholar = listscholarship.get(position);
        Glide.with(holder.itemView.getContext())
                .load(Scholar.getPhoto())
                .apply(new RequestOptions().override(70, 70))
                .into(holder.imgPhoto);

        holder.tvName.setText(Scholar.getName());
        holder.tvLocation.setText(Scholar.getLocation());

    }

    @Override
    public int getItemCount() {
        return listscholarship.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvLocation;

        ListViewHolder (View itemView){
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvLocation= itemView.findViewById(R.id.tv_item_location);
        }
        }


    }

