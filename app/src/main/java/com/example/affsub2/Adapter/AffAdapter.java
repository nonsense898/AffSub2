package com.example.affsub2.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.affsub2.Activity.ProfileActivity;
import com.example.affsub2.R;
import com.example.affsub2.REST.Model.Result;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AffAdapter extends RecyclerView.Adapter<AffAdapter.AffRecycleViewAdapterHolder> {
    private List<Result> userList = new ArrayList<>();
    private Context context;


    public void setData(List<Result> list, Context context) {
        userList.clear();
        userList.addAll(list);
        this.notifyDataSetChanged();
        this.context = context;
    }


    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public AffRecycleViewAdapterHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView;
        itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_users_layout, null);

        return new AffRecycleViewAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AffRecycleViewAdapterHolder holder, int position) {
        if (!userList.isEmpty()) {
            Result model = userList.get(position);

            Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation_from_right);
            holder.itemView.startAnimation(animation);

            holder.itemView.setOnClickListener((v) -> {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("avatar", model.getPicture().getLarge());
                intent.putExtra("first_name", model.getName().getFirst());
                intent.putExtra("last_name", model.getName().getLast());
                intent.putExtra("age", model.getDob().getAge());
                intent.putExtra("phone", model.getPhone());
                intent.putExtra("email", model.getEmail());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
            holder.initializeViews(model);
        }
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }


    public static class AffRecycleViewAdapterHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView mImageView;
        TextView firstName;
        TextView lastName;


        public AffRecycleViewAdapterHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.card_view);
            mImageView = itemView.findViewById(R.id.user_avatar);
            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
        }

        private void initializeViews(Result model) {
            Picasso.get()
                    .load(model.getPicture().getLarge()).fit().centerInside()
                    .into(mImageView);
            firstName.setText(model.getName().getFirst());
            lastName.setText(model.getName().getLast());
        }
    }
}